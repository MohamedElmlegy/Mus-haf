package com.example.mushaf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mushaf.model2.Data;
import com.example.mushaf.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public CompletableFuture<Data> getsurah(int i ){
        return repository.getsurah(i);
    }
}
