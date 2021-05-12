package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuScreen extends AppCompatActivity {

    DatabaseHelper db;

    ArrayList<Integer> itemOneId = new ArrayList<>();
    ArrayList<String> itemOneImage = new ArrayList<>();
    ArrayList<String> itemOneName = new ArrayList<>();
    ArrayList<Double> itemOnePrice = new ArrayList<>();

    ArrayList<Integer> itemTwoId = new ArrayList<>();
    ArrayList<String> itemTwoImage = new ArrayList<>();
    ArrayList<String> itemTwoName = new ArrayList<>();
    ArrayList<Double> itemTwoPrice = new ArrayList<>();

    ArrayList<Integer> itemThreeId = new ArrayList<>();
    ArrayList<String> itemThreeImage = new ArrayList<>();
    ArrayList<String> itemThreeName = new ArrayList<>();
    ArrayList<Double> itemThreePrice = new ArrayList<>();

    Button combo, twoSet, plate, bibimbap, drinks;
    Button checkoutBtn, cancel;
    TextView menuScreenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        db = new DatabaseHelper(this);

        combo = findViewById(R.id.menuComboButton);
        twoSet = findViewById(R.id.menu2SetButton);
        plate = findViewById(R.id.menuPlateButton);
        bibimbap = findViewById(R.id.menuBibimbapButton);
        drinks = findViewById(R.id.menuDrinksButton);
        menuScreenTitle = findViewById(R.id.menuScreenHeader);

        checkoutBtn = findViewById(R.id.menuCheckoutButton);
        cancel = findViewById(R.id.menuCancelOrderButton);

        Cursor allItems = db.getAllItems();


        while (allItems.moveToNext()) {

            if (allItems.getString(0).equals("1") ||
                    allItems.getString(0).equals("4") ||
                    allItems.getString(0).equals("7") ||
                    allItems.getString(0).equals("10") ||
                    allItems.getString(0).equals("13") ||
                    allItems.getString(0).equals("16") ||
                    allItems.getString(0).equals("19") ||
                    allItems.getString(0).equals("22") ||
                    allItems.getString(0).equals("25") ||
                    allItems.getString(0).equals("28") ||
                    allItems.getString(0).equals("31")){
                //Toast.makeText(MenuScreen.this, allItems.getString(0) + allItems.getString(1), Toast.LENGTH_SHORT).show();

                itemOneId.add(allItems.getInt(0));
                addItemImage(allItems.getString(0));
                itemOneName.add(allItems.getString(1));
                itemOnePrice.add(allItems.getDouble(3));
            } else if (allItems.getString(0).equals("2") ||
                    allItems.getString(0).equals("5") ||
                    allItems.getString(0).equals("8") ||
                    allItems.getString(0).equals("11") ||
                    allItems.getString(0).equals("14") ||
                    allItems.getString(0).equals("17") ||
                    allItems.getString(0).equals("20") ||
                    allItems.getString(0).equals("23") ||
                    allItems.getString(0).equals("26") ||
                    allItems.getString(0).equals("29")){
                //Toast.makeText(MenuScreen.this, allItems.getString(0) + allItems.getString(1), Toast.LENGTH_SHORT).show();

                itemTwoId.add(allItems.getInt(0));
                addItemImage(allItems.getString(0));
                itemTwoName.add(allItems.getString(1));
                itemTwoPrice.add(allItems.getDouble(3));
            } else if (allItems.getString(0).equals("3") ||
                    allItems.getString(0).equals("6") ||
                    allItems.getString(0).equals("9") ||
                    allItems.getString(0).equals("12") ||
                    allItems.getString(0).equals("15") ||
                    allItems.getString(0).equals("18") ||
                    allItems.getString(0).equals("21") ||
                    allItems.getString(0).equals("24") ||
                    allItems.getString(0).equals("27") ||
                    allItems.getString(0).equals("30")){
                //Toast.makeText(MenuScreen.this, allItems.getString(0) + allItems.getString(1), Toast.LENGTH_SHORT).show();

                itemThreeId.add(allItems.getInt(0));
                addItemImage(allItems.getString(0));
                itemThreeName.add(allItems.getString(1));
                itemThreePrice.add(allItems.getDouble(3));
            }
        }

        itemTwoId.add(null);
        itemTwoImage.add(null);
        itemTwoName.add(null);
        itemTwoPrice.add(null);

        itemThreeId.add(null);
        itemThreeImage.add(null);
        itemThreeName.add(null);
        itemThreePrice.add(null);


        RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
        MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                itemTwoId, itemTwoImage, itemTwoName, itemTwoPrice,
                itemThreeId, itemThreeImage, itemThreeName, itemThreePrice,
                this);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));


        combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                itemTwoImage.clear();
                itemTwoName.clear();
                itemTwoPrice.clear();

                itemThreeImage.clear();
                itemThreeName.clear();
                itemThreePrice.clear();

                menuScreenTitle.setText("Combo");


                Cursor comboItems = db.getComboItems();

                while (comboItems.moveToNext()){
                    if (comboItems.getString(0).equals("7") ||
                            comboItems.getString(0).equals("10")) {

                        itemOneId.add(allItems.getInt(0));
                        addItemImage(comboItems.getString(0));
                        itemOneName.add(comboItems.getString(1));
                        itemOnePrice.add(comboItems.getDouble(3));
                    }else if (comboItems.getString(0).equals("8") ||
                            comboItems.getString(0).equals("11")){

                        itemTwoId.add(allItems.getInt(0));
                        addItemImage(comboItems.getString(0));
                        itemTwoName.add(comboItems.getString(1));
                        itemTwoPrice.add(comboItems.getDouble(3));
                    }else if (comboItems.getString(0).equals("9")){

                        itemThreeId.add(allItems.getInt(0));
                        addItemImage(comboItems.getString(0));
                        itemThreeName.add(comboItems.getString(1));
                        itemThreePrice.add(comboItems.getDouble(3));
                    }
                }

                itemThreeId.add(null);
                itemThreeImage.add(null);
                itemThreeName.add(null);
                itemThreePrice.add(null);

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                        itemTwoId, itemTwoImage, itemTwoName, itemTwoPrice,
                        itemThreeId, itemThreeImage, itemThreeName, itemThreePrice,
                        getApplicationContext());
                recycleView.setAdapter(adapter);
                recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });
        twoSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                itemTwoImage.clear();
                itemTwoName.clear();
                itemTwoPrice.clear();

                itemThreeImage.clear();
                itemThreeName.clear();
                itemThreePrice.clear();

                menuScreenTitle.setText("2 Set");


                Cursor twoSetItems = db.get2SetItems();

                while (twoSetItems.moveToNext()){
                    if (twoSetItems.getString(0).equals("1") ||
                            twoSetItems.getString(0).equals("4")) {

                        itemOneId.add(allItems.getInt(0));
                        addItemImage(twoSetItems.getString(0));
                        itemOneName.add(twoSetItems.getString(1));
                        itemOnePrice.add(twoSetItems.getDouble(3));
                    }else if (twoSetItems.getString(0).equals("2") ||
                            twoSetItems.getString(0).equals("5")){

                        itemTwoId.add(allItems.getInt(0));
                        addItemImage(twoSetItems.getString(0));
                        itemTwoName.add(twoSetItems.getString(1));
                        itemTwoPrice.add(twoSetItems.getDouble(3));
                    }else if (twoSetItems.getString(0).equals("3") ||
                            twoSetItems.getString(0).equals("6")){

                        itemThreeId.add(allItems.getInt(0));
                        addItemImage(twoSetItems.getString(0));
                        itemThreeName.add(twoSetItems.getString(1));
                        itemThreePrice.add(twoSetItems.getDouble(3));
                    }
                }

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                        itemTwoId, itemTwoImage, itemTwoName, itemTwoPrice,
                        itemThreeId, itemThreeImage, itemThreeName, itemThreePrice,
                        getApplicationContext());
                recycleView.setAdapter(adapter);
                recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });
        plate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                itemTwoImage.clear();
                itemTwoName.clear();
                itemTwoPrice.clear();

                itemThreeImage.clear();
                itemThreeName.clear();
                itemThreePrice.clear();

                menuScreenTitle.setText("Plate");


                Cursor plateItems = db.getPlateItems();

                while (plateItems.moveToNext()){
                    if (plateItems.getString(0).equals("19") ||
                            plateItems.getString(0).equals("22")) {

                        itemOneId.add(allItems.getInt(0));
                        addItemImage(plateItems.getString(0));
                        itemOneName.add(plateItems.getString(1));
                        itemOnePrice.add(plateItems.getDouble(3));
                    }else if (plateItems.getString(0).equals("20") ||
                            plateItems.getString(0).equals("23")){

                        itemTwoId.add(allItems.getInt(0));
                        addItemImage(plateItems.getString(0));
                        itemTwoName.add(plateItems.getString(1));
                        itemTwoPrice.add(plateItems.getDouble(3));
                    }else if (plateItems.getString(0).equals("21") ||
                            plateItems.getString(0).equals("24")){

                        itemThreeId.add(allItems.getInt(0));
                        addItemImage(plateItems.getString(0));
                        itemThreeName.add(plateItems.getString(1));
                        itemThreePrice.add(plateItems.getDouble(3));
                    }
                }

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                        itemTwoId, itemTwoImage, itemTwoName, itemTwoPrice,
                        itemThreeId, itemThreeImage, itemThreeName, itemThreePrice,
                        getApplicationContext());
                recycleView.setAdapter(adapter);
                recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });
        bibimbap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                itemTwoImage.clear();
                itemTwoName.clear();
                itemTwoPrice.clear();

                itemThreeImage.clear();
                itemThreeName.clear();
                itemThreePrice.clear();

                menuScreenTitle.setText("Bibimbap");


                Cursor bibimbapItems = db.getBibimbapItems();

                while (bibimbapItems.moveToNext()){
                    if (bibimbapItems.getString(0).equals("12") ||
                            bibimbapItems.getString(0).equals("15") ||
                             bibimbapItems.getString(0).equals("18")){

                        itemOneId.add(allItems.getInt(0));
                        addItemImage(bibimbapItems.getString(0));
                        itemOneName.add(bibimbapItems.getString(1));
                        itemOnePrice.add(bibimbapItems.getDouble(3));
                    }else if (bibimbapItems.getString(0).equals("13") ||
                            bibimbapItems.getString(0).equals("16")){

                        itemTwoId.add(allItems.getInt(0));
                        addItemImage(bibimbapItems.getString(0));
                        itemTwoName.add(bibimbapItems.getString(1));
                        itemTwoPrice.add(bibimbapItems.getDouble(3));
                    }else if (bibimbapItems.getString(0).equals("14") ||
                            bibimbapItems.getString(0).equals("17")){

                        itemThreeId.add(allItems.getInt(0));
                        addItemImage(bibimbapItems.getString(0));
                        itemThreeName.add(bibimbapItems.getString(1));
                        itemThreePrice.add(bibimbapItems.getDouble(3));
                    }
                }

                itemTwoId.add(null);
                itemTwoImage.add(null);
                itemTwoName.add(null);
                itemTwoPrice.add(null);
                itemTwoId.add(null);
                itemTwoImage.add(null);
                itemTwoName.add(null);
                itemTwoPrice.add(null);

                itemThreeId.add(null);
                itemThreeImage.add(null);
                itemThreeName.add(null);
                itemThreePrice.add(null);
                itemThreeId.add(null);
                itemThreeImage.add(null);
                itemThreeName.add(null);
                itemThreePrice.add(null);

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                        itemTwoId, itemTwoImage, itemTwoName, itemTwoPrice,
                        itemThreeId, itemThreeImage, itemThreeName, itemThreePrice,
                        getApplicationContext());
                recycleView.setAdapter(adapter);
                recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });
        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                itemTwoImage.clear();
                itemTwoName.clear();
                itemTwoPrice.clear();

                itemThreeImage.clear();
                itemThreeName.clear();
                itemThreePrice.clear();

                menuScreenTitle.setText("Drinks");


                Cursor drinksItems = db.getDrinksItems();

                while (drinksItems.moveToNext()){
                    if (drinksItems.getString(0).equals("25") ||
                            drinksItems.getString(0).equals("28") ||
                            drinksItems.getString(0).equals("31")) {

                        itemOneId.add(allItems.getInt(0));
                        addItemImage(drinksItems.getString(0));
                        itemOneName.add(drinksItems.getString(1));
                        itemOnePrice.add(drinksItems.getDouble(3));
                    }else if (drinksItems.getString(0).equals("26") ||
                            drinksItems.getString(0).equals("29")){

                        itemTwoId.add(allItems.getInt(0));
                        addItemImage(drinksItems.getString(0));
                        itemTwoName.add(drinksItems.getString(1));
                        itemTwoPrice.add(drinksItems.getDouble(3));
                    }else if (drinksItems.getString(0).equals("27") ||
                            drinksItems.getString(0).equals("30")){

                        itemThreeId.add(allItems.getInt(0));
                        addItemImage(drinksItems.getString(0));
                        itemThreeName.add(drinksItems.getString(1));
                        itemThreePrice.add(drinksItems.getDouble(3));
                    }
                }

                itemTwoId.add(null);
                itemTwoImage.add(null);
                itemTwoName.add(null);
                itemTwoPrice.add(null);
                itemTwoId.add(null);
                itemTwoImage.add(null);
                itemTwoName.add(null);
                itemTwoPrice.add(null);

                itemThreeId.add(null);
                itemThreeImage.add(null);
                itemThreeName.add(null);
                itemThreePrice.add(null);
                itemThreeId.add(null);
                itemThreeImage.add(null);
                itemThreeName.add(null);
                itemThreePrice.add(null);

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                        itemTwoId, itemTwoImage, itemTwoName, itemTwoPrice,
                        itemThreeId, itemThreeImage, itemThreeName, itemThreePrice,
                        getApplicationContext());
                recycleView.setAdapter(adapter);
                recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });


        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, CartScreen.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addItemImage(String itemId){
        if (itemId.equals("1")){
            itemOneImage.add("https://www.theflavorbender.com/wp-content/uploads/2018/04/Galbi-Korean-BBQ-Short-Ribs-Feat2.jpg");
        }else if (itemId.equals("2")){
            itemTwoImage.add("https://assets.bonappetit.com/photos/57acd741f1c801a1038bc801/8:5/w_4839,h_3024,c_limit/basic-bulgogi.jpg");
        }else if (itemId.equals("3")){
            itemThreeImage.add("https://pinoybites.com/wp-content/uploads/2020/06/Snapseed-52-scaled.jpg");
        }else if (itemId.equals("4")){
            itemOneImage.add("https://www.thespruceeats.com/thmb/Z76Rt2kLpu7QguWrBd1tt5grKMU=/1500x1000/filters:fill(auto,1)/slow-roasted-pork-belly-crispy-skin-3059509-7_preview-5b16dcbb1d6404003605a196.jpeg");
        }else if (itemId.equals("5")){
            itemTwoImage.add("https://thewholecook.com/wp-content/uploads/2020/01/Sweet-Spicy-Chicken-Bites-by-The-Whole-Cook-horizontal-3.jpg");
        }else if (itemId.equals("6")){
            itemThreeImage.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/d05f9fcc7003488aa2840d15f4a7d470/BFV30005_SpicyKoreanPork-FB1080SQ_H264.jpg");
        }else if (itemId.equals("7")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2019/04/BulgogiDosirak.jpg");
        }else if (itemId.equals("8")){
            itemTwoImage.add("https://cdn.shopify.com/s/files/1/0486/0693/7249/products/20210403_163527_2048x2048.jpg?v=1617745215");
        }else if (itemId.equals("9")){
            itemThreeImage.add("https://www.koreanbapsang.com/wp-content/uploads/2010/05/DSC_1830.jpg");
        }else if (itemId.equals("10")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2018/10/Dosirak-Thumbnail-320x321.jpg");
        }else if (itemId.equals("11")){
            itemTwoImage.add("https://futuredish.com/wp-content/uploads/2020/10/Spicy-Garlic-Dakgangjeong.jpg");
        }else if (itemId.equals("12")){
            itemThreeImage.add("https://www.halfbakedharvest.com/wp-content/uploads/2018/01/Superfood-Bibimbap-with-Crispy-Tofu-1-500x500.jpg");
        }else if (itemId.equals("13")){
            itemOneImage.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/199535.jpg");
        }else if (itemId.equals("14")){
            itemTwoImage.add("https://www.justataste.com/wp-content/uploads/2016/03/teriyaki-chicken-stir-fry-noodles-recipe.jpg");
        }else if (itemId.equals("15")){
            itemThreeImage.add("https://www.koreanbapsang.com/wp-content/uploads/2011/04/DSC_0085-1-e1539058478522.jpg");
        }else if (itemId.equals("16")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2020/04/Soondubu-Hero-Image-3.jpg");
        }else if (itemId.equals("17")){
            itemTwoImage.add("https://futuredish.com/wp-content/uploads/2019/09/Ttukbaegi-Bulgogi.jpg");
        }else if (itemId.equals("18")){
            itemThreeImage.add("https://www.koreanbapsang.com/wp-content/uploads/2018/06/DSC_0672.jpg");
        }else if (itemId.equals("19")){
            itemOneImage.add("https://i1.wp.com/seonkyounglongest.com/wp-content/uploads/2019/09/Tangsuyuk-12.jpg?fit=1500%2C1000&ssl=1");
        }else if (itemId.equals("20")){
            itemTwoImage.add("https://twoplaidaprons.com/wp-content/uploads/2020/09/tteokbokki-top-down-view-of-tteokbokki-in-a-bowl-500x500.jpg");
        }else if (itemId.equals("21")){
            itemThreeImage.add("https://www.spicetheplate.com/wp-content/uploads/2017/02/Tuna-Kimchi-Fried-Rice-8-320x320.jpg");
        }else if (itemId.equals("22")){
            itemOneImage.add("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2016/12/21/0/FNK_Haemul-Pajeon-2_s4x3.jpg.rend.hgtvcom.616.462.suffix/1482354070138.jpeg");
        }else if (itemId.equals("23")){
            itemTwoImage.add("https://ryukoch.com/images/food-blog/jokbal-t.jpg");
        }else if (itemId.equals("24")){
            itemThreeImage.add("https://www.maangchi.com/wp-content/uploads/2019/11/vegankimchi-insta.jpg");
        }else if (itemId.equals("25")){
            itemOneImage.add("https://www.theflavorbender.com/wp-content/uploads/2018/04/Galbi-Korean-BBQ-Short-Ribs-Feat2.jpg");
        }else if (itemId.equals("26")){
            itemTwoImage.add("https://cdn11.bigcommerce.com/s-5bce5hukxg/images/stencil/1280x1280/products/2401/2989/coke355__27172.1580962278.jpg?c=2");
        }else if (itemId.equals("27")){
            itemThreeImage.add("https://supremepizzaonline.ca/wp-content/uploads/2017/08/7up-Can-update.jpg");
        }else if (itemId.equals("28")){
            itemOneImage.add("https://cdn.shopify.com/s/files/1/0036/4806/1509/products/305d0939eab876525890bc24f21124adbe4d1128_square119193_1.jpg?v=1611330354");
        }else if (itemId.equals("29")){
            itemTwoImage.add("https://www.lcbo.com/content/dam/lcbo/products/383059.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg");
        }else if (itemId.equals("30")){
            itemThreeImage.add("https://www.lcbo.com/content/dam/lcbo/products/546564.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg");
        }else if (itemId.equals("31")){
            itemOneImage.add("https://www.mybottleshop.com/media/catalog/product/cache/37a945ae749548a321239175b147c2a7/t/s/tsingtao_beer_bottles_12_pack_640ml.jpg");
        }
    }
}