package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemascasa.tigie.ActivityArco;
import com.example.sistemascasa.tigie.AvisoDePrivacidadActivity;
import com.example.sistemascasa.tigie.Comunes;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.SearchFractionWords;
import com.example.sistemascasa.tigie.adapter.ChapterAdapter;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Chapters;
import com.example.sistemascasa.tigie.settings.ActivityAbout;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {

    private Context contexto;
    private Toolbar toolbar;
    private ConstructorData constructorData;
    private String chapterCode = "";
    private Integer valTigie = 0;
    private TextView toolbar_title;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        contexto = getApplicationContext();
        constructorData = new ConstructorData(contexto);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        activity            = (Activity)     this;

        Bundle parametros       = getIntent().getExtras();
        if (parametros !=  null) {
            chapterCode     = parametros.getString("chapterCode");
            valTigie        = parametros.getInt("valTigie");
        }

        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }

        try {
            toolbar_title = (TextView) findViewById(R.id.toolbar_title);
            toolbar_title.setText("E - TIGIE " + valTigie);
        } catch (Exception e) {}

        if (toolbar != null)
            setSupportActionBar(miActionBar);

        if (getIntent().getBooleanExtra("CLOSE", false)) {
            finish();
        }

        if (chapterCode == null) {
            getChaptersBD();
        }  else {
            if (chapterCode.isEmpty()) {
                getChaptersBD();
            } else{
                getChaptersBycode(chapterCode);
            }
        }
    }

    public void getChaptersBycode (String chapterCode) {
        ArrayList<Chapters> chapters = constructorData.getChapterByCode (chapterCode);
        if (chapters.size() > 0) {
            RecyclerView rvC = (RecyclerView) findViewById(R.id.rvChapters);
            LinearLayoutManager llm = new LinearLayoutManager(contexto);
            rvC.setLayoutManager(llm);

            ChapterAdapter adapter = new ChapterAdapter(chapters, this, contexto, valTigie);
            rvC.setAdapter(adapter);
        } else {
            Toast.makeText(ChapterActivity.this, "ERROR! La Búsqueda fue incorrecta.", Toast.LENGTH_LONG).show();
        }
    }

    public void getChaptersBD() {
        ArrayList<Chapters> chapters = constructorData.getChapters();
        if (chapters.size() > 0) {
            RecyclerView rvC = (RecyclerView) findViewById(R.id.rvChapters);
            LinearLayoutManager llm = new LinearLayoutManager(contexto);
            rvC.setLayoutManager(llm);

            ChapterAdapter adapter = new ChapterAdapter(chapters, this, contexto, valTigie);
            rvC.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favourites, menu);
        inflater.inflate(R.menu.menu_general, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.search));
        searchView.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
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
                            Intent intent_chap = new Intent(ChapterActivity.this, ChapterActivity.class);
                            intent_chap.putExtra("chapterCode", query);
                            intent_chap.putExtra("valTigie", valTigie);
                            startActivity(intent_chap);
                            break;

                        case 4:
                            Intent intent_head = new Intent(ChapterActivity.this, HeadingActivity.class);
                            intent_head.putExtra("flag", 2);
                            intent_head.putExtra("tariffHeadingCode", query);
                            intent_head.putExtra("valTigie", valTigie);
                            startActivity(intent_head);
                            break;

                        case 6:
                            Intent intent_subhead = new Intent(ChapterActivity.this, SubheadingActivity.class);
                            intent_subhead.putExtra("flag", 2);
                            intent_subhead.putExtra("tariffSubheadingCode", query);
                            intent_subhead.putExtra("valTigie", valTigie);
                            startActivity(intent_subhead);
                            break;

                        case 8:
                            Intent intent_frac = new Intent(ChapterActivity.this, FractionsActivity.class);
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

    public void getFractionWords(String words) {
        Intent intent = new Intent(this, SearchFractionWords.class);
        intent.putExtra("words", words);
        intent.putExtra("valTigie", valTigie);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.mShowFavourites:
                Intent intent_showfav = new Intent(this, ActivityFraccionFavoritos.class);
                intent_showfav.putExtra("valTigie", valTigie);
                startActivity(intent_showfav);
                break;

            case R.id.action_search:
                return true;

            case R.id.mAbout:
                Intent intent_about = new Intent(this, ActivityAbout.class);
                intent_about.putExtra("valTigie", valTigie);
                startActivity(intent_about);
                break;

            case R.id.mDerArco:
                Intent intent_arco = new Intent(this, ActivityArco.class);
                startActivity(intent_arco);
                return true;

            case R.id.mAvisoPrivacidad:
                Intent intent_aviso = new Intent(this, AvisoDePrivacidadActivity.class);
                startActivity(intent_aviso);
                return true;

            case R.id.mExit:
                final AlertDialog.Builder builder = new AlertDialog.Builder(ChapterActivity.this);
                LayoutInflater inflater          =  activity.getLayoutInflater();
                View            view             =  inflater.inflate(R.layout.activity_eliminar_partida,null);
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
        }
        return super.onOptionsItemSelected(item);
    }
}
