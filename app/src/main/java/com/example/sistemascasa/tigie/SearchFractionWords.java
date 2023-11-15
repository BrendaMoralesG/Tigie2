package com.example.sistemascasa.tigie;

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

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.HeadingActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.activities.MainActivity;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.adapter.FractionAdapter;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityAbout;

import java.io.IOError;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFractionWords extends AppCompatActivity {
    String text = "";
    Context contexto;
    Activity activity;
    Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fraction_words);

        contexto = getApplicationContext();
        activity = this;

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            text = parametros.getString("words");
            valTigie    = parametros.getInt("valTigie");
            getFractionsWord(text, valTigie);
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
    }

    public void getFractionsWord (String text, final Integer valTigie) {
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
            Call<ArrayList<Fractions>> service = endService.getFractionsByString(text, valTigie, email, token);

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general, menu);
        getMenuInflater().inflate(R.menu.menu_chapters, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.search));
        searchView.setDrawingCacheBackgroundColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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
                            Intent intent_chap = new Intent(SearchFractionWords.this, ChapterActivity.class);
                            intent_chap.putExtra("valTigie", valTigie);
                            intent_chap.putExtra("chapterCode", query);
                            startActivity(intent_chap);
                            break;

                        case 4:
                            Intent intent_head = new Intent(SearchFractionWords.this, HeadingActivity.class);
                            intent_head.putExtra("flag", 2);
                            intent_head.putExtra("valTigie", valTigie);
                            intent_head.putExtra("tariffHeadingCode", query);
                            startActivity(intent_head);
                            break;

                        case 6:
                            Intent intent_subhead = new Intent(SearchFractionWords.this, SubheadingActivity.class);
                            intent_subhead.putExtra("flag", 2);
                            intent_subhead.putExtra("valTigie", valTigie);
                            intent_subhead.putExtra("tariffSubheadingCode", query);
                            startActivity(intent_subhead);
                            break;

                        case 8:
                            Intent intent_frac = new Intent(SearchFractionWords.this, FractionsActivity.class);
                            intent_frac.putExtra("flag", 2);
                            intent_frac.putExtra("valTigie", valTigie);
                            intent_frac.putExtra("fraccionCode", query);
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
                Intent intent_chapters = new Intent(this, MainActivity.class);
                startActivity(intent_chapters);
                break;

            case R.id.mExit:
                Intent intent_exit = new Intent(getApplicationContext(), MainActivity.class);
                intent_exit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_exit.putExtra("EXIT", true);
                startActivity(intent_exit);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getFractionWords(String words, Integer valTigie) {
        Intent intent = new Intent(this, SearchFractionWords.class);
        intent.putExtra("words", words);
        intent.putExtra("valTigie", valTigie);
        startActivity(intent);
    }
}
