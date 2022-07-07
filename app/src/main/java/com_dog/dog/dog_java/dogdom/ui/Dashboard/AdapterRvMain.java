package com_dog.dog.dog_java.dogdom.ui.Dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com_dog.dog.dog_java.dogdom.Modules.ItemHouse;
import com_dog.dog.dog_java.dogdom.R;

public class AdapterRvMain extends RecyclerView.Adapter<AdapterRvMain.ViewHolderHouse> {

    private ArrayList<ItemHouse> arrayHouses;
    private Context context;
    private static int lastClickedPosition = -1;
    private int selectedItem;

    public AdapterRvMain(ArrayList<ItemHouse> arrayHouses, Context context) {
        this.arrayHouses = arrayHouses;
        this.context = context;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public ViewHolderHouse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_houses, parent, false);
        return new ViewHolderHouse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHouse holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setTag(arrayHouses.get(position));
        holder.titleText.setText(arrayHouses.get(position).getTitle());
        holder.houseImg.setImageResource(arrayHouses.get(position).getImg());
        holder.streetText.setText(arrayHouses.get(position).getStreet());

    }

    @Override
    public int getItemCount() {
        return arrayHouses.size();
    }

    public class ViewHolderHouse extends RecyclerView.ViewHolder {
        private TextView titleText, streetText;
        private ImageView houseImg;
        public ViewHolderHouse(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.text_title);
            houseImg = itemView.findViewById(R.id.img_house);
            streetText = itemView.findViewById(R.id.text_street);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, DataActivity.class));
                }
            });

        }
    }

    public void filterList(ArrayList<ItemHouse> filteredList) {
        arrayHouses = filteredList;
        notifyDataSetChanged();
    }

}