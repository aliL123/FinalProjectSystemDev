package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemScreen extends AppCompatActivity {

    ImageView itemImage;

    TextView itemPrice, itemDescription;

    Button addCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);

        itemImage = findViewById(R.id.itemImage);
        itemPrice = findViewById(R.id.itemPrice);
        itemDescription = findViewById(R.id.itemDescription);

        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        double price = Double.parseDouble(intent.getStringExtra("price"));

        Picasso.get()
                .load(image)
                .into(itemImage);

        itemPrice.setText(price+"");

        addCart = findViewById(R.id.itemAddCartButton);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}