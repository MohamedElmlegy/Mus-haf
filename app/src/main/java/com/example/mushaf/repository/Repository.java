package com.example.mushaf.repository;


import android.app.Application;
import android.content.res.AssetManager;

import androidx.lifecycle.MutableLiveData;

import com.example.mushaf.api.ApiClient;
import com.example.mushaf.api.Quran_Api;
import com.example.mushaf.model2.Data;
import com.example.mushaf.model2.SurahResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private Application application;

    private Quran_Api api;


    public Repository(Application application) {
        this.application = application;
        api = ApiClient.getInstance().getMyApi();

    }


    public CompletableFuture<Data> getsurahLocal(int i ){
        CompletableFuture<Data> completableFuture
                = new CompletableFuture<>();
        try {
            String pad;
            if(i<10) {
                pad = "00";
            }
            else if(i<100 && i >10) {
                pad = "0";
            }else {
                pad = "";
            }

            AssetManager mngr = application.getAssets();
            InputStream inputStream = application.getAssets().open("chapters/"+pad+i+".json");
            InputStreamReader reader = new InputStreamReader(inputStream);

            Gson gson = new Gson();
            SurahResponse temp = gson.fromJson(reader, SurahResponse.class);

            reader.close();
            inputStream.close();
            Data surahData = turn_object((temp.getData()));
            completableFuture.complete(surahData);


        } catch (FileNotFoundException e) {
            completableFuture.completeExceptionally(e);

        } catch (IOException e) {
            completableFuture.completeExceptionally(e);
        }


        return completableFuture;

    }


    public CompletableFuture<Data> getsurah(int i ){
        CompletableFuture<Data> completableFuture
                = new CompletableFuture<>();

        api.getsurah(i).enqueue(new Callback<SurahResponse>(){

            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                if (response.isSuccessful()) {
                     Data surahData = turn_object((response.body()
                            .getData()));
                    completableFuture.complete(surahData);
                } else {
                    completableFuture.completeExceptionally(new IOException("Error fetching data"));
                }
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                completableFuture.completeExceptionally(t);

            }
        });
        return completableFuture;

    }

    public Data turn_object(Data surah){

            for (com.example.mushaf.model2.Ayah ayah : surah.getAyahs()){
                String text ;
                text = ayah.getText().toString();
                text = text + " (" + convertToArabic(Integer.parseInt(ayah.getNumber().toString())) +") ";
                ayah.setText(text);
            }
        return surah;
    }



    public String convertToArabic(int value)
    {
        String newValue = (((((((((((value+"")
                .replaceAll("1", "١")).replaceAll("2", "٢"))
                .replaceAll("3", "٣")).replaceAll("4", "٤"))
                .replaceAll("5", "٥")).replaceAll("6", "٦"))
                .replaceAll("7", "٧")).replaceAll("8", "٨"))
                .replaceAll("9", "٩")).replaceAll("0", "٠"));
        return newValue;
    }


}
