package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.Prosec;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by desarrolloweb on 11/01/17.
 */
public class ProsecAdapter extends RecyclerView.Adapter<ProsecAdapter.ProsecViewHolder> {
    Activity activity;
    List<Prosec> prosecList;

    public static class ProsecViewHolder extends RecyclerView.ViewHolder {
        private TextView  prosecDescriptionName;
        private TextView  prosecPreferenceAdualRate;
        private TextView  prosecNoteDescription;
        private TextView  prosecPreferenceAgreementDate;

        public ProsecViewHolder(View itemView) {
            super(itemView);

            prosecDescriptionName           = (TextView)  itemView.findViewById(R.id.prosecDescriptionName);
            prosecPreferenceAdualRate       = (TextView)  itemView.findViewById(R.id.prosecPreferenceAdualRate);
            prosecNoteDescription           = (TextView)  itemView.findViewById(R.id.prosecNoteDescription);
            prosecPreferenceAgreementDate   = (TextView)  itemView.findViewById(R.id.prosecPreferenceAgreementDate);
        }
    }

    public ProsecAdapter(List<Prosec> prosecList) {
        this.prosecList = prosecList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProsecAdapter.ProsecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_prosec, parent, false);
        return new ProsecViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProsecAdapter.ProsecViewHolder prosecViewHolder, int position) {
        final Prosec prosec = prosecList.get(position);
        String text2 = "";

        try {
            text2 = new String(prosec.getProsecNoteDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            prosecViewHolder.prosecNoteDescription.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = prosec.getProsecNoteDescription();
            prosecViewHolder.prosecNoteDescription.setText(text2);
        }

        prosecViewHolder.prosecDescriptionName.setText(prosec.getProsecDescriptionName());
        prosecViewHolder.prosecPreferenceAdualRate.setText(prosec.getProsecPreferenceAdualRate());
        prosecViewHolder.prosecPreferenceAgreementDate.setText(prosec.getProsecPreferenceAgreementDate());
    }

    @Override
    public int getItemCount() {
        return prosecList.size();
    }

}
