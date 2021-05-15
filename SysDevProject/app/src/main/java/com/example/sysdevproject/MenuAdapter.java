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

    Context mContext;

    public MenuAdapter(ArrayList<Integer> idsOne, ArrayList<String> imageOne, ArrayList<String> nameOne, ArrayList<Double> priceOne,
                       Context mContext) {

        this.idsOne = idsOne;
        this.imagesOne = imageOne;
        this.namesOne = nameOne;
        this.pricesOne = priceOne;

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

        holder.itemOneName.setText(namesOne.get(position));
        holder.itemOnePrice.setText("$"+pricesOne.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesOne.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton imageOne;
        TextView itemOneName, itemOnePrice;
        ImageButton delete, edit;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageOne = itemView.findViewById(R.id.itemOneImage);
            itemOneName = itemView.findViewById(R.id.itemOneName);
            itemOnePrice = itemView.findViewById(R.id.itemOnePrice);
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
        }
    }
}
