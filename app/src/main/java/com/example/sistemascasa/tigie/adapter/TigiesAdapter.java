package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.ChapterActivity;

import java.util.ArrayList;

public class TigiesAdapter extends RecyclerView.Adapter<TigiesAdapter.ModuleViewHolder> {
    ArrayList<String> tigiesArrayList;
    Activity activity;

    public TigiesAdapter(ArrayList<String> tigiesArrayList, Activity activity) {
        this.tigiesArrayList = tigiesArrayList;
        this.activity = activity;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tigies, parent, false);
        return new ModuleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder moduleViewHolder, int position) {
        final String tigie = tigiesArrayList.get(position);
        final int num = position;

        moduleViewHolder.tigieTitle.setText(tigie);

        moduleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valTigie = 0;
                
                if (num == 0) {
                    valTigie = 2012;
                } else {
                    valTigie = 2020;
                }
                Intent intentChap = new Intent(activity, ChapterActivity.class);
                intentChap.putExtra("chapterCode", "");
                intentChap.putExtra("valTigie", valTigie);
                activity.startActivity(intentChap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tigiesArrayList.size();
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        private TextView tigieTitle;

        public ModuleViewHolder(View itemView) {
            super(itemView);
            tigieTitle  = (TextView)  itemView.findViewById(R.id.tigieTitle);
        }
    }
}

