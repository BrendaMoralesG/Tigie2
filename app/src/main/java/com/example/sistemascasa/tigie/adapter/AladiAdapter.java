package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Aladi;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 18/08/16.
 */
public class AladiAdapter extends RecyclerView.Adapter<AladiAdapter.AladiViewHolder> {
    ArrayList<Aladi> aladis;
    Activity activity;

    public AladiAdapter(ArrayList<Aladi> aladis, Activity activity) {
        this.aladis = aladis;
        this.activity = activity;
    }

    @Override
    public AladiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_aladi, parent, false); //Cual sera el layout que estara reciclando la lista
        return new AladiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AladiViewHolder aladiViewHolder, int position) {
        final Aladi aladi = aladis.get(position);

        aladiViewHolder.ivFlagAladi.setImageResource(aladi.getFlagAladi());
        aladiViewHolder.countryDescription.setText(aladi.getCountryDescription());
        aladiViewHolder.aladiPreferenceRate.setText(aladi.getAladiPreferenceRate());
        aladiViewHolder.aladiDof.setText(aladi.getAladiDof());
        aladiViewHolder.aladiNota.setText(aladi.getAladiNota());

        if (aladi.getCountryDescription() == null) {
            aladiViewHolder.iconAladiCountryr.setVisibility(View.INVISIBLE);
        } else {
            aladiViewHolder.iconAladiCountryr.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return aladis.size();
    }


    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class AladiViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFlagAladi;
        private TextView countryDescription;
        private TextView  aladiPreferenceRate;
        private TextView  aladiDof;
        private TextView  aladiNota;
        private ImageView iconAladiCountryr;

        public AladiViewHolder(View itemView) {
            super(itemView);
            ivFlagAladi          = (ImageView) itemView.findViewById(R.id.imageViewAladi);
            countryDescription   = (TextView)  itemView.findViewById(R.id.tvAladiPaisCode);
            aladiPreferenceRate  = (TextView)  itemView.findViewById(R.id.tvAladiAdvaloremCode);
            aladiDof             = (TextView)  itemView.findViewById(R.id.tvAladiDofCode);
            aladiNota            = (TextView)  itemView.findViewById(R.id.notaAladi);
            iconAladiCountryr    = (ImageView) itemView.findViewById(R.id.iconAladiCountryr);
        }
    }
}
