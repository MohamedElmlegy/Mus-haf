package com.example.mushaf.adapter;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.model2.Ayah;

import java.io.IOException;
import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {


    private int currentPlayingPosition = -1;

    RecyclerView recyclerView ;
    private List<Ayah> ayahList ;

    private Context context ;

    private boolean playing_g;

    public boolean isLoop() {
        return loop;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size>30) {
            this.size = 30;
        } else if (size < 20) {
            this.size = 20;

        } else{
            this.size = size;
        }
    }

    private int size = 26 ;

    public void setLoop(boolean loop) {

        this.loop = loop;
    }

    private boolean loop  = true;


    public boolean isPlaying_g() {
        return playing_g;
    }

    public void scroll_to_position(int i){
        recyclerView.scrollToPosition(i+3);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

    }

    public void setPlaying_g(boolean playing_g) {
        this.playing_g = playing_g;
    }





    public SurahAdapter(List<Ayah> ayahList, Context context) {
        this.ayahList = ayahList;
        this.context = context;
        this.playing_g = false;

    }

    public boolean connected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        return connected;
    }

    private void playNextMediaPlayer(int currentPosition) {


        if (loop && connected()) {

            if (currentPosition < getItemCount() - 1) {
                SurahViewHolder nextViewHolder = (SurahViewHolder) recyclerView.findViewHolderForAdapterPosition(currentPosition + 1);

                if (nextViewHolder != null) {
                    nextViewHolder.playMedia();
                    scroll_to_position(currentPosition != ayahList.size() - 1 ? currentPosition + 1 : currentPosition);
                }
            }
        }

    }



    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.ayah_item_layout, parent, false);

        SurahViewHolder temp = new SurahViewHolder(view);

        return temp;

    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        holder.ayah_text.setText(ayahList.get(position).getText());


        holder.ayah_text.setTextSize(size);




        holder.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCompletion(MediaPlayer mp) {
                holder.mediaBtn.setImageResource(R.drawable.play32);
                mp.stop();
                playing_g = false;
                holder.playing = false ;
                playNextMediaPlayer(holder.getAdapterPosition());
                holder.ayah_text.setTextColor(ContextCompat.getColor(context, R.color.black));

            }
        });






        holder.mediaBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (!holder.playing && connected()){

                    if(!playing_g) {

                        try {
                            holder.mediaPlayer.reset();
                            holder.mediaPlayer.setDataSource(ayahList.get(holder.getAdapterPosition())
                                    .getAudio());
                            holder.mediaPlayer.prepare();


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        holder.mediaPlayer.start();
                        playing_g = true;
                        holder.mediaBtn.setImageResource(R.drawable.pause32);
                        holder.playing = true;
                        holder.ayah_text.setTextColor(ContextCompat.getColor(context, R.color.high));


                    }else {

                    }


                }
                else{
                    holder.mediaBtn.setImageResource(R.drawable.play32);
                    holder.mediaPlayer.stop();
                    holder.playing = false ;
                    playing_g = false;
                    holder.ayah_text.setTextColor(ContextCompat.getColor(context, R.color.black));



                }
            }
        });







    }

    @Override
    public int getItemCount() {
        return ayahList.size();
    }


    public class SurahViewHolder extends RecyclerView.ViewHolder
            {


        private TextView ayah_text;

        private ImageButton mediaBtn;

        private MediaPlayer mediaPlayer;


        private boolean playing;





        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);


            mediaPlayer = new MediaPlayer();

            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            playing = false;




            ayah_text = itemView.findViewById(R.id.ayah_text);
            mediaBtn = itemView.findViewById(R.id.media_btn);


        }

        @SuppressLint("ResourceAsColor")
        public void playMedia() {
            if (!playing && !playing_g && mediaPlayer != null && connected()) {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(ayahList.get(getAdapterPosition()).getAudio());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    ayah_text.setTextColor(ContextCompat.getColor(context, R.color.high));
                    playing = true;
                    playing_g = true ;
                    mediaBtn.setImageResource(R.drawable.pause32);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaBtn.setImageResource(R.drawable.play32);
                            mp.stop();
                            playing = false;
                            playing_g = false;
                            playNextMediaPlayer(getAdapterPosition());
                            ayah_text.setTextColor(ContextCompat.getColor(context, R.color.black));

                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public void stopmedia(){
            mediaPlayer.stop();
        }



    }



}


