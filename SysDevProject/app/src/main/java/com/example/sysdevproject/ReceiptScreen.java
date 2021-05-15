package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.StrictMath.round;

public class ReceiptScreen extends AppCompatActivity {

    DatabaseHelper db;

    Button printReceipt;

    TextView subTotal, gst, qst, finalPrice;

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

            }
        });
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
        }
    }
}