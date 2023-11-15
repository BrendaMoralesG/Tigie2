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
import com.example.sistemascasa.tigie.pojo.Annexes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by desarrolloweb on 12/01/17.
 */
public class AnnexesAdapter extends RecyclerView.Adapter<AnnexesAdapter.AnnexesViewHolder> {
    ArrayList<Annexes> annexes;
    Activity activity;

    public AnnexesAdapter(ArrayList<Annexes> annexes, Activity activity) {
        this.annexes = annexes;
        this.activity = activity;
    }

    @Override
    public AnnexesAdapter.AnnexesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_annexes, parent, false); //Cual sera el layout que estara reciclando la lista
        return new AnnexesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnnexesAdapter.AnnexesViewHolder annexesViewholder, int position) {
        final Annexes annexe = annexes.get(position);

        String text = null;
        String text2 = null;
        String text3 = null;

        try {
            text = new String(annexe.getTitle().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text).toString();
            annexesViewholder.title.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text = annexe.getTitle();
            annexesViewholder.title.setText(text);
        }

        try {
            text2 = new String(annexe.getAnnexDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            annexesViewholder.annexDescription.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = annexe.getAnnexDescription();
            annexesViewholder.annexDescription.setText(text2);
        }

        try {
            text3 = new String(annexe.getAnnexNoteDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text3).toString();
            annexesViewholder.annexNoteDescription.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text3 = annexe.getAnnexNoteDescription();
            annexesViewholder.annexNoteDescription.setText(text3);
        }

        annexesViewholder.annexPublicationDate.setText(annexe.getAnnexPublicationDate());
        annexesViewholder.annexApplicationDate.setText(annexe.getAnnexApplicationDate());
        annexesViewholder.annexEffectiveDate.setText(annexe.getAnnexEffectiveDate());

        if(annexe.getAnnexApplicationDate().equals("") || annexe.getAnnexApplicationDate() == null) {
            annexesViewholder.iconAnnexAgreement.setVisibility(View.INVISIBLE);
        } else {
            annexesViewholder.iconAnnexAgreement.setVisibility(View.VISIBLE);
        }

        if(annexe.getAnnexEffectiveDate().equals("") || annexe.getAnnexEffectiveDate() == null) {
            annexesViewholder.iconAnnexEffective.setVisibility(View.INVISIBLE);
        } else {
            annexesViewholder.iconAnnexEffective.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return annexes.size();
    }

    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class AnnexesViewHolder extends RecyclerView.ViewHolder {
        private TextView  title;
        private TextView  annexDescription;
        private TextView  annexNoteDescription;
        private TextView  annexPublicationDate;
        private TextView  annexApplicationDate;
        private TextView  annexEffectiveDate;
        private ImageView iconAnnexAgreement;
        private ImageView iconAnnexEffective;

        public AnnexesViewHolder(View itemView) {
            super(itemView);

            title                   = (TextView)  itemView.findViewById(R.id.titleAnnex);
            annexDescription        = (TextView)  itemView.findViewById(R.id.annex);
            annexNoteDescription    = (TextView)  itemView.findViewById(R.id.noteAnnex);
            annexPublicationDate    = (TextView)  itemView.findViewById(R.id.dofAnnex);
            annexApplicationDate    = (TextView)  itemView.findViewById(R.id.applicationAnnex);
            annexEffectiveDate      = (TextView)  itemView.findViewById(R.id.effectivenAnnex);
            iconAnnexAgreement      = (ImageView) itemView.findViewById(R.id.iconAnnexAgreement);
            iconAnnexEffective      = (ImageView) itemView.findViewById(R.id.iconAnnexEffective);
        }
    }
}
