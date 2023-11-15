package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.pojo.Headings;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 16/07/16.
 */

public class HeadingAdapter extends RecyclerView.Adapter<HeadingAdapter.HeadingViewHolder> {
    ArrayList<Headings> headings;
    Activity activity;
    int iconChapter;
    Integer valTigie;

    public HeadingAdapter(ArrayList<Headings> headings, Activity activity, int iconChapter, Integer valTigie) {
        this.headings = headings;
        this.activity = activity;
        this.iconChapter = iconChapter;
        this.valTigie = valTigie;
    }

    @Override
    public HeadingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_headings, parent, false); //Cual sera el layout que estara reciclando la lista
        return new HeadingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeadingViewHolder headingViewHolder, int position) {
        final Headings heading = headings.get(position);

        headingViewHolder.idTariffHeading.setText(String.valueOf(heading.getIdTariffHeading()));
        headingViewHolder.idTariffChapter.setText(String.valueOf(heading.getIdTariffChapter()));
        headingViewHolder.headingCode.setText(heading.getTariffHeadingCode());
        headingViewHolder.tariffHeadingDescription.setText(heading.getTariffHeadingDescription());

        headingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSubheading = new Intent(activity, SubheadingActivity.class);
                intentSubheading.putExtra("flag", 1);
                intentSubheading.putExtra("iconChapter",iconChapter);
                intentSubheading.putExtra("idTariffHeading", String.valueOf(heading.getIdTariffHeading()));
                intentSubheading.putExtra("tariffHeadingCode", heading.getTariffHeadingCode());
                intentSubheading.putExtra("tariffHeadingDescription", heading.getTariffHeadingDescription());
                intentSubheading.putExtra("idHeading", heading.getIdTariffHeading());
                intentSubheading.putExtra("valTigie", valTigie);
                activity.startActivity(intentSubheading);
            }
        });
    }

    @Override
    public int getItemCount() {
        return headings.size();
    }

    public static class HeadingViewHolder extends RecyclerView.ViewHolder {
        private TextView idTariffHeading;
        private TextView idTariffChapter;
        private TextView headingCode;
        private TextView tariffHeadingDescription;
        private ImageView ivIconChap;

        public HeadingViewHolder(View itemView) {
            super(itemView);

            idTariffHeading            = (TextView)  itemView.findViewById(R.id.tvIdTariffHeading);
            idTariffChapter            = (TextView)  itemView.findViewById(R.id.tvIdTariffChapter);
            headingCode                = (TextView)  itemView.findViewById(R.id.tvHeadingCode);
            tariffHeadingDescription   = (TextView)  itemView.findViewById(R.id.tvHeadingDescription);
            ivIconChap                 = (ImageView) itemView.findViewById(R.id.ivIconChap);
        }
    }
}