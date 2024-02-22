package com.example.mushaf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mushaf.R;
import com.example.mushaf.activities.SurahActivity;
import com.example.mushaf.model2.FehresItem;

import java.util.List;

public class FehresAdapter extends RecyclerView.Adapter<FehresAdapter.FehresViewHolder>{

    List<FehresItem> fehres ;
    Context context ;

    public FehresAdapter(List<FehresItem> fehres, Context context) {
        this.fehres = fehres;
        this.context = context;
    }

    @NonNull
    @Override
    public FehresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.fehres_item, parent, false);


        return new FehresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FehresViewHolder holder, int position) {

        holder.item_arabic.setText(fehres.get(position)
                .getArabic_name());

    }

    @Override
    public int getItemCount() {
        return fehres.size();
    }


    public class FehresViewHolder extends RecyclerView.ViewHolder{

        private TextView item_arabic;


        public FehresViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(),
                            SurahActivity.class);
                    intent.putExtra("no",i+1);
                    itemView.getContext().startActivity(intent);
                }
            });
            item_arabic = itemView.findViewById(R.id.fehres_item_arabic);

        }
    }
}
