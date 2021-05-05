package com.example.sysdevproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button frButton, enButton;
    ImageButton settingButton;
    AlertDialog.Builder builder;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);


        frButton = findViewById(R.id.frenchbtn);
        enButton = findViewById(R.id.englishbtn);
        settingButton = (ImageButton) findViewById(R.id.settingImageButton);
        builder = new AlertDialog.Builder(this);

        frButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Verification de l'age");
                builder.setMessage("Avez-Vous plus de 18 ans?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                        Intent intent = new Intent(MainActivity.this, MenuScreen.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent intent = new Intent(MainActivity.this, MenuScreen.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        enButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Age Verification");
                builder.setMessage("Are you over 18?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, MenuScreen.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MenuScreen.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)  {

                startActivity(new Intent(MainActivity.this, LoginAdminActivity.class));
            }
        });

        db.insertItem("LA Galbi", "", 38.99, 0, 1, "2 Set");
        db.insertItem("Bulgogi", "", 38.99, 0, 1, "2 Set");
        db.insertItem("Pork BBQ", "", 34.99, 0, 1, "2 Set");
        db.insertItem("Pork Belly", "", 34.99, 0, 1, "2 Set");
        db.insertItem("Spicy Chicken", "", 37.99, 0, 1, "2 Set");
        db.insertItem("Spicy Pork", "", 34.99, 0, 1, "2 Set");

        db.insertItem("Bulgogi Doshirak", "", 18.99, 0, 1, "Combo");
        db.insertItem("LA Galbi Doshirak", "", 18.99, 0, 1, "Combo");
        db.insertItem("Spicy Pork Doshirak", "", 16.99, 0, 1, "Combo");
        db.insertItem("Pork BBQ Doshirak", "", 16.99, 0, 1, "Combo");
        db.insertItem("Spicy Chicken Doshirak", "", 17.99, 0, 1, "Combo");

        db.insertItem("Fried Tofu Bibimbap", "", 12.99, 0, 1, "Bibimbap");
        db.insertItem("Bulgogi Bibimbap", "", 14.99, 0, 1, "Bibimbap");
        db.insertItem("Chicken Teriyaki Bibimbap", "", 13.99, 0, 1, "Bibimbap");

        db.insertItem("Japchae", "", 12.99, 0, 1, "Plate");
        db.insertItem("Jjajangmyun", "", 12.99, 0, 1, "Plate");
        db.insertItem("Seafood Teriyaki Stir-fried Noodles", "", 13.99, 0, 1, "Plate");
        db.insertItem("Korean spicy noodles soup", "", 7.99, 0, 1, "Plate");

        db.insertItem("Spicy soft tofu stew with seafood", "", 13.99, 0, 1, "Plate");
        db.insertItem("Ddukbaegi Bulgogi", "", 14.99, 0, 1, "Plate");
        db.insertItem("Soybean paste stew", "", 12.99, 0, 1, "Plate");

        db.insertItem("Tangsuyuk", "", 15.99, 0, 1, "Plate");
        db.insertItem("Tteok-bokki", "", 12.99, 0, 1, "Plate");
        db.insertItem("Kimchi Fried Rice with Tuna", "", 12.99, 0, 1, "Plate");
        db.insertItem("Seafood Pancake", "", 14.99, 0, 1, "Plate");
        db.insertItem("Tonkatsu Doshirak", "", 15.99, 0, 1, "Plate");
        db.insertItem("Yobuchobap", "", 6.99, 0, 1, "Plate");
        db.insertItem("Jokbal (Pigs' Feet)", "", 29.99, 0, 1, "Plate");
        db.insertItem("Steamed rice", "", 2.99, 0, 1, "Plate");
        db.insertItem("Lettuce", "", 2.99, 0, 1, "Plate");
        db.insertItem("Pickled Radish", "", 2.99, 0, 1, "Plate");
        db.insertItem("Seasoned Green Onions", "", 2.99, 0, 1, "Plate");
        db.insertItem("Handmade Kimchi", "", 4.99, 0, 1, "Plate");

        db.insertItem("Crispy Fried Chicken Drumsticks", "", 5.99, 0, 1, "Plate");
        db.insertItem("Crispy Fried Chicken Wings and French Fries", "", 12.99, 0, 1, "Plate");

        db.insertItem("Milkis", "", 3.50, 1, 1, "Drinks");
        db.insertItem("Sikhye (Rice punch)", "", 3.00, 1, 1, "Drinks");
        db.insertItem("Bong Bong (Grape juice)", "", 3.00, 1, 1, "Drinks");
        db.insertItem("Aloe drink", "", 3.50, 1, 1, "Drinks");

        db.insertItem("Coke", "", 2.50, 1, 1, "Drinks");
        db.insertItem("Diet Coke", "", 2.50, 1, 1, "Drinks");
        db.insertItem("7up", "", 2.50, 1, 1, "Drinks");
        db.insertItem("Crush orange", "", 2.50, 1, 1, "Drinks");
        db.insertItem("Canada Dry", "", 2.50, 1, 1, "Drinks");

        db.insertItem("Sleeman Beer", "", 3.99, 1, 1, "Drinks");
        db.insertItem("Sapporo Beer", "", 4.99, 1, 1, "Drinks");
        db.insertItem("Tsingtao bBeer", "", 4.99, 1, 1, "Drinks");
        db.insertItem("Original Soju", "", 14.99, 1, 1, "Drinks");
        db.insertItem("Fruit Soju", "", 14.99, 1, 1, "Drinks");
        db.insertItem("Makguli (Rice wine)", "", 14.99, 1, 1, "Drinks");
        db.insertItem("Maehwasu (Plume wine)", "", 12.99, 1, 1, "Drinks");
    }

    public  void setLocal(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());

    }
}