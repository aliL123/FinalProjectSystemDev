package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AdminDeleteItemActivity extends AppCompatActivity {
    DatabaseHelper mydb;

    EditText itemEditText;
    Button deleteItem, search;
    ImageButton goBack;
    ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_item);



        itemEditText = findViewById(R.id.itemEditText);
        itemList = findViewById(R.id.itemList);
        deleteItem = findViewById(R.id.deleteItem);
        search = findViewById(R.id.search);
        goBack = findViewById(R.id.goBack);

        mydb = new DatabaseHelper(this);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDeleteItemActivity.this, AdminHomeActivity.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEditText.getText().toString().trim();

                SimpleCursorAdapter simpleCursorAdapter = (SimpleCursorAdapter) mydb.getAllItemsByName(item);
                itemList.setAdapter(simpleCursorAdapter);
                itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                        String name = cursor.getColumnName(1);
                        cursor.

                    }
                });

            }
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = itemEditText.getText().toString().trim();

                int id = mydb.getItemName(item);
                mydb.deleteItem(id);

                //display related search items in listview


            }
        });
    }

}