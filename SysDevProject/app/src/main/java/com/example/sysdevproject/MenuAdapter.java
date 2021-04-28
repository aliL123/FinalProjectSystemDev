package com.example.sysdevproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

    ArrayList<String> imagesOne = new ArrayList<>();
    ArrayList<String> namesOne = new ArrayList<>();
    ArrayList<String> pricesOne = new ArrayList<>();

    ArrayList<String> imagesTwo = new ArrayList<>();
    ArrayList<String> namesTwo = new ArrayList<>();
    ArrayList<String> pricesTwo = new ArrayList<>();

    ArrayList<String> imagesThree = new ArrayList<>();
    ArrayList<String> namesThree = new ArrayList<>();
    ArrayList<String> pricesThree = new ArrayList<>();
    Context mContext;

    public MenuAdapter(ArrayList<String> imageOne, ArrayList<String> nameOne, ArrayList<String> priceOne,
                       ArrayList<String> imageTwo, ArrayList<String> nameTwo, ArrayList<String> priceTwo,
                       ArrayList<String> imagesThree, ArrayList<String> namesThree, ArrayList<String> pricesThree,
                       Context mContext) {

        this.imagesOne = imageOne;
        this.namesOne = nameOne;
        this.pricesOne = priceOne;

        this.imagesTwo = imageTwo;
        this.namesTwo = nameTwo;
        this.pricesTwo = priceTwo;

        this.imagesThree = imagesThree;
        this.namesThree = namesThree;
        this.pricesThree = pricesThree;

        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
//        Glide.with(mContext)
//                .asBitmap()
//                .load(imagesOne.get(position))
//                .into(holder.imageOne);
//
//        Glide.with(mContext)
//                .asBitmap()
//                .load(imagesTwo.get(position))
//                .into(holder.imageTwo);
//
//        Glide.with(mContext)
//                .asBitmap()
//                .load(imagesThree.get(position))
//                .into(holder.imageThree);

        holder.itemOneName.setText(namesOne.get(position));
        holder.itemOnePrice.setText(pricesOne.get(position));

        holder.itemTwoName.setText(namesTwo.get(position));
        holder.itemTwoPrice.setText(pricesTwo.get(position));

        holder.itemThreeName.setText(namesThree.get(position));
        holder.itemThreePrice.setText(pricesTwo.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesOne.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageOne, imageTwo, imageThree;
        TextView itemOneName, itemOnePrice;
        TextView itemTwoName, itemTwoPrice;
        TextView itemThreeName, itemThreePrice;
        ImageButton delete, edit;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageOne = itemView.findViewById(R.id.itemOneImage);
            itemOneName = itemView.findViewById(R.id.itemOneName);
            itemOnePrice = itemView.findViewById(R.id.itemOnePrice);
            parentLayout = itemView.findViewById(R.id.parent);

            imageTwo = itemView.findViewById(R.id.itemTwoImage);
            itemTwoName = itemView.findViewById(R.id.itemTwoName);
            itemTwoPrice = itemView.findViewById(R.id.itemTwoPrice);
            parentLayout = itemView.findViewById(R.id.parent);

            imageThree = itemView.findViewById(R.id.itemThreeImage);
            itemThreeName = itemView.findViewById(R.id.itemThreeName);
            itemThreePrice = itemView.findViewById(R.id.itemThreePrice);
            parentLayout = itemView.findViewById(R.id.parent);
        }
    }
}
