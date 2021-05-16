package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

import static java.lang.StrictMath.round;

public class ReceiptScreen extends AppCompatActivity {

    DatabaseHelper db;

    Button printReceipt;

    TextView subTotal, gst, qst, finalPrice;

    OutputStream mOutputStream;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;
    BluetoothSocket mSocket;
    InputStream mInputStream;
    Boolean stopWorker;
    int readBufferPosition;
    byte[] readBuffer;
    Thread workerThread;
    ArrayList<String> receiptItemImages = new ArrayList<>();
    ArrayList<String> receiptItemNames= new ArrayList<>();
    ArrayList<Integer> receiptItemQuantities = new ArrayList<>();
    ArrayList<Double> receiptItemPrices = new ArrayList<>();

    double subTotalPrice = 0;
    double gstPrice = 0;
    double qstPrice = 0;
    double finalTotalPrice = 0;

    double price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_screen);

        db = new DatabaseHelper(this);

        printReceipt = findViewById(R.id.printReceiptButton);

        subTotal = findViewById(R.id.receiptSubTotalPrice);
        gst = findViewById(R.id.receiptGSTPrice);
        qst = findViewById(R.id.receiptQSTPrice);
        finalPrice = findViewById(R.id.receiptFinalPriceValue);


        Cursor receiptItems = db.getItemFromCart(MainActivity.customerId);

        while (receiptItems.moveToNext()){
            Item item = db.getItem(receiptItems.getInt(2));

            price = receiptItems.getInt(3) * item.getPrice();

            addItemImage(String.valueOf(receiptItems.getInt(2)));
            receiptItemNames.add(item.getName());
            receiptItemPrices.add(price);
            receiptItemQuantities.add(receiptItems.getInt(3));

            subTotalPrice += price;

            price = 0;
        }

        subTotalPrice = round(subTotalPrice*100.0)/100.0;
        subTotal.setText("$"+subTotalPrice);

        gstPrice = round((subTotalPrice * 5 / 100)*100.0)/100.0;
        qstPrice = round((subTotalPrice * 9.975 / 100)*100.0)/100.0;
        finalTotalPrice = subTotalPrice + gstPrice + qstPrice;
        finalTotalPrice = round(finalTotalPrice*100.0)/100.0;

        gst.setText("$"+gstPrice);
        qst.setText("$"+qstPrice);
        finalPrice.setText("$"+finalTotalPrice);


        RecyclerView recycleView = findViewById(R.id.receiptRecyclerView);
        ReceiptAdapter adapter = new ReceiptAdapter(receiptItemImages, receiptItemNames, receiptItemQuantities, receiptItemPrices, this);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        printReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    findBT();
                    openBT();

                    for (int i = 0; i < receiptItemNames.size(); i++)
                    {
                        printTickets(receiptItemNames.get(i), receiptItemQuantities.get(i), receiptItemPrices.get(i));
                    }

                    closeBT();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // this will find a bluetooth printer device
    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                Toast.makeText(ReceiptScreen.this, "No bluetooth adapter available", Toast.LENGTH_SHORT).show();
            }

            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {

                    // RPP300 is the name of the bluetooth printer device
                    // we got this name from the list of paired devices
                    if (device.getName().equals("XT4131A")) {
                        mDevice = device;
                        break;
                    }
                }
            }

            Toast.makeText(ReceiptScreen.this, "Bluetooth device found.", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // tries to open a connection to the bluetooth printer device
    void openBT() throws IOException {
        try {

            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mSocket = mDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();

            beginListenForData();

            Toast.makeText(ReceiptScreen.this, "Bluetooth Connection Opened", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * after opening a connection to bluetooth printer device,
     * we have to listen and check if a data were sent to be printed.
     */
    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mInputStream.available();

                            if (bytesAvailable > 0) {

                                byte[] packetBytes = new byte[bytesAvailable];
                                mInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
                                                Toast.makeText(ReceiptScreen.this, data, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // this will send text data to be printed by the bluetooth printer
    void printTickets(String itemName, int itemQuantity, double itemPrice) throws IOException {
        try {
            byte[] arrayOfByte1 = { 27, 33, 0 };
            byte[] format = { 27, 33, 0 };
            // Bold
            format[2] = ((byte)(0x8 | arrayOfByte1[2]));
            // Height
            format[2] = ((byte)(0x10 | arrayOfByte1[2]));
            // Width
            format[2] = ((byte) (0x20 | arrayOfByte1[2]));
            byte[] center = new byte[]{ 0x1b, 0x61, 0x01 };
            // the text typed by the user
            StringBuilder msg = new StringBuilder();
            msg.append("\n");
            msg.append("\n");
            msg.append("\n HoHo Korean BBQ Restaurant");
            mOutputStream.write(center);
            mOutputStream.write(format);
            mOutputStream.write(msg.toString().getBytes());

            // Bold
            format[2] = ((byte)(0x8 | arrayOfByte1[2]));
            msg = new StringBuilder();
            msg.append("\n Address: 6521 Somerled Ave, Montreal, Quebec H4V 1S7");
            msg.append("\n Tel.: (514) 488-2580");
            msg.append("\n");
            msg.append("\n");
            msg.append("\n");
            mOutputStream.write(center);
            mOutputStream.write(format);
            mOutputStream.write(msg.toString().getBytes());

            // Bold
            format[2] = ((byte)(0x8 | arrayOfByte1[2]));
            byte[] left = new byte[]{ 0x1b, 0x61, 0x00 };
            msg = new StringBuilder();
            msg.append("\n Event");
            mOutputStream.write(format);
            mOutputStream.write(left);
            mOutputStream.write(msg.toString().getBytes());

            mOutputStream.write(arrayOfByte1);

            left = new byte[]{ 0x1b, 0x61, 0x00 };
            msg = new StringBuilder();
            msg.append("\n  Item Name  : " + itemName);
            msg.append("\n  Quantity  : " + itemQuantity);
            msg.append("\n  Price  : " + itemPrice);
            msg.append("\n");
            msg.append("\n");
            mOutputStream.write(arrayOfByte1);
            mOutputStream.write(left);
            mOutputStream.write(msg.toString().getBytes());

            // Bold
            format[2] = ((byte)(0x8 | arrayOfByte1[2]));
            msg = new StringBuilder();
            msg.append("\n Ticket");
            mOutputStream.write(format);
            mOutputStream.write(left);
            mOutputStream.write(msg.toString().getBytes());

            mOutputStream.write(arrayOfByte1);

            left = new byte[]{ 0x1b, 0x61, 0x00 };
            msg = new StringBuilder();
            msg.append("\n  Code              : " + "to.getCode()");
            msg.append("\n  Class             : " + "to.getTclass()");
            msg.append("\n  Fee               : " + "to.getPrice()");

//            msg.append("\n  Code              : " + to.getCode());
//            msg.append("\n  Class             : " + to.getTclass() );
//            msg.append("\n  Fee               : " + to.getPrice());
            msg.append("\n  Seat              : " + "N / A");
            msg.append("\n  Date of Purchase  : " + Calendar.getInstance().getTime().toString());
            msg.append("\n");
            msg.append("\n");
            msg.append("\n");
            msg.append("\n");
            msg.append("\n");
            mOutputStream.write(arrayOfByte1);
            mOutputStream.write(left);
            mOutputStream.write(msg.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // close the connection to bluetooth printer.
    void closeBT() throws IOException {
        try {
            stopWorker = true;
            mOutputStream.close();
            mInputStream.close();
            mSocket.close();
            // myLabel.setText("Bluetooth Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addItemImage(String itemId){
        if (itemId.equals("1")){
            receiptItemImages.add("https://www.theflavorbender.com/wp-content/uploads/2018/04/Galbi-Korean-BBQ-Short-Ribs-Feat2.jpg");
        }else if (itemId.equals("2")){
            receiptItemImages.add("https://assets.bonappetit.com/photos/57acd741f1c801a1038bc801/8:5/w_4839,h_3024,c_limit/basic-bulgogi.jpg");
        }else if (itemId.equals("3")){
            receiptItemImages.add("https://pinoybites.com/wp-content/uploads/2020/06/Snapseed-52-scaled.jpg");
        }else if (itemId.equals("4")){
            receiptItemImages.add("https://www.thespruceeats.com/thmb/Z76Rt2kLpu7QguWrBd1tt5grKMU=/1500x1000/filters:fill(auto,1)/slow-roasted-pork-belly-crispy-skin-3059509-7_preview-5b16dcbb1d6404003605a196.jpeg");
        }else if (itemId.equals("5")){
            receiptItemImages.add("https://thewholecook.com/wp-content/uploads/2020/01/Sweet-Spicy-Chicken-Bites-by-The-Whole-Cook-horizontal-3.jpg");
        }else if (itemId.equals("6")){
            receiptItemImages.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/d05f9fcc7003488aa2840d15f4a7d470/BFV30005_SpicyKoreanPork-FB1080SQ_H264.jpg");
        }else if (itemId.equals("7")){
            receiptItemImages.add("https://futuredish.com/wp-content/uploads/2019/04/BulgogiDosirak.jpg");
        }else if (itemId.equals("8")){
            receiptItemImages.add("https://cdn.shopify.com/s/files/1/0486/0693/7249/products/20210403_163527_2048x2048.jpg?v=1617745215");
        }else if (itemId.equals("9")){
            receiptItemImages.add("https://www.koreanbapsang.com/wp-content/uploads/2010/05/DSC_1830.jpg");
        }else if (itemId.equals("10")){
            receiptItemImages.add("https://futuredish.com/wp-content/uploads/2018/10/Dosirak-Thumbnail-320x321.jpg");
        }else if (itemId.equals("11")){
            receiptItemImages.add("https://futuredish.com/wp-content/uploads/2020/10/Spicy-Garlic-Dakgangjeong.jpg");
        }else if (itemId.equals("12")){
            receiptItemImages.add("https://www.halfbakedharvest.com/wp-content/uploads/2018/01/Superfood-Bibimbap-with-Crispy-Tofu-1-500x500.jpg");
        }else if (itemId.equals("13")){
            receiptItemImages.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/199535.jpg");
        }else if (itemId.equals("14")){
            receiptItemImages.add("https://www.justataste.com/wp-content/uploads/2016/03/teriyaki-chicken-stir-fry-noodles-recipe.jpg");
        }else if (itemId.equals("15")){
            receiptItemImages.add("https://www.koreanbapsang.com/wp-content/uploads/2011/04/DSC_0085-1-e1539058478522.jpg");
        }else if (itemId.equals("16")){
            receiptItemImages.add("https://futuredish.com/wp-content/uploads/2020/04/Soondubu-Hero-Image-3.jpg");
        }else if (itemId.equals("17")){
            receiptItemImages.add("https://futuredish.com/wp-content/uploads/2019/09/Ttukbaegi-Bulgogi.jpg");
        }else if (itemId.equals("18")){
            receiptItemImages.add("https://www.koreanbapsang.com/wp-content/uploads/2018/06/DSC_0672.jpg");
        }else if (itemId.equals("19")){
            receiptItemImages.add("https://i1.wp.com/seonkyounglongest.com/wp-content/uploads/2019/09/Tangsuyuk-12.jpg?fit=1500%2C1000&ssl=1");
        }else if (itemId.equals("20")){
            receiptItemImages.add("https://twoplaidaprons.com/wp-content/uploads/2020/09/tteokbokki-top-down-view-of-tteokbokki-in-a-bowl-500x500.jpg");
        }else if (itemId.equals("21")){
            receiptItemImages.add("https://www.spicetheplate.com/wp-content/uploads/2017/02/Tuna-Kimchi-Fried-Rice-8-320x320.jpg");
        }else if (itemId.equals("22")){
            receiptItemImages.add("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2016/12/21/0/FNK_Haemul-Pajeon-2_s4x3.jpg.rend.hgtvcom.616.462.suffix/1482354070138.jpeg");
        }else if (itemId.equals("23")){
            receiptItemImages.add("https://ryukoch.com/images/food-blog/jokbal-t.jpg");
        }else if (itemId.equals("24")){
            receiptItemImages.add("https://www.maangchi.com/wp-content/uploads/2019/11/vegankimchi-insta.jpg");
        }else if (itemId.equals("25")){
            receiptItemImages.add("https://www.theflavorbender.com/wp-content/uploads/2018/04/Galbi-Korean-BBQ-Short-Ribs-Feat2.jpg");
        }else if (itemId.equals("26")){
            receiptItemImages.add("https://cdn11.bigcommerce.com/s-5bce5hukxg/images/stencil/1280x1280/products/2401/2989/coke355__27172.1580962278.jpg?c=2");
        }else if (itemId.equals("27")){
            receiptItemImages.add("https://supremepizzaonline.ca/wp-content/uploads/2017/08/7up-Can-update.jpg");
        }else if (itemId.equals("28")){
            receiptItemImages.add("https://cdn.shopify.com/s/files/1/0036/4806/1509/products/305d0939eab876525890bc24f21124adbe4d1128_square119193_1.jpg?v=1611330354");
        }else if (itemId.equals("29")){
            receiptItemImages.add("https://www.lcbo.com/content/dam/lcbo/products/383059.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg");
        }else if (itemId.equals("30")){
            receiptItemImages.add("https://www.lcbo.com/content/dam/lcbo/products/546564.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg");
        }else if (itemId.equals("31")){
            receiptItemImages.add("https://www.mybottleshop.com/media/catalog/product/cache/37a945ae749548a321239175b147c2a7/t/s/tsingtao_beer_bottles_12_pack_640ml.jpg");
        }else{
            receiptItemImages.add("https://image.shutterstock.com/image-vector/spoon-fork-icon-symbol-vector-260nw-1673884297.jpg");
        }
    }
}