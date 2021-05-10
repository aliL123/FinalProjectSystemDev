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
import android.widget.Toast;

import java.util.ArrayList;

public class AdminDeleteItemActivity extends AppCompatActivity {
    DatabaseHelper mydb;

    EditText itemEditText;
    Button deleteItem, search;
    ImageButton goBack;

    ListView itemList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_item);

        itemEditText = findViewById(R.id.itemEditText);
        deleteItem = findViewById(R.id.deleteItem);
        search = findViewById(R.id.search);
        goBack = findViewById(R.id.goBack);

        mydb = new DatabaseHelper(this);

        itemList = findViewById(R.id.itemList);
        listItem = new ArrayList<>();

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
                viewData(item);
            }
        });

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = itemList.getItemAtPosition(1).toString();
                deleteItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = mydb.getItemName(item);
                        mydb.deleteItem(id);

                    }
                });
            }
        });

    }

    public void viewData(String itemName){
        Cursor cursor = mydb.getAllItemsByName(itemName);

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            itemList.setAdapter(adapter);
        }
    }

}