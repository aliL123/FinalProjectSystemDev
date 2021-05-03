package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        db = new DatabaseHelper(this);

        Cursor allItems = db.getAllItems();

        itemOneImage.add("https://www.seriouseats.com/2019/07/20190619-korean-bbq-kalbi-beef-short-ribs-vicky-wasik-21-1500x1125.jpg");
        itemOneName.add("LA Galbi");
        itemOnePrice.add(38.99);

        itemTwoImage.add("https://www.justonecookbook.com/wp-content/uploads/2020/06/Bulgogi-Korean-Grilled-Beef-9238-I.jpg");
        itemTwoName.add("Bulgogi");
        itemTwoPrice.add(38.99);

        itemThreeImage.add("https://i.pinimg.com/originals/da/88/12/da8812565e031c79c33693115018b040.jpg");
        itemThreeName.add("Pork BBQ");
        itemThreePrice.add(34.99);


        RecyclerView recycleView = findViewById(R.id.menuRecyclerView);
        MenuAdapter adapter = new MenuAdapter(itemOneImage, itemOneName, itemOnePrice,
                itemTwoImage, itemTwoName, itemTwoPrice,
                itemThreeImage, itemThreeName, itemThreePrice,
                this);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

    }
}