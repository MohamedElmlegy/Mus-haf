package com.example.mushaf.model2.tafseer;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_t {

    @SerializedName("ayahs")
    @Expose
    private List<Ayah_t> ayahs;

    public List<Ayah_t> getAyahs() {
        return ayahs;
    }
    public void setAyahs(List<Ayah_t> ayahs) {
        this.ayahs = ayahs;
    }
}