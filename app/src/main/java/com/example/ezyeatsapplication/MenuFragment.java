package com.example.ezyeatsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyeatsapplication.adapters.RestaurantListAdapter;
import com.example.ezyeatsapplication.model.RestaurantModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MenuFragment extends Fragment implements RestaurantListAdapter.RestaurantListClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_hotel, container, false);

        List<RestaurantModel> restaurantModelList = getRestaurantData();
        initRecyclerView(view, restaurantModelList);

        return view;
    }

    private void initRecyclerView(View view, List<RestaurantModel> restaurantModelList) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RestaurantListAdapter adapter = new RestaurantListAdapter(restaurantModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<RestaurantModel> getRestaurantData() {
        InputStream is = getResources().openRawResource(R.raw.restaurent);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log errors to debug issues
        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        RestaurantModel[] restaurantModels = gson.fromJson(jsonStr, RestaurantModel[].class);

        return Arrays.asList(restaurantModels);
    }

    @Override
    public void onItemClick(RestaurantModel restaurantModel) {
        Intent intent = new Intent(getActivity(), RestaurantMenuActivity.class);
        intent.putExtra("RestaurantModel", restaurantModel);
        startActivity(intent);
    }
}
