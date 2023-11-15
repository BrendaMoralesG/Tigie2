package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.OtherTaxes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by desarrolloweb on 7/02/17.
 */
public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.OtherViewHolder> {
    ArrayList<OtherTaxes> otherTaxes;
    Activity activity;

    public OtherAdapter(ArrayList<OtherTaxes> otherTaxes, Activity activity) {
        this.otherTaxes = otherTaxes;
        this.activity = activity;
    }

    @Override
    public OtherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_othertaxes, parent, false);
        return new OtherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OtherViewHolder otherViewHolder, int position) {
        final OtherTaxes otherTaxe = otherTaxes.get(position);
        String text = "";
        String text2 = "";

        if (otherTaxe.getIvaNoteDescription() != null) {
            otherViewHolder.ivaNoteDescription.setText(otherTaxe.getIvaNoteDescription());
        }

        try {
            text = new String(otherTaxe.getTitleOtherTaxes().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text).toString();
            otherViewHolder.titleOtherTaxes.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text = otherTaxe.getTitleOtherTaxes();
            otherViewHolder.titleOtherTaxes.setText(text);
        }

        try {
            text2 = new String(otherTaxe.getDescriptionOtherTaxes().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            otherViewHolder.descriptionOtherTaxes.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = otherTaxe.getDescriptionOtherTaxes();
            otherViewHolder.descriptionOtherTaxes.setText(text2);
        }

        otherViewHolder.publicationDateOtherTaxes.setText(otherTaxe.getPublicationDateOtherTaxes());
        otherViewHolder.aplicationDateOtherTaxes.setText(otherTaxe.getAplicationDateOtherTaxes());
        otherViewHolder.effectiveDateOtherTaxes.setText(otherTaxe.getEffectiveDateOtherTaxes());

        if (otherTaxe.getPublicationDateOtherTaxes().equals("") || otherTaxe.getPublicationDateOtherTaxes() == null) {
            otherViewHolder.iconOtherDof.setVisibility(View.INVISIBLE);
        } else {
            otherViewHolder.iconOtherDof.setVisibility(View.VISIBLE);
        }

        if (otherTaxe.getAplicationDateOtherTaxes().equals("") || otherTaxe.getAplicationDateOtherTaxes() == null) {
            otherViewHolder.iconOtherAgreement.setVisibility(View.INVISIBLE);
        } else {
            otherViewHolder.iconOtherAgreement.setVisibility(View.VISIBLE);
        }

        if (otherTaxe.getEffectiveDateOtherTaxes().equals("") || otherTaxe.getEffectiveDateOtherTaxes() == null) {
            otherViewHolder.iconAOtherEffective.setVisibility(View.INVISIBLE);
        } else {
            otherViewHolder.iconAOtherEffective.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return otherTaxes.size();
    }


    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        private TextView  titleOtherTaxes;
        private TextView  descriptionOtherTaxes;
        private TextView  publicationDateOtherTaxes;
        private TextView  aplicationDateOtherTaxes;
        private TextView  effectiveDateOtherTaxes;
        private ImageView iconOtherDof;
        private ImageView iconAOtherEffective;
        private ImageView iconOtherAgreement;
        private TextView ivaNoteDescription;

        public OtherViewHolder(View itemView) {
            super(itemView);

            titleOtherTaxes              = (TextView)  itemView.findViewById(R.id.titleOtherTaxes);
            descriptionOtherTaxes        = (TextView)  itemView.findViewById(R.id.descriptionOtherTaxes);
            publicationDateOtherTaxes    = (TextView)  itemView.findViewById(R.id.publicationDateOtherTaxes);
            aplicationDateOtherTaxes     = (TextView)  itemView.findViewById(R.id.aplicationDateOtherTaxes);
            effectiveDateOtherTaxes      = (TextView)  itemView.findViewById(R.id.effectiveDateOtherTaxes);
            iconOtherDof                 = (ImageView) itemView.findViewById(R.id.iconOtherDof);
            iconAOtherEffective          = (ImageView) itemView.findViewById(R.id.iconAOtherEffective);
            iconOtherAgreement           = (ImageView) itemView.findViewById(R.id.iconOtherAgreement);
            ivaNoteDescription           = (TextView) itemView.findViewById(R.id.ivaNoteDescription);
        }
    }
}
