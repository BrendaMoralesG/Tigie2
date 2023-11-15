package com.example.sistemascasa.tigie.FragmentsActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.adapter.ImportPermitAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.pojo.ImportPermits;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportPermitActivity extends AppCompatActivity  {
    private Activity activity;
    private Toolbar toolbar;
    public TabLayout tabLayout;
    private Context context;
    private TextView tvFraccionImportCode;
    private String fractionCode;
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_permit);

        context = getApplicationContext();
        activity = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar             = (Toolbar) findViewById(R.id.miActionBar2);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            fractionCode = parametros.getString("fractionCode");
            String id_fraccionS     = parametros.getString("id_fraccion");
            valTigie     = parametros.getInt("valTigie");
            Integer id_fraccion     = Integer.parseInt(id_fraccionS);

            tvFraccionImportCode = (TextView) findViewById(R.id.tvFraccionImportCode);
            tvFraccionImportCode.setText(fractionCode);
            getImportWs(id_fraccion, valTigie);
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
    }

    public void getImportWs (Integer id_fraccion, Integer valTigie) {
        try {
            RestApiAdapter restApiAdapter       = new RestApiAdapter();
            EndpointsService tlcService         = restApiAdapter.establecerConexionRestApi();ArrayList<Object> gotData = new ArrayList<Object>();
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
            Call<ArrayList<ImportPermits>> tlcCall   = tlcService.getimportpermits(id_fraccion, valTigie, email, token);

            tlcCall.enqueue(new Callback<ArrayList<ImportPermits>>() {
                @Override
                public void onResponse(Call<ArrayList<ImportPermits>> call, Response<ArrayList<ImportPermits>> response) {
                    try {
                        if (response.body() == null) {
                            mandarErrorImport ();
                        } else {
                            ArrayList<ImportPermits> importPermits = response.body();
                            if (importPermits.size() > 0) {
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvImportPermit);

                                LinearLayoutManager llm = new LinearLayoutManager(context);
                                rvC.setLayoutManager(llm);

                                ImportPermitAdapter adapter = new ImportPermitAdapter(importPermits, activity, getApplicationContext());
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e) {
                        mandarErrorImport ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<ImportPermits>> call, Throwable t) {
                    mandarErrorImport ();
                }
            });
        } catch (IOError e) {
            mandarErrorImport ();
        }
    }

    public void mandarErrorImport() {
        Toast.makeText(ImportPermitActivity.this , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
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
                Intent intent_close = new Intent(ImportPermitActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(ImportPermitActivity.this, FractionsActivity.class);
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
                intent_pdf.putExtra("fraccionCode2", fractionCode);
                startActivity(intent_pdf);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
