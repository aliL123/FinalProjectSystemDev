package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminAddItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editTextItemName, editTextPrice, editTextDescription;
    Button saveBtn;
    Spinner spinner;
    DatabaseHelper mydb;
    String category;
    RadioGroup radioGroupAble, radioGroupDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);
        editTextItemName = findViewById(R.id.editTextItemName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        saveBtn = findViewById(R.id.itemSaveButton);
        spinner = findViewById(R.id.spinnerCategory);
        radioGroupAble = findViewById(R.id.radioGroup);
        radioGroupDrink = findViewById(R.id.radioGroupDrink);
        mydb = new DatabaseHelper(this);
        ArrayAdapter<CharSequence> dataAdapter =  ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            //nsertItem(String name, String description, Double price, int is_drink, int available, String category)
            @Override
            public void onClick(View v) {
                int radioId = radioGroupAble.getCheckedRadioButtonId();
                int radioId2 = radioGroupDrink.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioId);
                RadioButton radioButtonDrink = findViewById(radioId2);
                int available = 0;
                int drink = 0;
                if(radioButton.getText().equals("YES"))
                    available = 1;

                if (radioButtonDrink.getText().equals("Drink"))
                    drink = 1;
                boolean isAdded = mydb.insertItem(editTextItemName.getText().toString(),
                        editTextDescription.getText().toString(),
                        Double.parseDouble(editTextPrice.getText().toString()), drink, available, category);
                if(isAdded) {
                    Toast.makeText(AdminAddItemActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(AdminAddItemActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}