package com.example.sysdevproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button frButton, enButton;
    ImageButton settingButton;
    AlertDialog.Builder builder;

    DatabaseHelper db;

    static int customerId = 0;

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
                        db.insertCustomer(1, "");

                        Cursor currentCustomer = db.findLastCustomer();
                        currentCustomer.moveToNext();

                        customerId = currentCustomer.getInt(0);

                        setLocal(MainActivity.this, "fr");

                        Intent intent = new Intent(MainActivity.this, MenuScreen.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertCustomer(0, "");

                        Cursor currentCustomer = db.findLastCustomer();
                        currentCustomer.moveToNext();

                        customerId = currentCustomer.getInt(0);

                        setLocal(MainActivity.this, "fr");


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
                                db.insertCustomer(1, "");

                                Cursor currentCustomer = db.findLastCustomer();
                                currentCustomer.moveToNext();

                                customerId = currentCustomer.getInt(0);
                                Intent intent = new Intent(MainActivity.this, MenuScreen.class);

                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.insertCustomer(0, "");

                                Cursor currentCustomer = db.findLastCustomer();
                                currentCustomer.moveToNext();

                                customerId = currentCustomer.getInt(0);
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

        Cursor allItems = db.getAllItems();

        if (allItems.getCount() == 0) {

            db.insertItem("LA Galbi", "", 38.99, 0, 1, "2set");
            db.insertItem("Bulgogi", "", 38.99, 0, 1, "2set");
            db.insertItem("Pork BBQ", "", 34.99, 0, 1, "2set");
            db.insertItem("Pork Belly", "", 34.99, 0, 1, "2set");
            db.insertItem("Spicy Chicken", "", 37.99, 0, 1, "2set");
            db.insertItem("Spicy Pork", "", 34.99, 0, 1, "2set");

            db.insertItem("Bulgogi Doshirak", "", 18.99, 0, 1, "combo");
            db.insertItem("LA Galbi Doshirak", "", 18.99, 0, 1, "combo");
            db.insertItem("Spicy Pork Doshirak", "", 16.99, 0, 1, "combo");
            db.insertItem("Pork BBQ Doshirak", "", 16.99, 0, 1, "combo");
            db.insertItem("Spicy Chicken Doshirak", "", 17.99, 0, 1, "combo");

            db.insertItem("Fried Tofu Bibimbap", "", 12.99, 0, 1, "bibimbap");
            db.insertItem("Chicken Teriyaki Bibimbap", "", 13.99, 0, 1, "bibimbap");
            db.insertItem("Seafood Teriyaki Stir-fried Noodles", "", 13.99, 0, 1, "bibimbap");
            db.insertItem("Korean spicy noodles soup", "", 7.99, 0, 1, "bibimbap");
            db.insertItem("Spicy soft tofu stew with seafood", "", 13.99, 0, 1, "bibimbap");
            db.insertItem("Ddukbaegi Bulgogi", "", 14.99, 0, 1, "bibimbap");
            db.insertItem("Soybean paste stew", "", 12.99, 0, 1, "bibimbap");

            db.insertItem("Tangsuyuk", "", 15.99, 0, 1, "plate");
            db.insertItem("Tteok-bokki", "", 12.99, 0, 1, "plate");
            db.insertItem("Kimchi Fried Rice with Tuna", "", 12.99, 0, 1, "plate");
            db.insertItem("Seafood Pancake", "", 14.99, 0, 1, "plate");
            db.insertItem("Jokbal (Pigs' Feet)", "", 29.99, 0, 1, "plate");
            db.insertItem("Handmade Kimchi", "", 4.99, 0, 1, "plate");

            db.insertItem("Coke", "", 2.50, 1, 1, "drinks");
            db.insertItem("Diet Coke", "", 2.50, 1, 1, "drinks");
            db.insertItem("7up", "", 2.50, 1, 1, "drinks");
            db.insertItem("Crush orange", "", 2.50, 1, 1, "drinks");
            db.insertItem("Sleeman Beer", "", 3.99, 1, 1, "drinks");
            db.insertItem("Sapporo Beer", "", 4.99, 1, 1, "drinks");
            db.insertItem("Tsingtao bBeer", "", 4.99, 1, 1, "drinks");
        }

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