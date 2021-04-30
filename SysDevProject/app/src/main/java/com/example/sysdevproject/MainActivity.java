package com.example.sysdevproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button frButton, enButton;
    ImageButton settingButton;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                        finish();
                        //Intent intent = new Intent(MainActivity.this, Menu.this);
                        //startActivity(intent);
                    }
                }).setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        //Intent intent = new Intent(MainActivity.this, Menu.this);
                        //startActivity(intent);
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
                                finish();
                                //Intent intent = new Intent(MainActivity.this, Menu.this);
                                //startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        //Intent intent = new Intent(MainActivity.this, Menu.this);
                        //startActivity(intent);
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


    }
}