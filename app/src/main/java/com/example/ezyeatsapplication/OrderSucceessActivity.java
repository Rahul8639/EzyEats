package com.example.ezyeatsapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ezyeatsapplication.model.RestaurantModel;

public class OrderSucceessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_order_succeess); // Ensure the layout is correct

        // Retrieve RestaurantModel from the intent
        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");

        // Initialize ActionBar and set its title and subtitle
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(restaurantModel.getName());
            actionBar.setSubtitle(restaurantModel.getAddress());
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        // Handle the Done button click
        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });
    }
}
