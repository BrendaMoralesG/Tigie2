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
import com.example.sistemascasa.tigie.pojo.BorderStrip;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by desarrolloweb on 12/01/17.
 */
public class BorderStripAdapter extends RecyclerView.Adapter<BorderStripAdapter.BorderStripViewHolder> {
    ArrayList<BorderStrip> borderStrips;
    Activity activity;

    public BorderStripAdapter (ArrayList<BorderStrip> borderStrips, Activity activity) {
        this.borderStrips = borderStrips;
        this.activity  = activity;
    }

    @Override
    public BorderStripAdapter.BorderStripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_border, parent, false); //Cual sera el layout que estara reciclando la lista
        return new BorderStripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BorderStripAdapter.BorderStripViewHolder borderViewHolder, int position) {
        final BorderStrip borderStrip = borderStrips.get(position);
        String text2 = "";

        if (borderStrip.getBorderStripNoteDescription() != null) {
            if (! borderStrip.getBorderStripNoteDescription().isEmpty()) {
                borderViewHolder.borderStripAccordanceDescription3.setText(borderStrip.getBorderStripNoteDescription());
            }
        }

        try {
            text2 = new String(borderStrip.getBorderStripAccordanceDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            borderViewHolder.borderStripAccordanceDescription.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = borderStrip.getBorderStripAccordanceDescription();
            borderViewHolder.borderStripAccordanceDescription.setText(text2);
        }

        borderViewHolder.borderStripAccordanceTariffRate.setText(borderStrip.getBorderStripAccordanceTariffRate());
        borderViewHolder.borderStripPreferenceAgreementDate.setText(borderStrip.getBorderStripPreferenceAgreementDate());
        borderViewHolder.borderStripEfectiveDate.setText(borderStrip.getEfectiveDate());

        if(borderStrip.getEfectiveDate().equals("") || borderStrip.getEfectiveDate() == null) {
            borderViewHolder.iconBorderEffective.setVisibility(View.INVISIBLE);
        } else {
            borderViewHolder.iconBorderEffective.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return borderStrips.size();
    }


    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class BorderStripViewHolder extends RecyclerView.ViewHolder {
        private TextView  borderStripAccordanceTariffRate;
        private TextView  borderStripAccordanceDescription;
        private TextView  borderStripPreferenceAgreementDate;
        private TextView  borderStripEfectiveDate;
        private ImageView iconBorderEffective;
        private TextView borderStripAccordanceDescription3;

        public BorderStripViewHolder(View itemView) {
            super(itemView);

            borderStripAccordanceTariffRate         = (TextView)  itemView.findViewById(R.id.borderStripAccordanceTariffRate);
            borderStripAccordanceDescription        = (TextView)  itemView.findViewById(R.id.borderStripAccordanceDescription);
            borderStripAccordanceDescription3        = (TextView)  itemView.findViewById(R.id.borderStripAccordanceDescription3);
            borderStripPreferenceAgreementDate      = (TextView)  itemView.findViewById(R.id.borderStripPreferenceAgreementDate);
            borderStripEfectiveDate                 = (TextView)  itemView.findViewById(R.id.borderStripEfectiveDate);
            iconBorderEffective                     = (ImageView) itemView.findViewById(R.id.iconBorderEffective);
        }
    }
}
