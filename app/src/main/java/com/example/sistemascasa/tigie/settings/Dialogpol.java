package com.example.sistemascasa.tigie.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.sistemascasa.tigie.db.ConstructorData;

public class Dialogpol extends DialogFragment {

    String fraccion = "";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle  argsA     =  getArguments();
        if (argsA != null) {
            fraccion            =  argsA.getString("fraccion");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Está seguro de eliminar la fracción de favoritos?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            ConstructorData constructorData = new ConstructorData(getContext());
                            Boolean resp = constructorData.deleteFavouritesByFrac(fraccion);
                            if (resp) {
                                Toast.makeText(getContext() , "¡ Operación exitosa !", Toast.LENGTH_LONG).show();
                                Intent intent_showfav = new Intent(getContext(), ActivityFraccionFavoritos.class);
                                getContext().startActivity(intent_showfav);
                            }
                        } catch (Exception e) { }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}