package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.AlliancePacific;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 20/08/16.
 */
public class AllianceAdapter extends RecyclerView.Adapter<AllianceAdapter.AllianceViewHolder>{

    ArrayList<AlliancePacific> alliancePacifics;
    Activity activity;

    public AllianceAdapter(ArrayList<AlliancePacific> alliancePacifics, Activity activity) {
        this.alliancePacifics = alliancePacifics;
        this.activity = activity;
    }

    @Override
    public AllianceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_alliance, parent, false); //Cual sera el layout que estara reciclando la lista
        return new AllianceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AllianceViewHolder allianceViewHolder, int position) {
        final AlliancePacific alliancePacific = alliancePacifics.get(position);

        allianceViewHolder.ivFlagIconAlliance.setImageResource(alliancePacific.getTlcPacificAllianceFlag());
        allianceViewHolder.tvPaisAllianceCode.setText(alliancePacific.getTlcPacificAllianceFreeCountryCode());
        allianceViewHolder.tvAdvaloremAllianceCode.setText(alliancePacific.getPacificAllianceAdvalCountry());
        allianceViewHolder.tvDofAllianceCode.setText(alliancePacific.getTlcPacificAllianceAdvalDof());
    }

    @Override
    public int getItemCount() {
        return alliancePacifics.size();
    }


    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class AllianceViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFlagIconAlliance;
        private TextView  tvPaisAllianceCode;
        private TextView  tvAdvaloremAllianceCode;
        private TextView  tvDofAllianceCode;

        public AllianceViewHolder(View itemView) {
            super(itemView);
            ivFlagIconAlliance          = (ImageView) itemView.findViewById(R.id.ivFlagIconAlliance);
            tvPaisAllianceCode          = (TextView)  itemView.findViewById(R.id.tvPaisAllianceCode);
            tvAdvaloremAllianceCode     = (TextView)  itemView.findViewById(R.id.tvAdvaloremAllianceCode);
            tvDofAllianceCode           = (TextView)  itemView.findViewById(R.id.tvDofAllianceCode);
        }
    }

}
