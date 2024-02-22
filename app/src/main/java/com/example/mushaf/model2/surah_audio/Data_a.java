package com.example.mushaf.model2.surah_audio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data_a {

    @SerializedName("ayahs")
    @Expose
    private List<Ayah_a> ayahs;

    public List<Ayah_a> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Ayah_a> ayahs) {
        this.ayahs = ayahs;
    }


}