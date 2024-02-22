package com.example.mushaf.model2.surah_audio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurahResponse_a {


    @SerializedName("data")
    @Expose
    private Data_a data;

    public Data_a getData() {
        return data;
    }

    public void setData(Data_a data) {
        this.data = data;
    }

}