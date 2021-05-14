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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

    ArrayList<Integer> idsOne = new ArrayList<>();
    ArrayList<String> imagesOne = new ArrayList<>();
    ArrayList<String> namesOne = new ArrayList<>();
    ArrayList<Double> pricesOne = new ArrayList<>();

    ArrayList<Integer> idsTwo = new ArrayList<>();
    ArrayList<String> imagesTwo = new ArrayList<>();
    ArrayList<String> namesTwo = new ArrayList<>();
    ArrayList<Double> pricesTwo = new ArrayList<>();

    ArrayList<Integer> idsThree = new ArrayList<>();
    ArrayList<String> imagesThree = new ArrayList<>();
    ArrayList<String> namesThree = new ArrayList<>();
    ArrayList<Double> pricesThree = new ArrayList<>();
    Context mContext;

    public MenuAdapter(ArrayList<Integer> idsOne, ArrayList<String> imageOne, ArrayList<String> nameOne, ArrayList<Double> priceOne,
                       ArrayList<Integer> idsTwo, ArrayList<String> imageTwo, ArrayList<String> nameTwo, ArrayList<Double> priceTwo,
                       ArrayList<Integer> idsThree, ArrayList<String> imagesThree, ArrayList<String> namesThree, ArrayList<Double> pricesThree,
                       Context mContext) {

        this.idsOne = idsOne;
        this.imagesOne = imageOne;
        this.namesOne = nameOne;
        this.pricesOne = priceOne;

        this.idsTwo = idsTwo;
        this.imagesTwo = imageTwo;
        this.namesTwo = nameTwo;
        this.pricesTwo = priceTwo;

        this.idsThree = idsThree;
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
        Picasso.get()
                .load(imagesOne.get(position))
                .into(holder.imageOne);

        Picasso.get()
                .load(imagesTwo.get(position))
                .into(holder.imageTwo);

        Picasso.get()
                .load(imagesThree.get(position))
                .into(holder.imageThree);

        holder.itemOneName.setText(namesOne.get(position));
        holder.itemOnePrice.setText("$"+pricesOne.get(position));

        holder.itemTwoName.setText(namesTwo.get(position));
        holder.itemTwoPrice.setText("$"+pricesTwo.get(position));

        holder.itemThreeName.setText(namesThree.get(position));
        holder.itemThreePrice.setText("$"+pricesThree.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesOne.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton imageOne, imageTwo, imageThree;
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


            imageOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ItemScreen.class);
                    intent.putExtra("id", idsOne.get(getAdapterPosition()));
                    intent.putExtra("image", imagesOne.get(getAdapterPosition()));
                    intent.putExtra("name", namesOne.get(getAdapterPosition()));
                    intent.putExtra("price", pricesOne.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });

            imageTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ItemScreen.class);
                    intent.putExtra("id", idsTwo.get(getAdapterPosition()));
                    intent.putExtra("image", imagesTwo.get(getAdapterPosition()));
                    intent.putExtra("name", namesTwo.get(getAdapterPosition()));
                    intent.putExtra("price", pricesTwo.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });

            imageThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ItemScreen.class);
                    intent.putExtra("id", idsThree.get(getAdapterPosition()));
                    intent.putExtra("image", imagesThree.get(getAdapterPosition()));
                    intent.putExtra("name", namesThree.get(getAdapterPosition()));
                    intent.putExtra("price", pricesThree.get(getAdapterPosition()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
