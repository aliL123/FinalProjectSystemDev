package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminUpdateActivity extends AppCompatActivity {

    DatabaseHelper mydb;

    EditText itemEditText;
    Button updateItem, search;
    ImageButton goBack;

    ListView itemList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        mydb = new DatabaseHelper(this);

        itemEditText = findViewById(R.id.itemEditText);
        search = findViewById(R.id.search);
        itemList = findViewById(R.id.itemUpdateList);
        listItem = new ArrayList<>();

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

                String item = itemList.getItemAtPosition(position).toString();
                updateItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = mydb.getItemName(item);
                        Item updateItem = mydb.getItem(id);

                        if (updateItem.getAvailable() == 0){
                            boolean isUpdated = mydb.updateItem(String.valueOf(id), 1);

                            if (isUpdated == true){
                                Toast.makeText(AdminUpdateActivity.this, "Item Updated", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(AdminUpdateActivity.this, "Item not Updated", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            boolean isUpdated = mydb.updateItem(String.valueOf(id), 0);

                            if (isUpdated == true){
                                Toast.makeText(AdminUpdateActivity.this, "Item Updated", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(AdminUpdateActivity.this, "Item not Updated", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });

        updateItem = findViewById(R.id.updateItem);

        updateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminUpdateActivity.this, AdminHomeActivity.class));
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