package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class AdminDeleteItemActivity extends AppCompatActivity {

    EditText itemEditText;
    Button deleteItem;
    ImageButton goBack;
    ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_item);

        itemEditText = findViewById(R.id.itemEditText);
        itemList = findViewById(R.id.itemList);
        deleteItem = findViewById(R.id.deleteItem);
        goBack = findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDeleteItemActivity.this, AdminHomeActivity.class));
            }
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEditText.getText().toString().trim();

                //display related search items in listview

                // delete item

            }
        });
    }
}