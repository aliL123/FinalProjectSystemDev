package com.example.sysdevproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceiptScreen extends AppCompatActivity {

    DatabaseHelper db;

    Button printReceipt;

    TextView subTotal, gst, qst, finalPrice;


    ArrayList<String> receiptItemImages = new ArrayList<>();
    ArrayList<String> receiptItemNames= new ArrayList<>();
    ArrayList<String> receiptItemPrices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_screen);

        db = new DatabaseHelper(this);

        printReceipt = findViewById(R.id.printReceiptButton);

        subTotal = findViewById(R.id.receiptSubTotalPrice);
        gst = findViewById(R.id.receiptGSTPrice);
        qst = findViewById(R.id.receiptQSTPrice);
        finalPrice = findViewById(R.id.receiptFinalPriceValue);


        RecyclerView recycleView = findViewById(R.id.receiptRecyclerView);
        ReceiptAdapter adapter = new ReceiptAdapter(receiptItemImages, receiptItemNames, receiptItemPrices, this);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}