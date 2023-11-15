package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import com.example.sistemascasa.tigie.db.BaseDatos;
import com.google.android.material.tabs.TabLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.FragmentsActivity.Pdf;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.EstimatedPricesAdapter;
import com.example.sistemascasa.tigie.pojo.EstimatedPrices;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstimatedPricesActivity extends AppCompatActivity {
    private Activity activity;
    private Toolbar toolbar;
    public TabLayout tabLayout;
    private Context context;
    TextView tvFraccionEstimatedCode;
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimated_prices);

        context = getApplicationContext();
        activity = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar             = (Toolbar) findViewById(R.id.miActionBar2);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        Bundle parametros   = getIntent().getExtras();
        if (parametros != null) {
            String fractionCode = parametros.getString("fractionCode");
            String id_fraccionS     = parametros.getString("id_fraccion");
            valTigie     = parametros.getInt("valTigie");
            Integer id_fraccion     = Integer.parseInt(id_fraccionS);

            tvFraccionEstimatedCode = (TextView) findViewById(R.id.tvFraccionEstimatedCode);
            tvFraccionEstimatedCode.setText(fractionCode);

            getEstimatedWs(id_fraccion, valTigie);
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
    }

    public void getEstimatedWs (Integer id_fraccion, Integer valTigie) {
        try {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsService tlcService   = restApiAdapter.establecerConexionRestApi();
            ArrayList<Object> gotData = new ArrayList<Object>();
            BaseDatos dataBase = new BaseDatos(context);
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
            Call<ArrayList<EstimatedPrices>> tlcCall = tlcService.getestimatedprices(id_fraccion, valTigie, email, token);

            tlcCall.enqueue(new Callback<ArrayList<EstimatedPrices>>() {
                @Override
                public void onResponse(Call<ArrayList<EstimatedPrices>> call, Response<ArrayList<EstimatedPrices>> response) {
                    try {
                        if (response.body() == null) {
                            mandarErrorEstimated ();
                        } else {
                            ArrayList<EstimatedPrices> estimatedPrices = response.body();
                            if (estimatedPrices.size() > 0) {
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvEstimated);

                                LinearLayoutManager llm = new LinearLayoutManager(context);
                                rvC.setLayoutManager(llm);

                                EstimatedPricesAdapter adapter = new EstimatedPricesAdapter(estimatedPrices, activity);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e) {
                        mandarErrorEstimated ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<EstimatedPrices>> call, Throwable t) {
                    mandarErrorEstimated ();
                }
            });
        } catch (IOError e) {
            mandarErrorEstimated ();
        }
    }

    public void mandarErrorEstimated() {
        Toast.makeText(EstimatedPricesActivity.this , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_headings, menu);
        getMenuInflater().inflate(R.menu.menu_subheadings, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
        getMenuInflater().inflate(R.menu.menu_pdf, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.mChapters:
                Intent intent_chapters = new Intent(this, ChapterActivity.class);
                intent_chapters.putExtra("valTigie", valTigie);
                startActivity(intent_chapters);
                break;

            case R.id.mHeadings:
                Intent intent_close = new Intent(EstimatedPricesActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(EstimatedPricesActivity.this, FractionsActivity.class);
                intent_sh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_sh.putExtra("CLOSE", true);
                intent_sh.putExtra("valTigie", valTigie);
                startActivity(intent_sh);
                this.finish();
                break;

            case R.id.mShowFavourites:
                Intent intent_showfav = new Intent(this, ActivityFraccionFavoritos.class);
                intent_showfav.putExtra("valTigie", valTigie);
                startActivity(intent_showfav);
                break;

            case R.id.mPdf:
                Intent intent_pdf = new Intent(this, Pdf.class);
                Bundle parametros2   = getIntent().getExtras();
                String fractionCode2 = parametros2.getString("fractionCode");
                intent_pdf.putExtra("fraccionCode2", fractionCode2);
                startActivity(intent_pdf);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
