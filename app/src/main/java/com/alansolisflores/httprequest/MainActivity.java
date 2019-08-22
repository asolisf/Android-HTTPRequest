package com.alansolisflores.httprequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alansolisflores.httprequest.Models.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.showCity();
    }

    private City getCity(){
        String cityJson = "{\"id\":\"100\",\"name\":\"Mexico City\"}";

        City city = null;

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        city = gson.fromJson(cityJson,City.class);

        return city;
    }

    private void showCity(){
        City city = this.getCity();
        Toast.makeText(this,city.getName(),Toast.LENGTH_SHORT).show();
    }
}
