package com.example.mushaf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mushaf.adapter.FehresAdapter;
import com.example.mushaf.adapter.SurahAdapter;
import com.example.mushaf.databinding.ActivitySurahBinding;
import com.example.mushaf.model2.Ayah;
import com.example.mushaf.model2.Data;
import com.example.mushaf.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class SurahActivity extends AppCompatActivity {

    private  int surah_no ;




    private TextView surah_name;
    private RecyclerView recyclerView;
    private SurahAdapter adapter;
    private List<Ayah> ayahList ;

    boolean loop_play ;

    private ActivitySurahBinding binding;

    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_surah
        );
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);




        binding.zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setSize(adapter.getSize()+2);
                adapter.notifyDataSetChanged();


            }
        });

        binding.zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setSize(adapter.getSize()-2);
                adapter.notifyDataSetChanged();

            }
        });

        binding.loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setLoop(!adapter.isLoop());
                binding.loop.setImageResource(adapter.isLoop()?R.drawable.loop
                        :R.drawable.loop_one);

            }
        });


        viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);


        ayahList = new ArrayList<>();

        surah_no = getIntent().getIntExtra("no",0);

        getfromfiles(false ,surah_no);




        binding.rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getfromfiles(true,surah_no -1);
            }
        });

        binding.leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getfromfiles(true, surah_no+1);
            }
        });

    }



    public void getfromfiles(boolean update , int i ){

        if(!update || !adapter.isPlaying_g()) {
            viewModel.getsurahlocal(i).thenAccept(surahDataList -> {


                binding.setSurah(surahDataList);
                ayahList = surahDataList.getAyahs();

                if (update) {
                    surah_no = i;
                }

                displaySurahInRecyclerView();


                disablebtns();


            }).exceptionally(throwable -> {
                Toast.makeText(this,
                        "file not found , searching the net ",
                        Toast.LENGTH_LONG).show();

                getfromnet(update, i);
                return null;
            });
        }
    }

    public  void getfromnet(boolean update , int i ){
        viewModel.getsurah(i).thenAccept(surahDataList -> {

            binding.setSurah(surahDataList);
            ayahList = surahDataList.getAyahs();

            if(update){
                surah_no = i ;
            }

            displaySurahInRecyclerView();

            disablebtns();



        }).exceptionally(throwable -> {
            Toast.makeText(this,
                    throwable.toString(),
                    Toast.LENGTH_LONG).show();

            return null;
        });
    }
    public void displaySurahInRecyclerView() {
        recyclerView = binding.recyclerview2;



        adapter  = new SurahAdapter(ayahList,this);




        recyclerView.setItemAnimator(new DefaultItemAnimator());




        RecyclerView.LayoutManager manager =
                new LinearLayoutManager(this
                        , LinearLayoutManager.VERTICAL,false){
                    @Override
                    public boolean canScrollVertically() {
                        return !adapter.isPlaying_g();
                    }
                };

        recyclerView.setLayoutManager(manager);



        recyclerView.setAdapter(adapter);

        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);

        adapter.onAttachedToRecyclerView(recyclerView);

        recyclerView.setNestedScrollingEnabled(false);


        // notify an adapter associated with a RecyclerView
        // that the underlying dataset hase changed
        adapter.notifyDataSetChanged();

        binding.loop.setImageResource(adapter.isLoop()?R.drawable.loop
                :R.drawable.loop_one);



    }

    public void disablebtns(){
        if (surah_no == 1 ){
            binding.rightBtn.setClickable(false);
        }
        else {
            binding.rightBtn.setClickable(true);
        }

        if (surah_no == 114 ){
            binding.leftBtn.setClickable(false);
        }
        else {
            binding.leftBtn.setClickable(true);
        }
    }

}