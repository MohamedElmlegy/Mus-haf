package com.example.mushaf.model2.tafseer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ayah_t {

    @SerializedName("text")
    @Expose
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}