package com.alansolisflores.httprequest.Helpers;

import com.alansolisflores.httprequest.Models.Objects.AppConfiguration;
import com.alansolisflores.httprequest.MyApplication;
import com.alansolisflores.httprequest.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Configuration {

    public static AppConfiguration Instance = initializeConfiguration();

    private static AppConfiguration initializeConfiguration(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(readFile(),AppConfiguration.class);
    }

    private static String readFile(){

        String jsonString = "";

        try {

            InputStream inputStream =
                    MyApplication
                            .getContext()
                            .getResources()
                            .openRawResource(R.raw.config);

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
