package com.example.sistemascasa.tigie.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Transpacific;

import java.util.ArrayList;

public class TranspacificAdapter extends RecyclerView.Adapter<TranspacificAdapter.TranspacificViewHolder>  {

    ArrayList<Transpacific> transpacifics;

    public TranspacificAdapter(ArrayList<Transpacific> transpacifics) {
        this.transpacifics = transpacifics;
    }

    @Override
    public TranspacificAdapter.TranspacificViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_transpacific, parent, false);
        return new TranspacificAdapter.TranspacificViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TranspacificAdapter.TranspacificViewHolder transViewHolder, int position) {
        final Transpacific transpacific = transpacifics.get(position);

        try {
            transViewHolder.ivFlagIconTrans.setImageResource(transpacific.getFlagIcon());
        } catch (Exception e) {}

        transViewHolder.tvDofTranpsCode.setText(transpacific.getTipTranspacificADofDate());
        transViewHolder.tvTransCountryCode.setText(transpacific.getTipTranspacificAFreeCountryName());
        transViewHolder.tvAdvaloremTranspCode.setText(transpacific.getTranspacificAAdvalCountryAmount());
    }

    @Override
    public int getItemCount() {
        return transpacifics.size();
    }

    public static class TranspacificViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFlagIconTrans;
        private TextView  tvDofTranpsCode;
        private TextView  tvTransCountryCode;
        private TextView  tvAdvaloremTranspCode;

        public TranspacificViewHolder (View itemView) {
            super(itemView);
            ivFlagIconTrans         = (ImageView) itemView.findViewById(R.id.ivFlagIconTrans);
            tvDofTranpsCode         = (TextView)  itemView.findViewById(R.id.tvDofTranpsCode);
            tvTransCountryCode      = (TextView)  itemView.findViewById(R.id.tvTransCountryCode);
            tvAdvaloremTranspCode   = (TextView)  itemView.findViewById(R.id.tvAdvaloremTranspCode);
        }
    }

}
