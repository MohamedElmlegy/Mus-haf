package com.example.mushaf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mushaf.adapter.FehresAdapter;
import com.example.mushaf.databinding.ActivityMainBinding;
import com.example.mushaf.model2.FehresItem;
import com.example.mushaf.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<FehresItem> newlist;
    private RecyclerView recyclerView;

    private FehresAdapter newadapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        newlist = new ArrayList<>();



        populateList();

        displayfehresInRecyclerView();

        

    }

    private void displayfehresInRecyclerView() {
        recyclerView = binding.recyclerview;



        newadapter  = new FehresAdapter(newlist,this);




        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager manager =
                new GridLayoutManager(this,2
                        ,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);



        recyclerView.setAdapter(newadapter);


        // notify an adapter associated with a RecyclerView
        // that the underlying dataset hase changed
        newadapter.notifyDataSetChanged();



    }



    private void populateList() {


        //1
        newlist.add(new FehresItem(
                "الفَاتِحَة"));

        //2
        newlist.add(new FehresItem(
                "البَقَرَة"));

        //3
        newlist.add(new FehresItem(
                "آل عِمرَان"));

        //4
        newlist.add(new FehresItem(
                "النِّسَاء"));

        //5
        newlist.add(new FehresItem(
                "المَائدة"));

        //6
        newlist.add(new FehresItem(
                "الأنعَام"));

        //7
        newlist.add(new FehresItem(
                "الأعرَاف"));

        //8
        newlist.add(new FehresItem(
                "الأنفَال"));

        //9
        newlist.add(new FehresItem(
                "التوبَة"));

        //10
        newlist.add(new FehresItem(
                "يُونس"));

        //11
        newlist.add(new FehresItem(
                "هُود"));

        //12
        newlist.add(new FehresItem(
                "يُوسُف"));

        //13
        newlist.add(new FehresItem(
                "الرَّعْد"));

        //14
        newlist.add(new FehresItem(
                "إبراهِيم"));

        //15
        newlist.add(new FehresItem(
                "الحِجْر"));

        //16
        newlist.add(new FehresItem(
                "النَّحْل"));

        //17
        newlist.add(new FehresItem(
                "الإسْرَاء"));

        //18
        newlist.add(new FehresItem(
                "الكهْف"));

        //19
        newlist.add(new FehresItem(
                "مَريَم"));

        //20
        newlist.add(new FehresItem(
                "طه"));

        //21
        newlist.add(new FehresItem(
                "الأنبيَاء"));

        //22
        newlist.add(new FehresItem(
                "الحَج"));

        //23
        newlist.add(new FehresItem(
                "المُؤمنون"));

        //24
        newlist.add(new FehresItem(
                "النُّور"));

        //25
        newlist.add(new FehresItem(
                "الفُرْقان"));

        //26
        newlist.add(new FehresItem(
                "الشُّعَرَاء"));

        //27
        newlist.add(new FehresItem(
                "النَّمْل"));

        //28
        newlist.add(new FehresItem(
                "القَصَص"));

        //29
        newlist.add(new FehresItem(
                "العَنكبوت"));

        //30
        newlist.add(new FehresItem(
                "الرُّوم"));

        //31
        newlist.add(new FehresItem(
                "لقمَان"));

        //32
        newlist.add(new FehresItem(
                "السَّجدَة"));

        //33
        newlist.add(new FehresItem(
                "الأحزَاب"));

        //34
        newlist.add(new FehresItem(
                "سَبَأ"));

        //35
        newlist.add(new FehresItem(
                "فَاطِر"));

        //36
        newlist.add(new FehresItem(
                "يس"));

        //37
        newlist.add(new FehresItem(
                "الصَّافات"));

        //38
        newlist.add(new FehresItem(
                "ص"));

        //39
        newlist.add(new FehresItem(
                "الزُّمَر"));

        //40
        newlist.add(new FehresItem(
                "غَافِر"));

        //41
        newlist.add(new FehresItem(
                "فُصِّلَتْ"));

        //42
        newlist.add(new FehresItem(
                "الشُّورَى"));

        //43
        newlist.add(new FehresItem(
                "الزُّخْرُف"));

        //44
        newlist.add(new FehresItem(
                "الدُّخان"));

        //45
        newlist.add(new FehresItem(
                "الجاثِية"));

        //46
        newlist.add(new FehresItem(
                "الأحقاف"));

        //47
        newlist.add(new FehresItem(
                "مُحَمّد"));

        //48
        newlist.add(new FehresItem(
                "الفَتْح"));

        //49
        newlist.add(new FehresItem(
                "الحُجُرات"));

        //50
        newlist.add(new FehresItem(
                "ق"));

        //51
        newlist.add(new FehresItem(
                "الذَّاريَات"));

        //52
        newlist.add(new FehresItem(
                "الطُّور"));

        //53
        newlist.add(new FehresItem(
                "النَّجْم"));

        //54
        newlist.add(new FehresItem(
                "القَمَر"));

        //55
        newlist.add(new FehresItem(
                "الرَّحمن"));

        //56
        newlist.add(new FehresItem(
                "الواقِعَة"));

        //57
        newlist.add(new FehresItem(
                "الحَديد"));

        //58
        newlist.add(new FehresItem(
                "المُجادَلة"));

        //59
        newlist.add(new FehresItem(
                "الحَشْر"));

        //60
        newlist.add(new FehresItem(
                "المُمتَحَنة"));

        //61
        newlist.add(new FehresItem(
                "الصَّف"));

        //62
        newlist.add(new FehresItem(
                "الجُّمُعة"));

        //63
        newlist.add(new FehresItem(
                "المُنافِقُون"));

        //64
        newlist.add(new FehresItem(
                "التَّغابُن"));

        //65
        newlist.add(new FehresItem(
                "الطَّلاق"));

        //66
        newlist.add(new FehresItem(
                "التَّحْريم"));

        //67
        newlist.add(new FehresItem(
                "المُلْك"));

        //68
        newlist.add(new FehresItem(
                "القَلـََم"));

        //69
        newlist.add(new FehresItem(
                "الحَاقّـَة"));

        //70
        newlist.add(new FehresItem(
                "المَعارِج"));

        //71
        newlist.add(new FehresItem(
                "نُوح"));

        //72
        newlist.add(new FehresItem(
                "الجِنّ"));

        //73
        newlist.add(new FehresItem(
                "المُزَّمّـِل"));

        //74
        newlist.add(new FehresItem(
                "المُدَّثــِّر"));

        //75
        newlist.add(new FehresItem(
                "القِيامَة"));

        //76
        newlist.add(new FehresItem(
                "الإنسان"));

        //77
        newlist.add(new FehresItem(
                "المُرسَلات"));

        //78
        newlist.add(new FehresItem(
                "النـَّبأ"));

        //79
        newlist.add(new FehresItem(
                "النـّازِعات"));

        //80
        newlist.add(new FehresItem(
                "عَبَس"));

        //81
        newlist.add(new FehresItem(
                "التـَّكْوير"));

        //82
        newlist.add(new FehresItem(
                "الإنفِطار"));

        //83
        newlist.add(new FehresItem(
                "المُطـَفِّفين"));

        //84
        newlist.add(new FehresItem(
                "الإنشِقاق"));

        //85
        newlist.add(new FehresItem(
                "البُروج"));

        //86
        newlist.add(new FehresItem(
                "الطّارق"));

        //87
        newlist.add(new FehresItem(
                "الأعلی"));

        //88
        newlist.add(new FehresItem(
                "الغاشِيَة"));

        //89
        newlist.add(new FehresItem(
                "الفَجْر"));

        //90
        newlist.add(new FehresItem(
                "البَـلـَد"));

        //91
        newlist.add(new FehresItem(
                "الشــَّمْس"));

        //92
        newlist.add(new FehresItem(
                "اللـَّيل"));

        //93
        newlist.add(new FehresItem(
                "الضُّحی"));

        //94
        newlist.add(new FehresItem(
                "الشَّرْح"));

        //95
        newlist.add(new FehresItem(
                "التـِّين"));

        //96
        newlist.add(new FehresItem(
                "العَلـَق"));

        //97
        newlist.add(new FehresItem(
                "القـَدر"));

        //98
        newlist.add(new FehresItem(
                "البَيِّنَة"));

        //99
        newlist.add(new FehresItem(
                "الزلزَلة"));

        //100
        newlist.add(new FehresItem(
                "العَادِيات"));

        //101
        newlist.add(new FehresItem(
                "القارِعَة"));

        //102
        newlist.add(new FehresItem(
                "التَكاثـُر"));

        //103
        newlist.add(new FehresItem(
                "العَصْر"));

        //104
        newlist.add(new FehresItem(
                "الهُمَزَة"));

        //105
        newlist.add(new FehresItem(
                "الفِيل"));

        //106
        newlist.add(new FehresItem(
                "قـُرَيْش"));

        //107
        newlist.add(new FehresItem(
                "المَاعُون"));

        //108
        newlist.add(new FehresItem(
                "الكَوْثَر"));

        //109
        newlist.add(new FehresItem(
                "الكَافِرُون"));

        //110
        newlist.add(new FehresItem(
                "النـَّصر"));

        //111
        newlist.add(new FehresItem(
                "المَسَد"));

        //112
        newlist.add(new FehresItem(
                "الإخْلَاص"));

        //113
        newlist.add(new FehresItem(
                "الفَلَق"));

        //114
        newlist.add(new FehresItem(
                "النَّاس"));

    }


}