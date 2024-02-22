package com.example.mushaf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mushaf.model2.surah_audio.Data_a;
import com.example.mushaf.model2.surah_text.Data;
import com.example.mushaf.model2.tafseer.Data_t;
import com.example.mushaf.repository.Repository;

import java.util.concurrent.CompletableFuture;

public class MyViewModel extends AndroidViewModel {

    private Repository repository;



    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }


    public CompletableFuture<Data> getsurahlocal(int i ){
        return repository.getsurahLocal(i);
    }
    public CompletableFuture<Data_a> getsurah_audio(int i , String edition ){
        return repository.getsurah_audio(i,edition);
    }
    public CompletableFuture<Data_t> getTafsirLocal(int i ){
        return repository.getTafsirLocal(i);
    }

}
