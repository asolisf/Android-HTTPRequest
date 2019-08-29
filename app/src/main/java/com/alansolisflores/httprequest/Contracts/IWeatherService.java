package com.alansolisflores.httprequest.Contracts;

import com.alansolisflores.httprequest.Models.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWeatherService {

    //weather?q={city}&appid={api_key}
    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid") String api_key);
}
