package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Subheadings;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 20/07/16.
 */
public class SubHeadingAdapter extends RecyclerView.Adapter<SubHeadingAdapter.SubHeadingViewHolder> {

    ArrayList<Subheadings> subheadingses;
    Activity activity;
    int iconChapter;
    Integer valTigie;

    public SubHeadingAdapter(ArrayList<Subheadings> subheadingses, Activity activity, int iconChapter, Integer valTigie) {
        this.subheadingses = subheadingses;
        this.activity = activity;
        this.iconChapter = iconChapter;
        this.valTigie = valTigie;
    }

    @Override
    public SubHeadingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_subheadings, parent, false); //Cual sera el layout que estara reciclando la lista
        return new SubHeadingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubHeadingViewHolder subHeadingViewHolder, int position) {
        final Subheadings subheadings = subheadingses.get(position);

        subHeadingViewHolder.idTariffSubHeading.setText(String.valueOf(subheadings.getIdTariffSubheading()));
        subHeadingViewHolder.idTariffHead.setText(String.valueOf(subheadings.getIdTariffHeading()));
        subHeadingViewHolder.idTariffPreSubHead.setText(String.valueOf(subheadings.getIdTariffPreSubheading()));
        subHeadingViewHolder.subHeadingCode.setText(subheadings.getTariffSubheadingCode());
        subHeadingViewHolder.tariffSubHeadingDescription.setText(subheadings.getTariffSubheadingDescription());

        subHeadingViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFraction = new Intent(activity, FractionsActivity.class);
                intentFraction.putExtra("flag", 1);
                intentFraction.putExtra("iconChapter", iconChapter);
                intentFraction.putExtra("idTariffSubheading", String.valueOf(subheadings.getIdTariffSubheading()));
                intentFraction.putExtra("tariffSubheadingCode", subheadings.getTariffSubheadingCode());
                intentFraction.putExtra("tariffSubheadingDescription", subheadings.getTariffSubheadingDescription());
                intentFraction.putExtra("idSubheading", subheadings.getIdTariffSubheading());
                intentFraction.putExtra("valTigie", valTigie);

                activity.startActivity(intentFraction);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subheadingses.size();
    }

    public static class SubHeadingViewHolder extends RecyclerView.ViewHolder {
        private TextView idTariffSubHeading;
        private TextView idTariffHead;
        private TextView subHeadingCode;
        private TextView tariffSubHeadingDescription;
        private TextView idTariffPreSubHead;

        public SubHeadingViewHolder(View itemView) {
            super(itemView);

            idTariffSubHeading              = (TextView)  itemView.findViewById(R.id.tvIdTariffSubheading);
            idTariffHead                    = (TextView)  itemView.findViewById(R.id.tvIdTariffHeading);
            subHeadingCode                  = (TextView)  itemView.findViewById(R.id.tvSubheadingCode);
            tariffSubHeadingDescription     = (TextView)  itemView.findViewById(R.id.tvSubheadingDescription);
            idTariffPreSubHead              = (TextView)  itemView.findViewById(R.id.tvIdTariffPreSubheading);
        }
    }
}
