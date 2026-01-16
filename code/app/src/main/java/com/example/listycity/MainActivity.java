package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int selectedCity = -1;
    ListView cityList;
    Button addCity;
    Button buttonConfirm;
    Button deleteCity;
    EditText addCityInput;
    LinearLayout inputBar;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city);
        addCityInput = findViewById(R.id.add_city_input);
        buttonConfirm= findViewById(R.id.button_confirm);
        deleteCity= findViewById(R.id.delete_city);
        addCityInput = findViewById(R.id.add_city_input);
        inputBar = findViewById(R.id.input_bar);



        String []cities = {"Edmonton"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        String addCityString = "Add City";

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // the user clicks the confirm button to add the city they typed
        buttonConfirm.setOnClickListener(v -> {
            String cityName = addCityInput.getText().toString().trim();

            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                addCityInput.setText("");
            }
        });

        // makes the input bar visible
        addCity.setOnClickListener(v -> {
            inputBar.setVisibility(View.VISIBLE);
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = position; // Remember which one was clicked
            String cityName = dataList.get(position);
            view.setBackgroundColor(Color.LTGRAY);
        });

        deleteCity.setOnClickListener(v -> {
            dataList.remove(selectedCity);
            cityAdapter.notifyDataSetChanged();
        });

    }
}