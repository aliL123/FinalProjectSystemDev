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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.lang.StrictMath.round;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<String> itemNames = new ArrayList<>();
    static ArrayList<Integer> itemQuantities = new ArrayList<>();

    Context mContext;

    DatabaseHelper db;

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

    public void delete(int index){
        itemNames.remove(index);
        itemQuantities.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cartItemTextView, cartQuantityTextView;
        ImageButton decrement, increment;
        Button delete;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cartItemTextView = itemView.findViewById(R.id.cartItemName);
            cartQuantityTextView = itemView.findViewById(R.id.itemCartQuantity);
            decrement = itemView.findViewById(R.id.decrementItemQuantity);
            increment = itemView.findViewById(R.id.incrementItemQuantity);
            delete = itemView.findViewById(R.id.deleteCartItemButton);

            parentLayout = itemView.findViewById(R.id.parent);

            db = new DatabaseHelper(mContext);

            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int initialQuantity = itemQuantities.get(getAdapterPosition());

                    if (initialQuantity == 1){
                        decrement.setEnabled(false);
                    }else {
                        initialQuantity--;
                        itemQuantities.set(getAdapterPosition(), initialQuantity);

                        cartQuantityTextView.setText(""+itemQuantities.get(getAdapterPosition()));

                        if (initialQuantity == 1){
                            decrement.setEnabled(false);
                        }
                    }
                }
            });

            increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int initialQuantity = itemQuantities.get(getAdapterPosition());
                    initialQuantity++;
                    itemQuantities.set(getAdapterPosition(), initialQuantity);

                    cartQuantityTextView.setText(""+itemQuantities.get(getAdapterPosition()));

                    decrement.setEnabled(true);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String customerId = String.valueOf(MainActivity.customerId);
                    String itemId = String.valueOf(db.getItemName(itemNames.get(getAdapterPosition())));
                    Integer deleteItem = db.deleteCartItem(customerId, itemId);

                    if (deleteItem > 0){
                        Toast.makeText(mContext, "Item removed", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(mContext, "Item not removed", Toast.LENGTH_LONG).show();
                    }

                    delete(getAdapterPosition());
                }
            });
        }
    }
}