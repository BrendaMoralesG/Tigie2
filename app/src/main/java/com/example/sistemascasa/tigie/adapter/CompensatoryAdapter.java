package com.example.sistemascasa.tigie.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by desarrolloweb on 11/01/17.
 */
public class CompensatoryAdapter  extends RecyclerView.Adapter<CompensatoryAdapter.CompensatoryViewHolder> {

    public static class CompensatoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView flagIconCompe;
        private TextView  nameCountryCompe;
        private TextView  rateTextCompe;
        private TextView  rateCompe;
        private TextView  textCompe;
        private TextView  applicateCompen;
        private TextView  dofCompen;
        private TextView  effectiveCompen;

        private ImageView iconCompeDof;
        private ImageView iconCompeAgreement;
        private ImageView iconCompeEffective;

        public CompensatoryViewHolder(View itemView) {
            super(itemView);
            flagIconCompe            = (ImageView) itemView.findViewById(R.id.flagIconCompe);
            nameCountryCompe         = (TextView)  itemView.findViewById(R.id.nameCountryCompe);
            rateTextCompe            = (TextView)  itemView.findViewById(R.id.rateTextCompe);
            rateCompe                = (TextView)  itemView.findViewById(R.id.rateCompe);
            textCompe                = (TextView)  itemView.findViewById(R.id.textCompe);
            applicateCompen          = (TextView)  itemView.findViewById(R.id.applicateCompen);
            dofCompen                = (TextView)  itemView.findViewById(R.id.dofCompen);
            effectiveCompen          = (TextView)  itemView.findViewById(R.id.effectiveCompen);
            iconCompeDof            = (ImageView) itemView.findViewById(R.id.iconCompeDof);
            iconCompeAgreement      = (ImageView) itemView.findViewById(R.id.iconCompeAgreement);
            iconCompeEffective      = (ImageView) itemView.findViewById(R.id.iconCompeEffective);
        }
    }

    List<CompensatoryShares> compensatorySharesList;

    public CompensatoryAdapter(List<CompensatoryShares> compensatorySharesList){
        this.compensatorySharesList = compensatorySharesList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public CompensatoryAdapter(ArrayList<CompensatoryShares> compensatoryShares) {
        this.compensatorySharesList = compensatoryShares;
    }

    @Override
    public CompensatoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_compensatory, parent, false);
        return new CompensatoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CompensatoryViewHolder compensatoryViewHolder, int position) {
        final CompensatoryShares compensatoryShare = compensatorySharesList.get(position);
        String text2 = "";

        try {
            text2 = new String(compensatoryShare.getCompensatoryShareMerchandiseDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            compensatoryViewHolder.textCompe.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = compensatoryShare.getCompensatoryShareMerchandiseDescription();
            compensatoryViewHolder.textCompe.setText(text2);
        }

        compensatoryViewHolder.flagIconCompe.setImageResource(compensatoryShare.getCompensatoryFlag());
        compensatoryViewHolder.nameCountryCompe.setText(compensatoryShare.getCompensatoryShareCountry());
        compensatoryViewHolder.rateTextCompe.setText(compensatoryShare.getCompensatoryShareTypeDescription());
        compensatoryViewHolder.rateCompe.setText(compensatoryShare.getCompensatoryShareTasa());
        compensatoryViewHolder.applicateCompen.setText(compensatoryShare.getCompensatoryShareAplicationDate());
        compensatoryViewHolder.dofCompen.setText(compensatoryShare.getCompensatorySharePublicationDate());
        compensatoryViewHolder.effectiveCompen.setText(compensatoryShare.getCompensatoryShareEffectiveDate());


        if (compensatoryShare.getCompensatorySharePublicationDate() == null || compensatoryShare.getCompensatorySharePublicationDate().equals("")) {
            compensatoryViewHolder.iconCompeDof.setVisibility(View.INVISIBLE);
        } else {
            compensatoryViewHolder.iconCompeDof.setVisibility(View.VISIBLE);
        }

        if(compensatoryShare.getCompensatoryShareAplicationDate() == null || compensatoryShare.getCompensatoryShareAplicationDate().equals("")) {
            compensatoryViewHolder.iconCompeAgreement.setVisibility(View.INVISIBLE);
        } else {
            compensatoryViewHolder.iconCompeAgreement.setVisibility(View.VISIBLE);
        }

        if(compensatoryShare.getCompensatoryShareEffectiveDate() == null || compensatoryShare.getCompensatoryShareEffectiveDate().equals("")) {
            compensatoryViewHolder.iconCompeEffective.setVisibility(View.INVISIBLE);
        } else {
            compensatoryViewHolder.iconCompeEffective.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return compensatorySharesList.size();
    }

}
