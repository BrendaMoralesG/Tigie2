package com.example.sistemascasa.tigie.presentador;

import android.content.Context;

import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fractions_Fav;
import com.example.sistemascasa.tigie.pojo.Fractions;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 30/08/16.
 */
public class RecyclerViewFragmentFractionFavPre implements IRecyclerViewFragmentFractionFavPre {
        private IRecyclerview_Fractions_Fav iRecyclerview_fractions_fav;
    private Context context;
    private ConstructorData constructorData;
    private ArrayList<Fractions> fractions;

    public RecyclerViewFragmentFractionFavPre(IRecyclerview_Fractions_Fav iRecyclerview_fractions_fav, Context context) {
        this.iRecyclerview_fractions_fav = iRecyclerview_fractions_fav;
        this.context = context;
        getFractionsFavBD();
    }

    @Override
    public void getFractionsFavBD() {
        constructorData = new ConstructorData(context);
        fractions = constructorData.getFractionsFav();
        showFractionFavDataRV();
    }

    @Override
    public void showFractionFavDataRV() {
        iRecyclerview_fractions_fav.inicializarAdaptadorRV(iRecyclerview_fractions_fav.crearAdaptador(fractions));
        iRecyclerview_fractions_fav.generarLinearLayoutVertical();
    }
}
