package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class CartScreen extends AppCompatActivity {

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<String> itemQuantities = new ArrayList<>();
    RecyclerView recyclerView;

    Button checkoutButton, cancelButton;

    ImageButton cartBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);

        checkoutButton = findViewById(R.id.cartCheckoutButton);
        cancelButton = findViewById(R.id.cartCancelButton);

        cartBackButton = findViewById(R.id.cartBackImageButton);

        itemNames.add("Item Name 1");
        itemQuantities.add("Quantity1");

        itemNames.add("Item Name 2");
        itemQuantities.add("Quantity2");

        itemNames.add("Item Name 3");
        itemQuantities.add("Quantity3");

        itemNames.add("Item Name 4");
        itemQuantities.add("Quantity4");

        itemNames.add("Item Name 5");
        itemQuantities.add("Quantity5");


        recyclerView = findViewById(R.id.cartScreenRecyclerView);
        CartAdapter cartAdapter = new CartAdapter(itemNames, itemQuantities, this);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartScreen.this, PaymentMethodScreen.class);
                startActivity(intent);
            }
        });

        cartBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartScreen.this, MenuScreen.class);
                startActivity(intent);
            }
        });
    }
}