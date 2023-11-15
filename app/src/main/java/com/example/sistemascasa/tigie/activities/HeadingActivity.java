package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.LinkMovementMethod;
import android.util.Log;
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
import com.example.sistemascasa.tigie.adapter.HeadingAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityAbout;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;
import com.example.sistemascasa.tigie.settings.IconosCapitulos;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadingActivity extends AppCompatActivity {
    Context contexto;
    private Activity activity;
    int imagen = R.drawable.z_page;
    private TextView tariffChapterCode;
    private TextView tariffChapterDescription;
    private ImageView iconChapter;
    private Integer valTigie;
    private ConstructorData constructorData;

    public static HeadingActivity instance = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headings);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        contexto = getApplicationContext();
        constructorData = new ConstructorData(contexto);
        activity = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView idTariffChapter   = (TextView) findViewById(R.id.idTariffChapter);
        tariffChapterCode          = (TextView) findViewById(R.id.tariffChapterCode);
        tariffChapterDescription   = (TextView) findViewById(R.id.tariffChapterDescription);
        iconChapter                = (ImageView) findViewById(R.id.ivIconChap);

        Bundle parametros   = getIntent().getExtras();
        if (parametros != null) {
            int flag            = parametros.getInt("flag");
            valTigie    = parametros.getInt("valTigie");
            if (flag == 1) {
                String id           = parametros.getString(getResources().getString(R.string.idTariffChapter));
                String code         = parametros.getString(getResources().getString(R.string.tariffChapterCode));
                String description  = parametros.getString(getResources().getString(R.string.tariffChapterDescription));
                int idChapter       = parametros.getInt("idChapter");
                idTariffChapter.setText(id);
                tariffChapterCode.setText(code);
                tariffChapterDescription.setText(description);

                try {
                    imagen  = parametros.getInt(getResources().getString(R.string.iconChapter));
                    iconChapter.setImageResource(imagen);
                } catch (Exception e) {
                    iconChapter.setImageResource(R.drawable.z_page);
                }
                getHeadingsWS(idChapter,valTigie);
            } else {
                String tariffHeadingCode = parametros.getString("tariffHeadingCode");
                getHeadingsWSByCode(tariffHeadingCode, valTigie);
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

    public void getHeadingsWSByCode (String tariffHeadingCode, final Integer valTigie) {
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
            Call<ArrayList<Headings>> service = endService.getHeadingsByCode(tariffHeadingCode, valTigie, email, token);

            service.enqueue(new Callback<ArrayList<Headings>>() {
                @Override
                public void onResponse(Call<ArrayList<Headings>> call, Response<ArrayList<Headings>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError();
                        } else {
                            IconosCapitulos iconos = new IconosCapitulos();
                            ArrayList<Headings> headingses = response.body();
                            if (headingses.size() > 0) {
                                try {
                                    tariffChapterCode.setText(headingses.get(0).getCap());
                                    tariffChapterDescription.setText(headingses.get(0).getCapDes());
                                    int icon = iconos.IconosCapitulos(headingses.get(0).getCap());
                                    iconChapter.setImageResource(icon);
                                } catch (Exception e) {
                                    iconChapter.setImageResource(R.drawable.z_page);
                                }

                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvHeadings);
                                LinearLayoutManager llm = new LinearLayoutManager(contexto);
                                rvC.setLayoutManager(llm);

                                HeadingAdapter adapter = new HeadingAdapter(headingses, activity, imagen, valTigie);
                                rvC.setAdapter(adapter);
                            } else {
                                Toast.makeText(HeadingActivity.this, "ERROR! La Búsqueda fue incorrecta.", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (IOError e) {
                        sentMessageError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Headings>> call, Throwable t) {
                    sentMessageError();
                }
            });
        } catch (Exception e) { }
    }

    public void getHeadingsWS (Integer idChapter, final Integer valTigie) {
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
            Call<ArrayList<Headings>> service = endService.getHeadings(idChapter, valTigie, email, token);

            service.enqueue(new Callback<ArrayList<Headings>>() {
                @Override
                public void onResponse(Call<ArrayList<Headings>> call, Response<ArrayList<Headings>> response) {
                    try {
                        if (response.body() == null) {
                            sentMessageError();
                        } else {
                            ArrayList<Headings> headingses = response.body();
                            if (headingses.size() > 0) {
                                RecyclerView rvC = (RecyclerView) findViewById(R.id.rvHeadings);
                                LinearLayoutManager llm = new LinearLayoutManager(contexto);
                                rvC.setLayoutManager(llm);

                                HeadingAdapter adapter = new HeadingAdapter(headingses, activity, imagen, valTigie);
                                rvC.setAdapter(adapter);
                            }
                        }
                    } catch (IOError e) {
                        sentMessageError();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Headings>> call, Throwable t) {
                    sentMessageError();
                }
            });
        } catch (Exception e) { }
    }

    public void sentMessageError() {
        Toast.makeText(contexto, "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);

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
                            Intent intent_chap = new Intent(HeadingActivity.this, ChapterActivity.class);
                            intent_chap.putExtra("chapterCode", query);
                            intent_chap.putExtra("valTigie", valTigie);
                            startActivity(intent_chap);
                            break;

                        case 4:
                            Intent intent_head = new Intent(HeadingActivity.this, HeadingActivity.class);
                            intent_head.putExtra("flag", 2);
                            intent_head.putExtra("tariffHeadingCode", query);
                            intent_head.putExtra("valTigie", valTigie);
                            startActivity(intent_head);
                            break;

                        case 6:
                            Intent intent_subhead = new Intent(HeadingActivity.this, SubheadingActivity.class);
                            intent_subhead.putExtra("flag", 2);
                            intent_subhead.putExtra("tariffSubheadingCode", query);
                            intent_subhead.putExtra("valTigie", valTigie);
                            startActivity(intent_subhead);
                            break;

                        case 8:
                            Intent intent_frac = new Intent(HeadingActivity.this, FractionsActivity.class);
                            intent_frac.putExtra("flag", 2);
                            intent_frac.putExtra("fraccionCode", query);
                            intent_frac.putExtra("valTigie", valTigie);
                            startActivity(intent_frac);
                            break;
                    }
                } else {
                    getFractionWords(query);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mAbout:
                Intent intent_about = new Intent(this, ActivityAbout.class);
                startActivity(intent_about);
                break;

            case R.id.mExit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(HeadingActivity.this);
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

            case R.id.mShowFavourites:
                Intent intent_showfav = new Intent(this, ActivityFraccionFavoritos.class);
                intent_showfav.putExtra("valTigie", valTigie);
                startActivity(intent_showfav);
                break;

            case R.id.mChapters:
                Intent intent_chapters = new Intent(this, ChapterActivity.class);
                startActivity(intent_chapters);
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

    public void getFractionWords(String words) {
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
