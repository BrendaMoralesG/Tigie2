package com.example.sistemascasa.tigie.presentador;

import com.example.sistemascasa.tigie.adapter.CorrelationAdapter;
import com.example.sistemascasa.tigie.pojo.Correlations;

import java.util.ArrayList;

public interface IRecyclerView_Correlations {
    public CorrelationAdapter crearAdaptador(ArrayList<Correlations> correlations);

    public void inicializarAdaptadorRV (CorrelationAdapter adapter);

    public void generarGridLayout();
}
