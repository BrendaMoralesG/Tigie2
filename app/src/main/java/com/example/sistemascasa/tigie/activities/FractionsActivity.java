package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.ActivityArco;
import com.example.sistemascasa.tigie.AvisoDePrivacidadActivity;
import com.example.sistemascasa.tigie.Comunes;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.SearchFractionWords;
import com.example.sistemascasa.tigie.adapter.FractionAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityAbout;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FractionsActivity extends AppCompatActivity {
    public static FractionsActivity instance = null;
    Context contexto;
    private Activity activity;

    private TextView tariffSubheadingCode;
    private TextView tariffSubheadingDescription;
    private ImageView ivIconFrac;
    private Integer valTigie;
    private ConstructorData constructorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction);
        contexto = getApplicationContext();
        constructorData = new ConstructorData(contexto);
        activity = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().getBooleanExtra("CLOSE", false)) {
            finish();
        }

        if (getIntent().getBooleanExtra("CLOSE_FRACTION", false)) {
            finish();
        }

        if (getIntent().getBooleanExtra("CLOSE_SUBHEADING", false)) {
            Intent intent_close = new Intent(this, SubheadingActivity.class);
            intent_close.putExtra("valTigie", valTigie);
            intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent_close.putExtra("CLOSE", true);
            startActivity(intent_close);
            this.finish();
        }

        tariffSubheadingCode       = (TextView) findViewById(R.id.tariffSubheadingCode);
        tariffSubheadingDescription= (TextView) findViewById(R.id.tariffSubheadingDescription);
        ivIconFrac                 = (ImageView) findViewById(R.id.ivIconFrac);

        Bundle parametros          = getIntent().getExtras();
        if (parametros != null) {
            int flag  = parametros.getInt("flag");
            valTigie    = parametros.getInt("valTigie");
            if (flag == 1) {
                String idSubheading             = parametros.getString(getResources().getString(R.string.idTariffSubheading));
                String codeSubheading           = parametros.getString(getResources().getString(R.string.tariffSubheadingCode));
                String descriptionSubheading    = parametros.getString(getResources().getString(R.string.tariffSubheadingDescription));
                int imagen                      = parametros.getInt(getResources().getString(R.string.iconChapter));
                int idSubheadingInt             = parametros.getInt("idSubheading");
                TextView idTariffSubheading     = (TextView) findViewById(R.id.idTariffSubheading);

                idTariffSubheading.setText(idSubheading);
                tariffSubheadingCode.setText(codeSubheading);
                tariffSubheadingDescription.setText(descriptionSubheading);

                try {
                    ivIconFrac.setImageResource(imagen);
                } catch (Exception e) {
                    ivIconFrac.setImageResource(R.drawable.z_page);
                }
                getFractionsWs(idSubheadingInt, valTigie);
            } else {
                String fraccionCode  = parametros.getString("fraccionCode");
                getFractionsByCode(fraccionCode, valTigie);
            }
        }

        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }

        try {
            TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
            toolbar_title.setText("E - TIGIE " + valTigie);
        } catch (Exception e) {}
    }

    public void getFractionsByCode (String fraccionCode, final Integer valTigie) {
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
            Call<ArrayList<Fractions>> service = endService.getFractionsByCode(fraccionCode, valTigie, email, token);

            service.enqueue(new Callback<ArrayList<Fractions>>() {
                @Override
                public void onResponse(Call<ArrayList<Fractions>> call, Response<ArrayList<Fractions>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError();
                        } else {
                            ArrayList<Fractions> fractions = response.body();
                            if (fractions.size() > 0) {
                                try {
                                    tariffSubheadingCode.setText(fractions.get(0).getTariffSubHeadCode());
                                    tariffSubheadingDescription.setText(fractions.get(0).getTariffSubHeadDescription());
                                    ivIconFrac.setImageResource(R.drawable.z_page);
                                } catch (Exception e) { }

                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvFractions);
                                LinearLayoutManager llm = new LinearLayoutManager(contexto);
                                rvC.setLayoutManager(llm);

                                FractionAdapter adapter = new FractionAdapter(fractions, activity, "", valTigie);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e){
                        sentMessageError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Fractions>> call, Throwable t) {
                    sentMessageError();
                }
            });
        } catch (IOError e){
            sentMessageError();
        }
    }

    public void getFractionsWs(Integer idTariffSubheading, final Integer valTigie) {
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

            Call<ArrayList<Fractions>> service = endService.getFractions(idTariffSubheading, valTigie, email, token);

            service.enqueue(new Callback<ArrayList<Fractions>>() {
                @Override
                public void onResponse(Call<ArrayList<Fractions>> call, Response<ArrayList<Fractions>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError();
                        } else {
                            ArrayList<Fractions> fractions = response.body();
                            if (fractions.size() > 0) {
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvFractions);
                                LinearLayoutManager llm = new LinearLayoutManager(contexto);
                                rvC.setLayoutManager(llm);

                                FractionAdapter adapter = new FractionAdapter(fractions, activity, "", valTigie);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e){
                        sentMessageError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Fractions>> call, Throwable t) {
                    sentMessageError();
                }
            });
        } catch (IOError e){
            sentMessageError();
        }
    }

    public void sentMessageError() {
        Toast.makeText(contexto, "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_headings, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.search));
        searchView.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            ChapterActivity mainActivity = new ChapterActivity();
            @Override
            public boolean onQueryTextSubmit(String query) {
                String text = "";
                if (Comunes.isNumeric(query)) {
                    if (query.length() == 1)
                        query = "0" + query;

                    if (query.length() == 3)
                        query = "0" + query;

                    if (query.length() == 5)
                        query = "0" + query;

                    if (query.length() == 7)
                        query = "0" + query;

                    switch (query.length()) {
                        case 2:
                            Intent intent_chap = new Intent(FractionsActivity.this, ChapterActivity.class);
                            intent_chap.putExtra("chapterCode", query);
                            intent_chap.putExtra("valTigie", valTigie);
                            startActivity(intent_chap);
                            break;

                        case 4:
                            Intent intent_head = new Intent(FractionsActivity.this, HeadingActivity.class);
                            intent_head.putExtra("flag", 2);
                            intent_head.putExtra("tariffHeadingCode", query);
                            intent_head.putExtra("valTigie", valTigie);
                            startActivity(intent_head);
                            break;

                        case 6:
                            Intent intent_subhead = new Intent(FractionsActivity.this, SubheadingActivity.class);
                            intent_subhead.putExtra("flag", 2);
                            intent_subhead.putExtra("tariffSubheadingCode", query);
                            intent_subhead.putExtra("valTigie", valTigie);
                            startActivity(intent_subhead);
                            break;

                        case 8:
                            Intent intent_frac = new Intent(FractionsActivity.this, FractionsActivity.class);
                            intent_frac.putExtra("flag", 2);
                            intent_frac.putExtra("fraccionCode", query);
                            intent_frac.putExtra("valTigie", valTigie);
                            startActivity(intent_frac);
                            break;
                    }
                } else {
                    getFractionWords(query, valTigie);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.mAbout:
                Intent intent_about = new Intent(this, ActivityAbout.class);
                startActivity(intent_about);
                break;

            case R.id.mChapters:
                Intent intent_chapters = new Intent(this, ChapterActivity.class);
                intent_chapters.putExtra("chapterCode", "");
                intent_chapters.putExtra("valTigie", valTigie);
                startActivity(intent_chapters);
                break;

            case R.id.mHeadings:
                Intent intent_close = new Intent(this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mExit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(FractionsActivity.this);
                LayoutInflater inflater          =  activity.getLayoutInflater();
                View view             =  inflater.inflate(R.layout.activity_eliminar_partida,null);
                TextView        tvDeletePartidas =  (TextView) view.findViewById(R.id.tvDeletePartidas);
                tvDeletePartidas.setText(contexto.getResources().getString(R.string.cerrar_sesion));

                builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        constructorData.deleteUserData();
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.depurar), Toast.LENGTH_SHORT).show();

                        Intent login = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(login);
                        dialog.dismiss();
                    }
                });
                break;

            case R.id.mAvisoPrivacidad:
                Intent intent_aviso = new Intent(this, AvisoDePrivacidadActivity.class);
                startActivity(intent_aviso);
                return true;

            case R.id.mDerArco:
                Intent intent_arco = new Intent(this, ActivityArco.class);
                startActivity(intent_arco);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getFractionWords(String words, Integer valTigie) {
        Intent intent = new Intent(this, SearchFractionWords.class);
        intent.putExtra("words", words);
        intent.putExtra("valTigie", valTigie);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    public void onPause() {
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
