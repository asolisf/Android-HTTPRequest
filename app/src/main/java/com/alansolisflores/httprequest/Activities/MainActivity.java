package com.alansolisflores.httprequest.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.alansolisflores.httprequest.BuildConfig;
import com.alansolisflores.httprequest.Contracts.IWeatherService;
import com.alansolisflores.httprequest.Models.City;
import com.alansolisflores.httprequest.R;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity{

    private final String CITY_NAME = "London,uk";

    private TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.weatherTextView = findViewById(R.id.weatherTextView);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BuildConfig.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IWeatherService weatherService = retrofit.create(IWeatherService.class);
        Call<City> cityCall = weatherService.getCity(CITY_NAME,BuildConfig.API_KEY);
        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();

                String weather = city.getName()+
                                 ", "+
                                 city.getMain().getTemp()+
                                 " °F";

                weatherTextView.setText(weather);
            }

            @Override
            public void onFailure(Call<City> call, Throwable throwable) {
                Toast.makeText(MainActivity.this,R.string.error_service,Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
