package com.example.mushaf.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient  {

    private static ApiClient instance = null;
    private Quran_Api myApi;
    private static String base_url = "http://api.alquran.cloud/v1/";



    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Quran_Api.class);
    }


    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }

        return instance;
    }
    public Quran_Api getMyApi() {
        return myApi;
    }
}
