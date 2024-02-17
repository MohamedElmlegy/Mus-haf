package com.example.mushaf.api;

import com.example.mushaf.model2.SurahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Quran_Api {



    @GET("surah/{id}/ar.alafasy")
    Call<SurahResponse> getsurah(@Path("id") int id);
}
