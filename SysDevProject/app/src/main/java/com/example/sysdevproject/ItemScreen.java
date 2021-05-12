package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ItemScreen extends AppCompatActivity {

    ImageView itemImage;

    TextView itemPrice, itemDescription;

    Button addCart;
    ImageButton backToCart;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);

        db = new DatabaseHelper(this);

        itemImage = findViewById(R.id.itemImage);
        itemPrice = findViewById(R.id.itemPrice);
        itemDescription = findViewById(R.id.itemDescription);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String image = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0);

        Picasso.get()
                .load(image)
                .into(itemImage);

        itemPrice.setText("$"+price);

        addCart = findViewById(R.id.itemAddCartButton);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean addedToCart = db.addToCart(0, MainActivity.customerId, id, 1);

                if (addedToCart){
                    Toast.makeText(ItemScreen.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ItemScreen.this, "Error", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(ItemScreen.this, MenuScreen.class));
            }
        });

        backToCart = findViewById(R.id.itemScreenBackImageButton);
        backToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemScreen.this, MenuScreen.class));
            }
        });
    }
}