package com.example.sysdevproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder>{

    ArrayList<String> receiptImages = new ArrayList<>();
    ArrayList<String> receiptItemsNames = new ArrayList<>();
    ArrayList<String> receiptItemsPrices = new ArrayList<>();
    Context mContext;

    public ReceiptAdapter(ArrayList<String> image, ArrayList<String> itemName, ArrayList<String> itemPrice, Context mContext) {
        this.receiptImages = image;
        this.receiptItemsNames = itemName;
        this.receiptItemsPrices = itemPrice;

        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ReceiptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.ViewHolder holder, int position) {
        Picasso.get()
                .load(receiptImages.get(position))
                .into(holder.receiptItemImage);

        holder.receiptItemName.setText(receiptItemsNames.get(position));
        holder.receiptItemPrice.setText(receiptItemsPrices.get(position));
    }

    @Override
    public int getItemCount() {
        return receiptImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView receiptItemImage;
        TextView receiptItemName, receiptItemPrice;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            receiptItemImage = itemView.findViewById(R.id.receiptItemImage);
            receiptItemName = itemView.findViewById(R.id.receiptItemName);
            receiptItemPrice = itemView.findViewById(R.id.receiptItemPrice);
            parentLayout = itemView.findViewById(R.id.parent);
        }
    }
}
