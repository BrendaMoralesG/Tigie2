package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Correlations;

import java.util.ArrayList;

public class CorrelationAdapter extends RecyclerView.Adapter<CorrelationAdapter.CorrelationViewHolder> {

    ArrayList<Correlations> correlations;
    Activity activity;

    public CorrelationAdapter(ArrayList<Correlations> correlations, Activity activity) {
        this.correlations = correlations;
        this.activity = activity;
    }
    @Override
    public CorrelationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_correlations, parent, false); //Cual sera el layout que estara reciclando la lista
        return new CorrelationAdapter.CorrelationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CorrelationAdapter.CorrelationViewHolder correlationViewHolder, int position) {
        final Correlations correlation = correlations.get(position);
        int id = correlation.getIdTariffFraction();
        correlationViewHolder.correlationCode.setText(correlation.getTariffFractionCode());
        correlationViewHolder.correlationDescription.setText(correlation.getTariffFractionDescription());
        correlationViewHolder.correlationImport.setText("IGI: " + correlation.getTariffPrincipalImport());
        correlationViewHolder.correlationExport.setText("IGE: " + correlation.getTariffPrincipalExport());
        correlationViewHolder.correlationUnit.setText("UM: " + correlation.getMeasurementUnitDescription());
    }

    @Override
    public int getItemCount() {
        return correlations.size();
    }

    public class CorrelationViewHolder extends RecyclerView.ViewHolder {
        private TextView correlationCode;
        private TextView correlationDescription;
        private TextView correlationImport;
        private TextView correlationExport;
        private TextView correlationUnit;

        public CorrelationViewHolder(@NonNull View itemView) {
            super(itemView);
            correlationCode  = (TextView)  itemView.findViewById(R.id.tvCode);
            correlationDescription  = (TextView)  itemView.findViewById(R.id.tvDescription);
            correlationImport  = (TextView)  itemView.findViewById(R.id.tvImport);
            correlationExport  = (TextView)  itemView.findViewById(R.id.tvExport);
            correlationUnit  = (TextView)  itemView.findViewById(R.id.tvUnit);

        }
    }
}
