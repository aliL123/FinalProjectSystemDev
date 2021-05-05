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

import java.util.ArrayList;

public class MenuScreen extends AppCompatActivity {

    DatabaseHelper db;

    ArrayList<String> itemOneImage = new ArrayList<>();
    ArrayList<String> itemOneName = new ArrayList<>();
    ArrayList<String> itemOneDescription = new ArrayList<>();
    ArrayList<Double> itemOnePrice = new ArrayList<>();
    ArrayList<Integer> itemOneIsDrink = new ArrayList<>();
    ArrayList<Integer> itemOneIsAvailable = new ArrayList<>();

    ArrayList<String> itemTwoImage = new ArrayList<>();
    ArrayList<String> itemTwoName = new ArrayList<>();
    ArrayList<String> itemTwoDescription = new ArrayList<>();
    ArrayList<Double> itemTwoPrice = new ArrayList<>();
    ArrayList<Integer> itemTwoIsDrink = new ArrayList<>();
    ArrayList<Integer> itemTwoIsAvailable = new ArrayList<>();

    ArrayList<String> itemThreeImage = new ArrayList<>();
    ArrayList<String> itemThreeName = new ArrayList<>();
    ArrayList<String> itemThreeDescription = new ArrayList<>();
    ArrayList<Double> itemThreePrice = new ArrayList<>();
    ArrayList<Integer> itemThreeIsDrink = new ArrayList<>();
    ArrayList<Integer> itemThreeIsAvailable = new ArrayList<>();

    Button combo, twoSet, plate, bibimbap, drinks;
    Button viewCart, cancel;
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

        viewCart = findViewById(R.id.menuCheckoutButton);
        cancel = findViewById(R.id.menuCancelOrderButton);

        Cursor allItems = db.getAllItems();

        itemOneImage.add("https://www.theflavorbender.com/wp-content/uploads/2018/04/Galbi-Korean-BBQ-Short-Ribs-Feat2.jpg");
        itemOneName.add("LA Galbi");
        itemOnePrice.add(38.99);

        itemTwoImage.add("https://www.justonecookbook.com/wp-content/uploads/2020/06/Bulgogi-Korean-Grilled-Beef-9238-I.jpg");
        itemTwoName.add("Bulgogi");
        itemTwoPrice.add(38.99);

        itemThreeImage.add("https://i.pinimg.com/originals/da/88/12/da8812565e031c79c33693115018b040.jpg");
        itemThreeName.add("Pork BBQ");
        itemThreePrice.add(34.99);


        itemOneImage.add("https://cdn.greatlifepublishing.net/wp-content/uploads/sites/2/2018/12/21230114/Crispy-Roast-Pork-Belly-2.jpg");
        itemOneName.add("Pork Belly");
        itemOnePrice.add(34.99);

        itemTwoImage.add("https://thewholecook.com/wp-content/uploads/2020/01/Sweet-Spicy-Chicken-Bites-by-The-Whole-Cook-horizontal-3.jpg");
        itemTwoName.add("Spicy Chicken");
        itemTwoPrice.add(37.99);

        itemThreeImage.add("https://img.buzzfeed.com/thumbnailer-prod-us-east-1/d05f9fcc7003488aa2840d15f4a7d470/BFV30005_SpicyKoreanPork-FB1080SQ_H264.jpg");
        itemThreeName.add("Spicy Pork");
        itemThreePrice.add(34.99);


        RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
        MenuAdapter adapter = new MenuAdapter(itemOneImage, itemOneName, itemOnePrice,
                itemTwoImage, itemTwoName, itemTwoPrice,
                itemThreeImage, itemThreeName, itemThreePrice,
                this);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));


        combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuScreenTitle.setText("Combo");
            }
        });
        twoSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuScreenTitle.setText(" 2 set items");
            }
        });
        plate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuScreenTitle.setText("Plate");
            }
        });
        bibimbap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuScreenTitle.setText("Bibimbap");
            }
        });
        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuScreenTitle.setText("Drinks");
            }
        });


        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuScreen.this, CartScreen.class);
                startActivity(intent);
            }
        });
    }
}