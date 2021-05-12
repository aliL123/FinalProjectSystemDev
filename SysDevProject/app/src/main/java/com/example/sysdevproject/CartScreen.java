package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class CartScreen extends AppCompatActivity {

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemQuantities = new ArrayList<>();
    RecyclerView recyclerView;

    Button checkoutButton, cancelButton;

    ImageButton cartBackButton;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);

        db = new DatabaseHelper(this);

        checkoutButton = findViewById(R.id.cartCheckoutButton);
        cancelButton = findViewById(R.id.cartCancelButton);

        cartBackButton = findViewById(R.id.cartBackImageButton);

        Cursor cartItems = db.getItemFromCart(MainActivity.customerId);

        while (cartItems.moveToNext()){
            Item item = db.getItem(cartItems.getInt(2));

            itemNames.add(item.getName());
            itemQuantities.add(cartItems.getInt(3));
        }


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