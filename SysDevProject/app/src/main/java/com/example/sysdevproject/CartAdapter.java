package com.example.sysdevproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemQuantities = new ArrayList<>();

    Context mContext;

    public CartAdapter(ArrayList<String> itemNames,
                       ArrayList<Integer> itemQuantities,
                       Context mContext) {

        this.itemNames = itemNames;
        this.itemQuantities = itemQuantities;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.cartItemTextView.setText(itemNames.get(position));
        holder.cartQuantityTextView.setText(itemQuantities.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cartItemTextView, cartQuantityTextView;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cartItemTextView = itemView.findViewById(R.id.cartItemName);
            cartQuantityTextView = itemView.findViewById(R.id.itemCartQuantity);

            parentLayout = itemView.findViewById(R.id.parent);

        }
    }
}