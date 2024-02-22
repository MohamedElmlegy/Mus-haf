package com.example.mushaf.model2.surah_audio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ayah_a {


    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("audioSecondary")
    @Expose
    private List<String> audioSecondary;

    @SerializedName("numberInSurah")
    @Expose
    private Integer numberInSurah;


    public Integer getNumber() {
        return numberInSurah;
    }


    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public List<String> getAudioSecondary() {
        return audioSecondary;
    }

    public void setAudioSecondary(List<String> audioSecondary) {
        this.audioSecondary = audioSecondary;
    }

    public Integer getNumberInSurah() {
        return numberInSurah;
    }

    public void setNumberInSurah(Integer numberInSurah) {
        this.numberInSurah = numberInSurah;
    }




}
