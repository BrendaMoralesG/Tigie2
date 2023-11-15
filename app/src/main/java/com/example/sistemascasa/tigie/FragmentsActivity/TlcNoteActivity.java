
package com.example.sistemascasa.tigie.FragmentsActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.db.BaseDatos;
import com.google.android.material.tabs.TabLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.adapter.TlcNotesAdapter;
import com.example.sistemascasa.tigie.pojo.TlcNotes;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;
import com.example.sistemascasa.tigie.settings.IconosBandera;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TlcNoteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public TabLayout tabLayout;
    private Context context;
    private Activity activity;
    TextView tvFraccionTlcNoteCode;
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlc_note);

        context  = getApplicationContext();
        activity = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar             = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        Bundle parametros = getIntent().getExtras();
        String fractionCode = parametros.getString("fractionCode");
        String id_fraccionS     = parametros.getString("id_fraccion");
        valTigie     = parametros.getInt("valTigie");
        Integer id_fraccion     = Integer.parseInt(id_fraccionS);

        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }

        tvFraccionTlcNoteCode = (TextView) findViewById(R.id.tvFraccionTlcNoteCode);
        tvFraccionTlcNoteCode.setText(fractionCode);

        getTlcNoteWs(id_fraccion, valTigie);
    }

    public void getTlcNoteWs (Integer id_fraccion, Integer valTigie) {
        //fraccion = "17049099";
        final IconosBandera iconosBandera   = new IconosBandera();

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
            Call<ArrayList<TlcNotes>> tlcCall   = tlcService.gettlcnotes(id_fraccion, valTigie, email, token);

            tlcCall.enqueue(new Callback<ArrayList<TlcNotes>>() {
                @Override
                public void onResponse(Call<ArrayList<TlcNotes>> call, Response<ArrayList<TlcNotes>> response) {

                    try {
                        if (response.body() == null) {
                            mandarErrorTlcNotes ();
                        } else {
                            ArrayList<TlcNotes> tlcNotes = response.body();
                            if (tlcNotes.size() > 0) {
                                int ivFlagTlcNote = R.drawable.paisesnodeclarados2;
                                for (TlcNotes tlc : tlcNotes) {
                                    if (tlc.getTlcNoteFreeCountryCode() == null) {
                                        ivFlagTlcNote = R.drawable.paisesnodeclarados4;
                                    } else {
                                        ivFlagTlcNote = iconosBandera.IconosBandera(tlc.getTlcNoteFreeCountryCode());
                                    }
                                    tlc.setIvFlagTlcNote(ivFlagTlcNote);
                                }
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvTlcNote);

                                LinearLayoutManager llm = new LinearLayoutManager(context);
                                rvC.setLayoutManager(llm);

                                TlcNotesAdapter adapter = new TlcNotesAdapter(tlcNotes, activity);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e) {
                        mandarErrorTlcNotes ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<TlcNotes>> call, Throwable t) {
                    mandarErrorTlcNotes ();
                }
            });
        } catch (IOError e) {
            mandarErrorTlcNotes ();
        }
    }

    public void mandarErrorTlcNotes() {
        Toast.makeText(TlcNoteActivity.this , "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
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
                Intent intent_close = new Intent(TlcNoteActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(TlcNoteActivity.this, FractionsActivity.class);
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
