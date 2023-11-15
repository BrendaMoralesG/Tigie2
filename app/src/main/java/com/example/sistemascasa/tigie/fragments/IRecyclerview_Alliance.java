package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.AllianceAdapter;
import com.example.sistemascasa.tigie.pojo.AlliancePacific;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 20/08/16.
 */
public interface IRecyclerview_Alliance {

    public void generarLinearLayoutVertical();

    public AllianceAdapter crearAdaptador(ArrayList<AlliancePacific> alliancePacifics);

    public void inicializarAdaptadorRV (AllianceAdapter adapter);

    public void mandarErrorAliance ();

}
