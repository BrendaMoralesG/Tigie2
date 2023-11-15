package com.example.sistemascasa.tigie.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.activities.AnnexActivity;
import com.example.sistemascasa.tigie.activities.BorderStripActivity;
import com.example.sistemascasa.tigie.activities.EstimatedPricesActivity;
import com.example.sistemascasa.tigie.FragmentsActivity.ImportPermitActivity;
import com.example.sistemascasa.tigie.FragmentsActivity.OtherTaxesActivity;
import com.example.sistemascasa.tigie.FragmentsActivity.ProsecActivity;
import com.example.sistemascasa.tigie.FragmentsActivity.TlcNoteActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.CompensatoriesActivity;
import com.example.sistemascasa.tigie.activities.CorrelationsActivity;
import com.example.sistemascasa.tigie.pojo.ModulesData;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 8/02/17.
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    ArrayList<ModulesData> modulesArrayList;
    Activity activity;
    Integer valTigie;

    public ModuleAdapter(ArrayList<ModulesData> modulesArrayList, Activity activity, Integer valTigie) {
        this.modulesArrayList = modulesArrayList;
        this.activity = activity;
        this.valTigie = valTigie;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_modules, parent, false);
        return new ModuleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder moduleViewHolder, int position) {
        final ModulesData modules = modulesArrayList.get(position);

        moduleViewHolder.moduleTitle.setText(modules.getTitle());
        moduleViewHolder.imageModule.setImageResource(modules.getImage());

        moduleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String module = modules.getModule();

                switch (module) {
                    case "compensatoryshares":
                        Intent intentComp = new Intent(activity, CompensatoriesActivity.class);
                        intentComp.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentComp.putExtra("fractionCode", modules.getFraction());
                        intentComp.putExtra("valTigie", valTigie);
                        activity.startActivity(intentComp);
                        break;

                    case "prosec":
                        Intent intenProsec = new Intent(activity, ProsecActivity.class);
                        intenProsec.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intenProsec.putExtra("fractionCode", modules.getFraction());
                        intenProsec.putExtra("valTigie", valTigie);
                        activity.startActivity(intenProsec);
                        break;

                    case "annexes":
                        Intent intentAnnex = new Intent(activity, AnnexActivity.class);
                        intentAnnex.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentAnnex.putExtra("fractionCode", modules.getFraction());
                        intentAnnex.putExtra("valTigie", valTigie);
                        activity.startActivity(intentAnnex);
                        break;

                    case "estimatedprices":
                        Intent intentEstim = new Intent(activity, EstimatedPricesActivity.class);
                        intentEstim.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentEstim.putExtra("fractionCode", modules.getFraction());
                        intentEstim.putExtra("valTigie", valTigie);
                        activity.startActivity(intentEstim);
                        break;

                    case "importpermits":
                        Intent intentImp = new Intent(activity, ImportPermitActivity.class);
                        intentImp.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentImp.putExtra("fractionCode", modules.getFraction());
                        intentImp.putExtra("valTigie", valTigie);
                        activity.startActivity(intentImp);
                        break;

                    case "borderstrip":
                        Intent intentBorder = new Intent(activity, BorderStripActivity.class);
                        intentBorder.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentBorder.putExtra("fractionCode", modules.getFraction());
                        intentBorder.putExtra("valTigie", valTigie);
                        activity.startActivity(intentBorder);
                        break;

                    case "tlcnotes":
                        Intent intentTlcNote = new Intent(activity, TlcNoteActivity.class);
                        intentTlcNote.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentTlcNote.putExtra("fractionCode", modules.getFraction());
                        intentTlcNote.putExtra("valTigie", valTigie);
                        activity.startActivity(intentTlcNote);
                        break;

                    case "othertaxes":
                        Intent intentOther = new Intent(activity, OtherTaxesActivity.class);
                        intentOther.putExtra("id_fraccion", String.valueOf(modules.getId_fraccion()));
                        intentOther.putExtra("fractionCode", modules.getFraction());
                        intentOther.putExtra("valTigie", valTigie);
                        activity.startActivity(intentOther);
                        break;

                    case "other":
                        Intent intentCorrelations = new Intent(activity, CorrelationsActivity.class);
                        intentCorrelations.putExtra("id_fraccion",modules.getId_fraccion());
                        activity.startActivity(intentCorrelations);
                        break;

                    default:
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modulesArrayList.size();
    }

    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        private TextView moduleTitle;
        private ImageView imageModule;

        public ModuleViewHolder(View itemView) {
            super(itemView);
            moduleTitle  = (TextView)  itemView.findViewById(R.id.moduleTitle);
            imageModule  = (ImageView) itemView.findViewById(R.id.imageModule);
        }
    }
}
