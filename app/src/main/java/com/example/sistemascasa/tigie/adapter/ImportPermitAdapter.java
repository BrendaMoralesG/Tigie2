package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.FragmentsActivity.Prueba;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.ImportPermits;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by desarrolloweb on 12/01/17.
 */
public class ImportPermitAdapter extends RecyclerView.Adapter<ImportPermitAdapter.ImportPermitViewHolder> {
    ArrayList<ImportPermits> importPermits;
    Activity activity;
    Context context;

    public ImportPermitAdapter(ArrayList<ImportPermits> importPermits, Activity activity,  Context context) {
        this.importPermits = importPermits;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public ImportPermitAdapter.ImportPermitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_import, parent, false);
        return new ImportPermitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImportPermitAdapter.ImportPermitViewHolder importPermitViewHolder, int position) {
        final ImportPermits importPermit = importPermits.get(position);

        String text  = "";
        String text2 = "";
        String text3 = "";

        try {
            text = new String(importPermit.getImportPermitTitle().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text).toString();
            importPermitViewHolder.importPermitTitle.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text = importPermit.getImportPermitTitle();
            importPermitViewHolder.importPermitTitle.setText(text);
        }

        try {
            text2 = new String(importPermit.getImportPermitDescription().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text2).toString();
            importPermitViewHolder.importPermitDescription.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text2 = importPermit.getImportPermitDescription();
            importPermitViewHolder.importPermitDescription.setText(text2);
        }

        try {
            text3 = new String(importPermit.getImportPermitNote().getBytes(), "UTF-8");
            String encodedText = Html.fromHtml(text3).toString();
            importPermitViewHolder.importPermitNote.setText(encodedText);
        } catch (UnsupportedEncodingException e) {
            text3 = importPermit.getImportPermitNote();
            importPermitViewHolder.importPermitNote.setText(text3);
        }

        if (importPermit.getArcticle() != null) {
            String htmlString="<u>"+ importPermit.getArcticle() +"</u>" + "<br>" + importPermit.getArcticleDesc();
            importPermitViewHolder.arcticleDesc.setText(Html.fromHtml(htmlString));
            importPermitViewHolder.iconImportArcticle.setVisibility(View.VISIBLE);
            importPermitViewHolder.tvArticleImp.setVisibility(View.VISIBLE);

            importPermitViewHolder.arcticleDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readData(context, importPermit.getArcticleDesc());
                    recuperar(importPermit.getLegislation());
                }
            });
        } else {
            importPermitViewHolder.iconImportArcticle.setVisibility(View.INVISIBLE);
            importPermitViewHolder.tvArticleImp.setVisibility(View.INVISIBLE);
        }

        importPermitViewHolder.importPermitPublicationDate.setText(importPermit.getImportPermitPublicationDate());
        importPermitViewHolder.importPermitAplicationDate.setText(importPermit.getImportPermitAplicationDate());
        importPermitViewHolder.importPermitEffectiveDate.setText(importPermit.getImportPermitEffectiveDate());

        if (importPermit.getImportPermitNote().equals("") || importPermit.getImportPermitNote() == null || importPermit.getImportPermitNote().equals("\r\n") ) {
            importPermitViewHolder.iconImportNote.setVisibility(View.INVISIBLE);
            importPermitViewHolder.tvImportPermitNote.setVisibility(View.INVISIBLE);
        } else {
            importPermitViewHolder.iconImportNote.setVisibility(View.VISIBLE);
            importPermitViewHolder.tvImportPermitNote.setVisibility(View.INVISIBLE);
        }

        if (importPermit.getImportPermitEffectiveDate().equals("") || importPermit.getImportPermitEffectiveDate() == null) {
            importPermitViewHolder.iconImportApplication.setVisibility(View.INVISIBLE);
        } else {
            importPermitViewHolder.iconImportApplication.setVisibility(View.VISIBLE);
        }

        if (importPermit.getImportPermitPublicationDate().equals("") || importPermit.getImportPermitPublicationDate() == null) {
            importPermitViewHolder.iconImportDof.setVisibility(View.INVISIBLE);
        } else {
            importPermitViewHolder.iconImportDof.setVisibility(View.VISIBLE);
        }

        if (importPermit.getImportPermitEffectiveDate().equals("") || importPermit.getImportPermitEffectiveDate() == null) {
            importPermitViewHolder.iconImportEffective.setVisibility(View.INVISIBLE);
        } else {
            importPermitViewHolder.iconImportEffective.setVisibility(View.VISIBLE);
        }
    }

    public void recuperar(String legisl) {
        File outFile = new File( context.getFilesDir().getPath()  +"/hello_file.html");
        String path = outFile.getAbsolutePath();;

        Intent intentImp = new Intent(activity, Prueba.class);
        intentImp.putExtra("legisl", legisl);
        intentImp.putExtra("path", path);
        activity.startActivity(intentImp);
    }


    public void readData( Context myContext, String content)  {
        String file_name = "hello_file.html";

        content = "<html><head><style>" +
                ".anchor { color: #0000FF; text-decoration: none; cursor: pointer;}\n" +
                ".titulo2 { color: #00a2c5; font-style: italic; font-weight: bold; text-align: center; font-size: 11pt;}\n" +
                ".sangria { margin-left: 73pt; margin-right: 93pt;}\n"+
                ".sangria1 { margin-left: 20pt; margin-right: 20pt;}\n" +
                ".sangria2 { margin-left: 40pt; margin-right: 40pt;}\n" +
                ".titulo1 { color: #2b4c65; text-align: center; font-style: italic; font-weight: bold;  font-size: 12pt;}\n"  +
                ".nota { font-size: 7pt; color: #800000; text-align: justify; font-family: Verdana;}\n" +
                "table { font-family: Verdana; text-align: justify;  font-size: 10pt;}\n" +
                ".indices { color: #333333; text-align: justify; font-family: Tahoma; font-size: 8pt;}\n" +
                ".titulos { color: #3F51B5; text-align: center; font-weight: bold; background-color: #359ac6; font-family: Arial; font-size: 11pt;}\n"+
                ".subtitulos { color: #008b8b; text-align: center; font-weight: bold; background-color: #9ACCE2; font-family: Arial; font-size: 10pt;}\n"+
                ".capitulos { color: #03A9F4; text-align: center; font-weight: bold; background-color: #a5d3e7; font-family: Arial; font-size: 10pt;}\n" +
                ".secciones { color: #333333; text-align: center; font-weight: bold; background-color: #94DBFF; font-family: Arial; font-size: 10pt;}\n"+
                ".anexos { color: #333333; text-align: center; font-weight: bold; background-color: #C2E1EE; font-family: Arial;  font-size: 10pt;}\n"+
                ".apendices { color: #333333; text-align: center; font-weight: bold; background-color: #DDECFC; font-family: Arial;}\n"+
                ".negrita_i { text-align: left; font-weight: bold; font-size: 10pt;}\n"+
                ".negrita_c { text-align: center; font-weight: bold; font-size: 10pt;\t}\n"+
                ".negrita_d { text-align: right; font-weight: bold; font-size: 10pt;}\n"+
                ".negrita_j { text-align: justify; font-weight: bold; font-size: 10pt;}\n"+
                "body { font-family: Verdana; font-size: 10pt; line-height: normal; text-align: justify; color: #2b4c65;margin-right: 3mm;}\n"+
                "b { color: #666666;}\n"+
                ".nota_c { font-size: 7pt; color: #800000; text-align: center; font-family: Verdana;}\n"+
                ".indice_izq { font-size: 8pt; color: #333333; text-align: justify; font-family: Tahoma; background-color: #dde0e5; border: 1px solid #193833;}\n"+
                ".titulo_izq {font-size: 9pt;color: #008b8b;text-align: center;font-weight: bold;background-color: #999fab;font-family: Arial;}\n"+
                "hr {color: #CCCC99; border-top: 1px none #CCCC99; border-right: 1px none #CCFF99; border-bottom: 1px none #CCFF99; border-left: 1px none #CCFF99; font-size: 10pt;}\n"+
                ".reforma { color: #FF8000;}\n"+
                ".adiciona { color: #008000;}\n"+
                ".deroga {color: #800000; text-decoration: line-through;}\n"+
                "#images img {  margin: 15px;}\n"+
                ".name{\t font-size:14px !important;  color:#000022;\t} \n"+
                ".inciso { font-weight: bold; font-size: 10pt; color: #03A9F4; text-transform: none;} </head></style>" + content + " <body> </body></html>";

        try {
            FileOutputStream fileOutputStream = myContext.openFileOutput(file_name, Context.MODE_PRIVATE );
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException ex)  {

        } catch (IOException e)  {

        }
    }

    @Override
    public int getItemCount() {
        return importPermits.size();
    }

    /** Declara viesws
     * Metodo Constructor
     ***/
    public static class ImportPermitViewHolder extends RecyclerView.ViewHolder {

        private TextView  importPermitTitle;
        private TextView  importPermitDescription;
        private TextView  importPermitNote;
        private TextView  importPermitPublicationDate;
        private TextView  importPermitAplicationDate;
        private TextView  importPermitEffectiveDate;
        private TextView  arcticleDesc;
        private TextView  tvArticleImp;
        private ImageView iconImportEffective;
        private ImageView iconImportApplication;
        private ImageView iconImportDof;
        private ImageView iconImportNote;
        private ImageView iconImportArcticle;
        private TextView tvImportPermitNote;

        public ImportPermitViewHolder(View itemView) {
            super(itemView);

            importPermitTitle           = (TextView)  itemView.findViewById(R.id.importPermitTitle);
            importPermitDescription     = (TextView)  itemView.findViewById(R.id.importPermitDescription);
            importPermitNote            = (TextView)  itemView.findViewById(R.id.importPermitNote);

            importPermitPublicationDate = (TextView)  itemView.findViewById(R.id.importPermitPublicationDate);
            importPermitAplicationDate  = (TextView)  itemView.findViewById(R.id.importPermitAplicationDate);
            importPermitEffectiveDate   = (TextView)  itemView.findViewById(R.id.importPermitEffectiveDate);

            iconImportEffective         = (ImageView) itemView.findViewById(R.id.iconImportEffective);
            iconImportApplication       = (ImageView) itemView.findViewById(R.id.iconImportApplication);
            iconImportDof               = (ImageView) itemView.findViewById(R.id.iconImportDof);
            iconImportNote              = (ImageView) itemView.findViewById(R.id.iconImportNote);
            iconImportArcticle          = (ImageView) itemView.findViewById(R.id.iconImportArcticle);

            tvArticleImp                = (TextView)  itemView.findViewById(R.id.tvArticleImp);
            arcticleDesc                = (TextView)  itemView.findViewById(R.id.arcticleDesc);
            tvImportPermitNote          = (TextView)  itemView.findViewById(R.id.tvImportPermitNote);
        }
    }
}
