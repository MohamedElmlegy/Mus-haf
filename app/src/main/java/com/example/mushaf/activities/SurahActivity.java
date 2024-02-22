package com.example.mushaf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mushaf.R;
import com.example.mushaf.adapter.SurahAdapter;
import com.example.mushaf.databinding.ActivitySurahBinding;
import com.example.mushaf.model2.surah_audio.Ayah_a;
import com.example.mushaf.model2.surah_text.Ayah;
import com.example.mushaf.model2.tafseer.Ayah_t;
import com.example.mushaf.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class SurahActivity extends AppCompatActivity
        implements
        AdapterView.OnItemSelectedListener {


    private  int surah_no ;

    private  String edition;




    private RecyclerView recyclerView;
    private SurahAdapter adapter;
    private Spinner spinner;
    private List<Ayah> ayahList ;

    private List<Ayah_t> ayahList_t ;

    private List<Ayah_a> ayahList_a ;


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




        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        String[] data = getResources().getStringArray(R.array.recitation);

        //spinner = findViewById(R.id.spinnerr);
        binding.spinnerr.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_item, data);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerr.setAdapter(adapter1);



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

        ayahList_t = new ArrayList<>();

        ayahList_a = new ArrayList<>();

        edition = "ar.abdulbasitmurattal";

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


            }).exceptionally(throwable -> {
                Toast.makeText(this,
                        "file not found  ",
                        Toast.LENGTH_LONG).show();

                return null;
            });
            viewModel.getTafsirLocal(i).thenAccept(surahDataList -> {

                ayahList_t = surahDataList.getAyahs();

            }).exceptionally(throwable -> {
                Toast.makeText(this,
                        "file not found ",
                        Toast.LENGTH_LONG).show();

                return null;
            });

            getaudio(i);

            if(update){
                surah_no = i ;
            }


        }

    }

    public  void getaudio(int i){
        viewModel.getsurah_audio(i,edition).thenAccept(surahDataList -> {

            ayahList_a = surahDataList.getAyahs();
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



        adapter  = new SurahAdapter(ayahList,ayahList_t,ayahList_a,this);




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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0 :
                edition = "ar.abdulbasitmurattal";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            case 1 :
                edition = "ar.abdurrahmaansudais";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            case 2 :
                edition = "ar.ahmedajamy";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            case 3 :
                edition = "ar.alafasy";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            case 4 :
                edition = "ar.husary";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            case 5 :
                edition = "ar.minshawi";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            case 6 :
                edition = "ar.mahermuaiqly";
                getaudio(surah_no);
                displaySurahInRecyclerView();
                break;
            default:
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);

    }
}