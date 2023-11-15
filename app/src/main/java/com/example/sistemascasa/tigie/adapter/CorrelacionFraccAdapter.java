package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemascasa.tigie.FragmentsActivity.FractionInformationActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Correlaciones2020;

import java.util.ArrayList;

public class CorrelacionFraccAdapter  extends RecyclerView.Adapter<CorrelacionFraccAdapter.CorrelacionFraccViewHolder> {

    ArrayList<Correlaciones2020> correlaciones;
    Activity activity;

    public CorrelacionFraccAdapter( ArrayList<Correlaciones2020> correlaciones, Activity activity) {
        this.correlaciones = correlaciones;
        this.activity  = activity;
    }

    @Override
    public CorrelacionFraccAdapter.CorrelacionFraccViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_corrfrac, parent, false);
        return new CorrelacionFraccAdapter.CorrelacionFraccViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CorrelacionFraccAdapter.CorrelacionFraccViewHolder correlacionFraccViewHolder, int position) {
        final Correlaciones2020 corr = correlaciones.get(position);

        correlacionFraccViewHolder.tvIdCorr.setText(String.valueOf(corr.getIdTariffFraction()));
        correlacionFraccViewHolder.tvCodeFavCorr.setText(corr.getTariffFractionCode());
        correlacionFraccViewHolder.tvCodeFavDesc.setText(corr.getTariffNicoCode());

        correlacionFraccViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FractionInformationActivity.class);
                intent.putExtra("fractionCode", corr.getTariffFractionCode());
                intent.putExtra("id_fraccion", corr.getIdTariffFraction());
                intent.putExtra("valTigie", corr.getTigie());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return correlaciones.size();
    }

    public static class CorrelacionFraccViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdCorr;
        private TextView tvCodeFavCorr;
        private TextView tvCodeFavDesc;

        public CorrelacionFraccViewHolder(View itemView) {
            super(itemView);

            tvIdCorr       = (TextView)  itemView.findViewById(R.id.tvIdCorr);
            tvCodeFavCorr  = (TextView)  itemView.findViewById(R.id.tvCodeFavCorr);
            tvCodeFavDesc  = (TextView)  itemView.findViewById(R.id.tvCodeFavDesc);
        }
    }

}
