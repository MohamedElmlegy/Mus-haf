package com.example.mushaf.model2.tafseer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TafseerResponse {


    @SerializedName("data")
    @Expose
    private Data_t data;


    public Data_t getData() {
        return data;
    }

    public void setData(Data_t data) {
        this.data = data;
    }

}
