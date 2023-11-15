package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.EstimatedPrices;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by desarrolloweb on 11/01/17.
 */
public class EstimatedPricesAdapter extends RecyclerView.Adapter<EstimatedPricesAdapter.EstimatedPricesViewHolder> {
    ArrayList<EstimatedPrices> estimatedPrices;
    Activity activity;

    public EstimatedPricesAdapter(ArrayList<EstimatedPrices> estimatedPrices, Activity activity) {
        this.estimatedPrices = estimatedPrices;
        this.activity = activity;
    }

    @Override
    public EstimatedPricesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_estimated, parent, false); //Cual sera el layout que estara reciclando la lista
        return new EstimatedPricesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EstimatedPricesViewHolder estimatedPricesViewHolder, int position) {
        final EstimatedPrices estimatedPrice = estimatedPrices.get(position);
        String text2 = "";

        if (estimatedPrice.getNico() != null) {
            estimatedPricesViewHolder.lyEstimatedPriceNico.setVisibility(View.VISIBLE);
            estimatedPricesViewHolder.tvEstimatedPriceNicoDesc.setText(estimatedPrice.getNico());
        }

        try {
            text2 = new String(estimatedPrice.getEstimatedPriceNote().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            estimatedPricesViewHolder.estimatedPriceNote.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = estimatedPrice.getEstimatedPriceNote();
            estimatedPricesViewHolder.estimatedPriceNote.setText(text2);
        }

        estimatedPricesViewHolder.estimatedPrice.setText(estimatedPrice.getEstimatedPrice());
        estimatedPricesViewHolder.estimatedPricePublicationDate.setText(estimatedPrice.getEstimatedPricePublicationDate());
        estimatedPricesViewHolder.estimatedPriceAplicationDate.setText(estimatedPrice.getEstimatedPriceAplicationDate());
        estimatedPricesViewHolder.estimatedPriceEffectiveDate.setText(estimatedPrice.getEstimatedPriceEffectiveDate());

        if (estimatedPrice.getEstimatedPriceAplicationDate().equals("") || estimatedPrice.getEstimatedPriceAplicationDate() == null) {
            estimatedPricesViewHolder.iconEstimatedAgreement.setVisibility(View.INVISIBLE);
        } else {
            estimatedPricesViewHolder.iconEstimatedAgreement.setVisibility(View.VISIBLE);
        }

        if (estimatedPrice.getEstimatedPriceAplicationDate().equals("") || estimatedPrice.getEstimatedPriceAplicationDate() == null) {
            estimatedPricesViewHolder.iconEstimatedEffective.setVisibility(View.INVISIBLE);
        } else {
            estimatedPricesViewHolder.iconEstimatedEffective.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return estimatedPrices.size();
    }


    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class EstimatedPricesViewHolder extends RecyclerView.ViewHolder {
        private TextView  estimatedPrice;
        private TextView  estimatedPriceNote;
        private TextView  estimatedPricePublicationDate;
        private TextView  estimatedPriceAplicationDate;
        private TextView  estimatedPriceEffectiveDate;
        private ImageView iconEstimatedAgreement;
        private ImageView iconEstimatedEffective;
        private LinearLayout lyEstimatedPriceNico;
        private TextView tvEstimatedPriceNicoDesc;

        public EstimatedPricesViewHolder(View itemView) {
            super(itemView);

            estimatedPrice                  = (TextView)  itemView.findViewById(R.id.estimatedPrice);
            estimatedPriceNote              = (TextView)  itemView.findViewById(R.id.estimatedPriceNote);
            estimatedPricePublicationDate   = (TextView)  itemView.findViewById(R.id.estimatedPricePublicationDate);
            estimatedPriceAplicationDate    = (TextView)  itemView.findViewById(R.id.estimatedPriceAplicationDate);
            estimatedPriceEffectiveDate     = (TextView)  itemView.findViewById(R.id.estimatedPriceEffectiveDate);
            iconEstimatedAgreement          = (ImageView) itemView.findViewById(R.id.iconEstimatedAgreement);
            iconEstimatedEffective          = (ImageView) itemView.findViewById(R.id.iconEstimatedEffective);
            lyEstimatedPriceNico            = (LinearLayout) itemView.findViewById(R.id.lyEstimatedPriceNico);
            tvEstimatedPriceNicoDesc        = (TextView) itemView.findViewById(R.id.tvEstimatedPriceNicoDesc);
        }
    }
}
