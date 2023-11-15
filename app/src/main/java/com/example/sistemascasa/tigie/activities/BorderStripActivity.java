package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.db.BaseDatos;
import com.google.android.material.tabs.TabLayout;

import com.example.sistemascasa.tigie.FragmentsActivity.Pdf;
import com.example.sistemascasa.tigie.R;

import com.example.sistemascasa.tigie.adapter.BorderStripAdapter;
import com.example.sistemascasa.tigie.pojo.BorderStrip;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorderStripActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public TabLayout tabLayout;
    private Context context;
    private Activity activity;
    TextView tvFraccionBorderCode;
    private String fractionCode = "";
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_border_strip);

        activity = this;
        context = getApplicationContext();
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
            Integer id_fraccion     = Integer.parseInt(id_fraccionS);
            valTigie     = parametros.getInt("valTigie");
            tvFraccionBorderCode = (TextView) findViewById(R.id.tvFraccionBorderCode);
            tvFraccionBorderCode.setText(fractionCode);

            getBorderStripWs(id_fraccion, fractionCode, valTigie);
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
    }

    public void getBorderStripWs (Integer id_fraccion, String fractionCode, Integer valTigie) {
        try {
            RestApiAdapter restApiAdapter       = new RestApiAdapter();
            EndpointsService tlcService         = restApiAdapter.establecerConexionRestApi();
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

            Call<ArrayList<BorderStrip>> tlcCall= tlcService.getborderstrip(id_fraccion, valTigie, email, token);

            tlcCall.enqueue(new Callback<ArrayList<BorderStrip>>() {
                @Override
                public void onResponse(Call<ArrayList<BorderStrip>> call, Response<ArrayList<BorderStrip>> response) {
                    try {
                        if (response.body() == null) {
                            mandarErrorBorder ();
                        } else {
                            ArrayList<BorderStrip> borderStrips = response.body();
                            if (borderStrips.size() > 0) {
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvBorder);

                                LinearLayoutManager llm = new LinearLayoutManager(context);
                                rvC.setLayoutManager(llm);

                                BorderStripAdapter adapter = new BorderStripAdapter(borderStrips, activity);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e){
                        mandarErrorBorder ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<BorderStrip>> call, Throwable t) {
                    mandarErrorBorder ();
                }
            });
        } catch (IOError e) {
            mandarErrorBorder ();
        }
    }

    public void mandarErrorBorder() {
        Toast.makeText(BorderStripActivity.this , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
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
                Intent intent_close = new Intent(BorderStripActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(BorderStripActivity.this, FractionsActivity.class);
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
                intent_pdf.putExtra("valTigie", valTigie);
                startActivity(intent_pdf);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
