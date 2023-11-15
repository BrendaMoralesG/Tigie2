package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Tlc;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 10/08/16.
 */
public class TlcAdapter extends RecyclerView.Adapter<TlcAdapter.TlcViewHolder> {
    ArrayList<Tlc> tlcs;
    Activity activity;

    public TlcAdapter(ArrayList<Tlc> tlcs, Activity activity) {
        this.tlcs = tlcs;
        this.activity = activity;
    }

    @Override
    public TlcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tlc, parent, false); //Cual sera el layout que estara reciclando la lista
        return new TlcViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TlcViewHolder tlcViewHolder, int position) {
        final Tlc tlc = tlcs.get(position);

        tlcViewHolder.ivFlagIcon.setImageResource(tlc.getTlcFlag());
        tlcViewHolder.tlcDof.setText(tlc.getTlcDof());
        tlcViewHolder.tlcCountry.setText(tlc.getTlcCountry());
        tlcViewHolder.tlcAdval.setText(tlc.getTlcAdval());
    }

    @Override
    public int getItemCount() {
        return tlcs.size();
    }

    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class TlcViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFlagIcon;
        private TextView  tlcDof;
        private TextView  tlcCountry;
        private TextView  tlcAdval;

        public TlcViewHolder(View itemView) {
            super(itemView);
            ivFlagIcon          = (ImageView) itemView.findViewById(R.id.ivFlagIcon);
            tlcDof              = (TextView)  itemView.findViewById(R.id.tlcDof);
            tlcCountry          = (TextView)  itemView.findViewById(R.id.tlcCountry);
            tlcAdval            = (TextView)  itemView.findViewById(R.id.tlcAdval);
        }
    }

}
