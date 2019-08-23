package com.alansolisflores.httprequest.Models.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppConfiguration {

    @Expose
    @SerializedName("endpoint")
    private String endpoint;

    @Expose
    @SerializedName("api_key")
    private String apiKey;

    @Expose
    @SerializedName("weather_method")
    private String weatherMethod;

    public AppConfiguration(String endpoint, String apiKey, String weatherMethod) {
        this.endpoint = endpoint;
        this.apiKey = apiKey;
        this.weatherMethod = weatherMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getWeatherMethod() {
        return weatherMethod;
    }

    public void setWeatherMethod(String weatherMethod) {
        this.weatherMethod = weatherMethod;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
