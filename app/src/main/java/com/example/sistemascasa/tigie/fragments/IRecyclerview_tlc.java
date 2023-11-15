package com.example.sistemascasa.tigie.fragments;

import com.example.sistemascasa.tigie.adapter.TlcAdapter;
import com.example.sistemascasa.tigie.pojo.Tlc;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 10/08/16.
 */
public interface IRecyclerview_tlc {

    public TlcAdapter crearAdaptador(ArrayList<Tlc> tlcs);

    public void inicializarAdaptadorRV (TlcAdapter adapter); //Recibiendo Adaptador

    public void generarGridLayout();

    public void mandarError();

}
