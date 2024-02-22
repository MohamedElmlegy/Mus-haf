package com.example.mushaf.api;

import com.example.mushaf.model2.surah_audio.SurahResponse_a;
import com.example.mushaf.model2.surah_text.SurahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Quran_Api {



    @GET("surah/{id}/{edition}")
    Call<SurahResponse_a> getsurah(@Path("id") int id , @Path("edition") String edition);
}
