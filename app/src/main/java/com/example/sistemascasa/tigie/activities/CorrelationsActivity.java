package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.CorrelationAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Correlations;
import com.example.sistemascasa.tigie.presentador.IRecyclerView_Correlations;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CorrelationsActivity extends AppCompatActivity implements IRecyclerView_Correlations {

    private Context contexto;
    private Toolbar toolbar;
    private ConstructorData constructorData;
    private Integer id_fraccion;
    private Activity activity;
    private RecyclerView listCorrelations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correlations);

        contexto = getApplicationContext();
        activity = this;
        constructorData = new ConstructorData(contexto);
        listCorrelations      =   (RecyclerView)  findViewById(R.id.rvCorrelations);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros       = getIntent().getExtras();
        toolbar             = (Toolbar) findViewById(R.id.miActionBar);
        if (parametros !=  null) {
            id_fraccion  = parametros.getInt("id_fraccion");
            getCorrelations(id_fraccion);
        }

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void getCorrelations (Integer id_fraccion) {
        try {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsService endService = restApiAdapter.establecerConexionRestApi();
            ArrayList<Object> gotData = new ArrayList<Object>();
            BaseDatos dataBase = new BaseDatos(contexto);
            gotData = dataBase.getUserData();
            String email = "";
            String token = "";

            if (gotData != null) {
                int listSize = gotData.size();
                if(listSize > 0) {
                    email = gotData.get(0).toString();
                    token = gotData.get(1).toString();
                }
            }
            Call<ArrayList<Correlations>> service = endService.getCorrelations(id_fraccion, email, token);

            service.enqueue(new Callback<ArrayList<Correlations>>() {
                @Override
                public void onResponse(Call<ArrayList<Correlations>> call, Response<ArrayList<Correlations>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError();
                        } else {
                            ArrayList<Correlations> correlations = response.body();
                            if (correlations.size() > 0) {
                                generarGridLayout();
                                inicializarAdaptadorRV(crearAdaptador(correlations));
                            }
                        }
                    } catch (IOError e) {
                        sentMessageError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Correlations>> call, Throwable t) {
                    sentMessageError();
                }
            });
        } catch (Exception e) { }
    }

    public void sentMessageError() {
        Toast.makeText(contexto, "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

    @Override
    public CorrelationAdapter crearAdaptador(ArrayList<Correlations> correlations) {
        CorrelationAdapter adapter = new CorrelationAdapter(correlations, this);
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(CorrelationAdapter adapter) {
        listCorrelations.setAdapter(adapter);
    }

    @Override
    public void generarGridLayout() {
        if (esTablet(contexto)) {
            GridLayoutManager glm = new GridLayoutManager(this,2);
            listCorrelations.setLayoutManager(glm);
        } else {
            GridLayoutManager glm = new GridLayoutManager(this,1);
            listCorrelations.setLayoutManager(glm);
        }
    }

    public static boolean esTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}