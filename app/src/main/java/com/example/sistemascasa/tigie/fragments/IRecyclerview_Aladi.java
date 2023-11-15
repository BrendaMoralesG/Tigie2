package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.AladiAdapter;
import com.example.sistemascasa.tigie.pojo.Aladi;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 18/08/16.
 */
public interface IRecyclerview_Aladi {

    public AladiAdapter crearAdaptador(ArrayList<Aladi> aladis);

    public void inicializarAdaptadorRV (AladiAdapter adapter); //Recibiendo Adaptador

    public void generarLinearLayoutVertical();

    public void mandarErrorAladi();
}
