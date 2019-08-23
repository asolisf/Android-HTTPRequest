package com.alansolisflores.httprequest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alansolisflores.httprequest.Models.Country;
import com.alansolisflores.httprequest.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.showCountry();
    }

    private Country getCountry(){
        Country country;

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        country = gson.fromJson(this.readFile(),Country.class);

        return country;
    }

    private void showCountry(){
        Country country = this.getCountry();
        Toast.makeText(this,
                country.getName()+", country "+country.getCities().size(),
                Toast.LENGTH_SHORT).show();
    }

    private String readFile(){

        String jsonString = "";

        try {

            InputStream inputStream = this.getResources().openRawResource(R.raw.country);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            jsonString = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
