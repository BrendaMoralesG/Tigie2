package com.example.sistemascasa.tigie.FragmentsActivity;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.db.BaseDatos;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.adapter.ProsecAdapter;
import com.example.sistemascasa.tigie.pojo.Prosec;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProsecActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public TabLayout tabLayout;
    private Context context;
    TextView tvFraccionProsecCode;
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prosec);

        context = getApplicationContext();

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar             = (Toolbar) findViewById(R.id.miActionBar2);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        Bundle parametros       = getIntent().getExtras();
        String fractionCode     = parametros.getString("fractionCode");
        String id_fraccionS     = parametros.getString("id_fraccion");
        valTigie     = parametros.getInt("valTigie");
        Integer id_fraccion     = Integer.parseInt(id_fraccionS);

        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
        tvFraccionProsecCode    = (TextView) findViewById(R.id.tvFraccionProsecCode);
        tvFraccionProsecCode.setText(fractionCode);

        getProsecWS(id_fraccion, valTigie);
    }

    public void getProsecWS(Integer id_fraccion, Integer valTigie) {
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
        Call<ArrayList<Prosec>> tlcCall    = tlcService.getprosec(id_fraccion, valTigie, email, token);

        try {
            tlcCall.enqueue(new Callback<ArrayList<Prosec>>() {
                @Override
                public void onResponse(Call<ArrayList<Prosec>> call, Response<ArrayList<Prosec>> response) {
                    try {
                        if (response.body() == null) {
                            mandarErrorProsec();
                        } else {
                            ArrayList<Prosec> prosecs = response.body();
                            if (prosecs.size() > 0) {
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvProsec);

                                LinearLayoutManager llm = new LinearLayoutManager(context);
                                rvC.setLayoutManager(llm);

                                ProsecAdapter adapter = new ProsecAdapter(prosecs);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e) {
                        mandarErrorProsec();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Prosec>> call, Throwable t) {
                    mandarErrorProsec();
                }
            });
        } catch (IOError e) {
            mandarErrorProsec();
        }
    }

    public void mandarErrorProsec() {
        Toast.makeText(ProsecActivity.this , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
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
                Intent intent_close = new Intent(ProsecActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(ProsecActivity.this, FractionsActivity.class);
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
                intent_pdf.putExtra("valTigie", valTigie);
                startActivity(intent_pdf);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
