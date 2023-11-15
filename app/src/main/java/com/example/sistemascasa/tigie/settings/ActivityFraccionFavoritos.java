package com.example.sistemascasa.tigie.settings;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.FractionFavAdapater;
import com.example.sistemascasa.tigie.fragments.IRecyclerview_Fractions_Fav;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.presentador.IRecyclerViewFragmentFractionFavPre;
import com.example.sistemascasa.tigie.presentador.RecyclerViewFragmentFractionFavPre;

import java.util.ArrayList;

public class ActivityFraccionFavoritos extends AppCompatActivity implements IRecyclerview_Fractions_Fav {

    private RecyclerView listFavFractions;
    public static ActivityFraccionFavoritos instance = null;
    private IRecyclerViewFragmentFractionFavPre presenter;
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraccion_favoritos);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listFavFractions = (RecyclerView) findViewById(R.id.rvFraccionFavourites);
        presenter    = new RecyclerViewFragmentFractionFavPre(this,  getApplicationContext());

        Bundle parametros       = getIntent().getExtras();
        if (parametros !=  null) {
            valTigie        = parametros.getInt("valTigie");
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listFavFractions.setLayoutManager(llm);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public FractionFavAdapater crearAdaptador(ArrayList<Fractions> fractionses) {
        FractionFavAdapater adaptader = new FractionFavAdapater(fractionses, this, getApplicationContext(), valTigie);
        return adaptader;
    }

    @Override
    public void inicializarAdaptadorRV(FractionFavAdapater adapter) {
        listFavFractions.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        instance = this;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        instance = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        instance = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}
