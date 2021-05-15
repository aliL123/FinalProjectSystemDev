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


        String itemColumn1 = "1";
        String itemColumn2 = "2";
        String itemColumn3 = "3";

        while (allItems.moveToNext()) {
            //Toast.makeText(MenuScreen.this, allItems.getString(0) + allItems.getString(1) + allItems.getDouble(3), Toast.LENGTH_SHORT).show();

            itemOneId.add(allItems.getInt(0));
            addItemImage(allItems.getString(0));
            itemOneName.add(allItems.getString(1));
            itemOnePrice.add(allItems.getDouble(3));
        }


        RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
        MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                this);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));


        combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                menuScreenTitle.setText("Combo");

                Cursor comboItems = db.getComboItems();

                while (comboItems.moveToNext()) {

                    itemOneId.add(comboItems.getInt(0));
                    addItemImage(comboItems.getString(0));
                    itemOneName.add(comboItems.getString(1));
                    itemOnePrice.add(comboItems.getDouble(3));


                    RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                    MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
                            getApplicationContext());
                    recycleView.setAdapter(adapter);
                    recycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }
        });
        twoSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOneImage.clear();
                itemOneName.clear();
                itemOnePrice.clear();

                menuScreenTitle.setText("2 Set");

                Cursor twoSetItems = db.get2SetItems();

                while (twoSetItems.moveToNext()){

                    itemOneId.add(twoSetItems.getInt(0));
                    addItemImage(twoSetItems.getString(0));
                    itemOneName.add(twoSetItems.getString(1));
                    itemOnePrice.add(twoSetItems.getDouble(3));
                }

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
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

                menuScreenTitle.setText("Plate");

                Cursor plateItems = db.getPlateItems();

                while (plateItems.moveToNext()){

                    itemOneId.add(plateItems.getInt(0));
                    addItemImage(plateItems.getString(0));
                    itemOneName.add(plateItems.getString(1));
                    itemOnePrice.add(plateItems.getDouble(3));
                }

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
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

                menuScreenTitle.setText("Bibimbap");


                Cursor bibimbapItems = db.getBibimbapItems();

                String bibimbapColumn1 = "12";
                String bibimbapColumn2 = "13";
                String bibimbapColumn3 = "14";

                while (bibimbapItems.moveToNext()){

                    itemOneId.add(bibimbapItems.getInt(0));
                    addItemImage(bibimbapItems.getString(0));
                    itemOneName.add(bibimbapItems.getString(1));
                    itemOnePrice.add(bibimbapItems.getDouble(3));
                }

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
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

                menuScreenTitle.setText("Drinks");


                Cursor drinksItems = db.getDrinksItems();

                while (drinksItems.moveToNext()){
                    itemOneId.add(drinksItems.getInt(0));
                    addItemImage(drinksItems.getString(0));
                    itemOneName.add(drinksItems.getString(1));
                    itemOnePrice.add(drinksItems.getDouble(3));
                }

                RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
                MenuAdapter adapter = new MenuAdapter(itemOneId, itemOneImage, itemOneName, itemOnePrice,
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
            itemOneImage.add("https://assets.bonappetit.com/photos/57acd741f1c801a1038bc801/8:5/w_4839,h_3024,c_limit/basic-bulgogi.jpg");
        }else if (itemId.equals("3")){
            itemOneImage.add("https://pinoybites.com/wp-content/uploads/2020/06/Snapseed-52-scaled.jpg");
        }else if (itemId.equals("4")){
            itemOneImage.add("https://www.thespruceeats.com/thmb/Z76Rt2kLpu7QguWrBd1tt5grKMU=/1500x1000/filters:fill(auto,1)/slow-roasted-pork-belly-crispy-skin-3059509-7_preview-5b16dcbb1d6404003605a196.jpeg");
        }else if (itemId.equals("5")){
            itemOneImage.add("https://thewholecook.com/wp-content/uploads/2020/01/Sweet-Spicy-Chicken-Bites-by-The-Whole-Cook-horizontal-3.jpg");
        }else if (itemId.equals("6")){
            itemOneImage.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/d05f9fcc7003488aa2840d15f4a7d470/BFV30005_SpicyKoreanPork-FB1080SQ_H264.jpg");
        }else if (itemId.equals("7")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2019/04/BulgogiDosirak.jpg");
        }else if (itemId.equals("8")){
            itemOneImage.add("https://cdn.shopify.com/s/files/1/0486/0693/7249/products/20210403_163527_2048x2048.jpg?v=1617745215");
        }else if (itemId.equals("9")){
            itemOneImage.add("https://www.koreanbapsang.com/wp-content/uploads/2010/05/DSC_1830.jpg");
        }else if (itemId.equals("10")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2018/10/Dosirak-Thumbnail-320x321.jpg");
        }else if (itemId.equals("11")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2020/10/Spicy-Garlic-Dakgangjeong.jpg");
        }else if (itemId.equals("12")){
            itemOneImage.add("https://www.halfbakedharvest.com/wp-content/uploads/2018/01/Superfood-Bibimbap-with-Crispy-Tofu-1-500x500.jpg");
        }else if (itemId.equals("13")){
            itemOneImage.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/199535.jpg");
        }else if (itemId.equals("14")){
            itemOneImage.add("https://www.justataste.com/wp-content/uploads/2016/03/teriyaki-chicken-stir-fry-noodles-recipe.jpg");
        }else if (itemId.equals("15")){
            itemOneImage.add("https://www.koreanbapsang.com/wp-content/uploads/2011/04/DSC_0085-1-e1539058478522.jpg");
        }else if (itemId.equals("16")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2020/04/Soondubu-Hero-Image-3.jpg");
        }else if (itemId.equals("17")){
            itemOneImage.add("https://futuredish.com/wp-content/uploads/2019/09/Ttukbaegi-Bulgogi.jpg");
        }else if (itemId.equals("18")){
            itemOneImage.add("https://www.koreanbapsang.com/wp-content/uploads/2018/06/DSC_0672.jpg");
        }else if (itemId.equals("19")){
            itemOneImage.add("https://i1.wp.com/seonkyounglongest.com/wp-content/uploads/2019/09/Tangsuyuk-12.jpg?fit=1500%2C1000&ssl=1");
        }else if (itemId.equals("20")){
            itemOneImage.add("https://twoplaidaprons.com/wp-content/uploads/2020/09/tteokbokki-top-down-view-of-tteokbokki-in-a-bowl-500x500.jpg");
        }else if (itemId.equals("21")){
            itemOneImage.add("https://www.spicetheplate.com/wp-content/uploads/2017/02/Tuna-Kimchi-Fried-Rice-8-320x320.jpg");
        }else if (itemId.equals("22")){
            itemOneImage.add("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2016/12/21/0/FNK_Haemul-Pajeon-2_s4x3.jpg.rend.hgtvcom.616.462.suffix/1482354070138.jpeg");
        }else if (itemId.equals("23")){
            itemOneImage.add("https://ryukoch.com/images/food-blog/jokbal-t.jpg");
        }else if (itemId.equals("24")){
            itemOneImage.add("https://www.maangchi.com/wp-content/uploads/2019/11/vegankimchi-insta.jpg");
        }else if (itemId.equals("25")){
            itemOneImage.add("https://cdn11.bigcommerce.com/s-5bce5hukxg/images/stencil/1280x1280/products/2401/2989/coke355__27172.1580962278.jpg?c=2");
        }else if (itemId.equals("26")){
            itemOneImage.add("https://cdn11.bigcommerce.com/s-5bce5hukxg/images/stencil/1280x1280/products/3528/2991/cokdiet__80073.1580962366.jpg?c=2");
        }else if (itemId.equals("27")){
            itemOneImage.add("https://supremepizzaonline.ca/wp-content/uploads/2017/08/7up-Can-update.jpg");
        }else if (itemId.equals("28")){
            itemOneImage.add("https://cdn.shopify.com/s/files/1/0036/4806/1509/products/305d0939eab876525890bc24f21124adbe4d1128_square119193_1.jpg?v=1611330354");
        }else if (itemId.equals("29")){
            itemOneImage.add("https://www.lcbo.com/content/dam/lcbo/products/383059.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg");
        }else if (itemId.equals("30")){
            itemOneImage.add("https://www.lcbo.com/content/dam/lcbo/products/546564.jpg/jcr:content/renditions/cq5dam.web.1280.1280.jpeg");
        }else if (itemId.equals("31")){
            itemOneImage.add("https://www.mybottleshop.com/media/catalog/product/cache/37a945ae749548a321239175b147c2a7/t/s/tsingtao_beer_bottles_12_pack_640ml.jpg");
        }
    }
}