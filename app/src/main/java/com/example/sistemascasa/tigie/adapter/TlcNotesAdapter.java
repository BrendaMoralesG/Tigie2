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
import com.example.sistemascasa.tigie.pojo.TlcNotes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by desarrolloweb on 12/01/17.
 */
public class TlcNotesAdapter extends RecyclerView.Adapter<TlcNotesAdapter.TlcNotesViewHolder> {
    ArrayList<TlcNotes> tlcNotes;
    Activity activity;


    public TlcNotesAdapter(ArrayList<TlcNotes> tlcNotes, Activity activity) {
        this.tlcNotes = tlcNotes;
        this.activity = activity;
    }

    @Override
    public TlcNotesAdapter.TlcNotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tlcnote, parent, false);
        return new TlcNotesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TlcNotesAdapter.TlcNotesViewHolder tlcNotesViewHolder, int position) {
        final TlcNotes tlcNote = tlcNotes.get(position);
        String text2 = "";

        try {
            text2 = new String(tlcNote.getTlcNoteDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            tlcNotesViewHolder.tlcNoteDescription.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = tlcNote.getTlcNoteDescription();
            tlcNotesViewHolder.tlcNoteDescription.setText(text2);
        }

        tlcNotesViewHolder.tlcNoteFreeCountryCode.setImageResource(tlcNote.getIvFlagTlcNote());
        tlcNotesViewHolder.tlcNoteTitle.setText(tlcNote.getTlcNoteTitle());
        tlcNotesViewHolder.tlcNoteAplicationDate.setText(tlcNote.getTlcNoteAplicationDate());
        tlcNotesViewHolder.tlcNoteEfectiveDate.setText(tlcNote.getTlcNoteEfectiveDate());

        if (tlcNote.getTlcNoteAplicationDate().equals("") || tlcNote.getTlcNoteAplicationDate() == null) {
            tlcNotesViewHolder.iconTlcAgreement.setVisibility(View.INVISIBLE);
        } else {
            tlcNotesViewHolder.iconTlcAgreement.setVisibility(View.VISIBLE);
        }

        if (tlcNote.getTlcNoteEfectiveDate().equals("") ||tlcNote.getTlcNoteEfectiveDate() == null) {
            tlcNotesViewHolder.iconTlcEffective.setVisibility(View.INVISIBLE);
        } else {
            tlcNotesViewHolder.iconTlcEffective.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tlcNotes.size();
    }


    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class TlcNotesViewHolder extends RecyclerView.ViewHolder {
        private ImageView tlcNoteFreeCountryCode;
        private TextView  tlcNoteTitle;
        private TextView  tlcNoteDescription;
        private TextView  tlcNoteAplicationDate;
        private TextView  tlcNoteEfectiveDate;
        private ImageView iconTlcAgreement;
        private ImageView iconTlcEffective;

        public TlcNotesViewHolder(View itemView) {
            super(itemView);
            tlcNoteFreeCountryCode    = (ImageView) itemView.findViewById(R.id.tlcNoteFreeCountryCode);
            tlcNoteTitle              = (TextView)  itemView.findViewById(R.id.tlcNoteTitle);
            tlcNoteDescription        = (TextView)  itemView.findViewById(R.id.tlcNoteDescription);
            tlcNoteAplicationDate     = (TextView)  itemView.findViewById(R.id.tlcNoteAplicationDate);
            tlcNoteEfectiveDate       = (TextView)  itemView.findViewById(R.id.tlcNoteEfectiveDate);
            iconTlcAgreement          = (ImageView) itemView.findViewById(R.id.iconTlcAgreement);
            iconTlcEffective          = (ImageView) itemView.findViewById(R.id.iconTlcEffective);
        }
    }
}
