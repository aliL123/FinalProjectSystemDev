package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PaymentMethodScreen extends AppCompatActivity {

    ImageView paymentBackButton;

    Button cardPayment, cashPayment;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_screen);

        db = new DatabaseHelper(this);

        paymentBackButton = findViewById(R.id.paymentScreenBackImageButton);
        cardPayment = findViewById(R.id.paymentCardButton);
        cashPayment = findViewById(R.id.paymentCashButton);

        paymentBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentMethodScreen.this, CartScreen.class);
                startActivity(intent);
            }
        });

        cardPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor lastCustomer = db.findLastCustomer();
                lastCustomer.moveToNext();

                db.updateCustomer(lastCustomer.getString(0), "card");

                Intent intent = new Intent(PaymentMethodScreen.this, ReceiptScreen.class);
                startActivity(intent);
            }
        });

        cashPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor lastCustomer = db.findLastCustomer();
                lastCustomer.moveToNext();

                db.updateCustomer(lastCustomer.getString(0), "cash");

                Intent intent = new Intent(PaymentMethodScreen.this, ReceiptScreen.class);
                startActivity(intent);
            }
        });
    }
}