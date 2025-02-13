package com.example.ezyeatsapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ezyeatsapplication.R;
import com.example.ezyeatsapplication.model.RestaurantModel;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    private List<RestaurantModel> restaurantModelList;
    private RestaurantListClickListener clickListener;

    public RestaurantListAdapter(List<RestaurantModel> restaurantModelList, RestaurantListClickListener clickListener) {
        this.restaurantModelList = restaurantModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<RestaurantModel> restaurantModelList) {
        this.restaurantModelList = restaurantModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RestaurantModel restaurant = restaurantModelList.get(holder.getAdapterPosition());

        holder.restaurantName.setText(restaurant.getName());
        holder.restaurantAddress.setText("Address: " + restaurant.getAddress());
        holder.restaurantHours.setText("Today's hours: " + restaurant.getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(restaurantModelList.get(adapterPosition));
                }
            }
        });

        Glide.with(holder.thumbImage)
                .load(restaurant.getImage())
                .into(holder.thumbImage);
    }

    @Override
    public int getItemCount() {
        return restaurantModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        TextView restaurantAddress;
        TextView restaurantHours;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            restaurantName = view.findViewById(R.id.restaurantName);
            restaurantAddress = view.findViewById(R.id.restaurantAddress);
            restaurantHours = view.findViewById(R.id.restaurantHours);
            thumbImage = view.findViewById(R.id.thumbImage);
        }
    }

    public interface RestaurantListClickListener {
        void onItemClick(RestaurantModel restaurantModel);
    }
}
