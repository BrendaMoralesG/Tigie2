package com.example.sistemascasa.tigie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.FragmentsActivity.FractionInformationActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;
import com.example.sistemascasa.tigie.settings.Dialogpol;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 30/08/16.
 */
public class FractionFavAdapater extends RecyclerView.Adapter<FractionFavAdapater.FractionFavViewHolder> {

    ArrayList<Fractions> fractions;
    ActivityFraccionFavoritos activity;
    Context context;
    Integer valTigie;

    public FractionFavAdapater(ArrayList<Fractions> fractions, ActivityFraccionFavoritos activity, Context context, Integer valTigie) {
        this.fractions = fractions;
        this.activity = activity;
        this.context = context;
        this.valTigie = valTigie;
    }

    @Override
    public FractionFavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favourites, parent, false);
        return new FractionFavViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FractionFavViewHolder fractionFavViewHolder, int position) {
        final Fractions fraction = fractions.get(position);

        fractionFavViewHolder.idTariffFraction.setText(String.valueOf(fraction.getIdTariffFraction()));
        fractionFavViewHolder.tariffFractionCode.setText(fraction.getTariffFractionCode());
        fractionFavViewHolder.tariffFractionDescription.setText(fraction.getTariffFractionDescription());

        fractionFavViewHolder.deleteFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogpol diagAddBult = new Dialogpol();
                Bundle argsA = new Bundle();
                argsA.putString("fraccion", fraction.getTariffFractionCode());
                diagAddBult.setArguments(argsA);
                diagAddBult.show(activity.getSupportFragmentManager(), "AddBultMenu");
            }
        });

        fractionFavViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FractionInformationActivity.class);
                intent.putExtra("valTigie", valTigie);
                intent.putExtra("fractionCode", fraction.getTariffFractionCode());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fractions.size();
    }

    public static class FractionFavViewHolder extends RecyclerView.ViewHolder {
        private TextView idTariffFraction;
        private TextView tariffFractionCode;
        private TextView tariffFractionDescription;
        private ImageView deleteFav;

        public FractionFavViewHolder(View itemView) {
            super(itemView);

            idTariffFraction                = (TextView)  itemView.findViewById(R.id.id_favourite);
            tariffFractionCode              = (TextView)  itemView.findViewById(R.id.tariffFractionCodeFavourite);
            tariffFractionDescription       = (TextView)  itemView.findViewById(R.id.tariffFractionFavouriteDesc);
            deleteFav                       = (ImageView) itemView.findViewById(R.id.deleteFav);
        }
    }
}
