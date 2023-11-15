package com.example.sistemascasa.tigie.FragmentsActivity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.SearchFractionWords;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.adapter.PageAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import  com.example.sistemascasa.tigie.settings.Comunicador;

import java.util.ArrayList;

public class FractionInformationActivity extends AppCompatActivity implements Comunicador {

    private Toolbar toolbar;
    public TabLayout tabLayout;
    private ViewPager viewPager;
    Context contexto;
    private ConstructorData constructorData;
    private String email, token = "";
    private Integer id_fraccion;
    private String fractionCode = "";
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction_information);

        contexto = getApplicationContext();
        constructorData = new ConstructorData(contexto);
        ArrayList<Object> gotData = new ArrayList<Object>();
        BaseDatos dataBase = new BaseDatos(contexto);
        gotData = dataBase.getUserData();

        if (gotData != null) {
            int listSize = gotData.size();
            if(listSize > 0) {
                email = gotData.get(0).toString();
                token = gotData.get(1).toString();
            }
        }
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros   = getIntent().getExtras();
        fractionCode        = parametros.getString("fractionCode");
        id_fraccion         = parametros.getInt("id_fraccion");
        valTigie    = parametros.getInt("valTigie");

        toolbar             = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);
        viewPager           = (ViewPager) findViewById(R.id.viewPager);

        TextView fraccion  = (TextView) findViewById(R.id.tvFraccion);
        fraccion.setText(fractionCode);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
        setupViewPager();
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();


        fragments.add(new InformationFraccionFragment());
        fragments.add(new TlcFragmen());
        fragments.add(new AladiFragment());
        fragments.add(new TranspacificFragment());
        fragments.add(new AllianceFragment());
        fragments.add(new ModulesFragment());

        return fragments;
    }

    private void setupViewPager () {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.getTabAt(0).setIcon(R.drawable.ic_inf2);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tlc);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_world);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_aliance3);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_trans);
        tabLayout.getTabAt(5).setIcon(R.drawable.ic_modules);


    }

    @Override
    public void responder(String codigo) {

    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Integer getIdFraccion() {
        return id_fraccion;
    }

    public Integer getValTigie() { return valTigie; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Boolean resp = constructorData.getFavouritesByFrac(fractionCode);
        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_headings, menu);
        getMenuInflater().inflate(R.menu.menu_subheadings, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
        if (resp == true) {
            getMenuInflater().inflate(R.menu.menu_star_filled, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_star, menu);
        }
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
                Intent intent_close = new Intent(FractionInformationActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(FractionInformationActivity.this, FractionsActivity.class);
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

            case R.id.mFavourite:
                Boolean resp = constructorData.getFavouritesByFrac(fractionCode);
                if (resp == true) {
                    Boolean success2 = constructorData.deleteFavouritesByFrac(fractionCode);
                    if (success2) {
                        Toast.makeText(getApplicationContext(), "La fracciÃ³n "+  fractionCode + " fue eliminada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.drawable.star_48);
                    }
                } else {
                    Boolean success = constructorData.insertFavouites(fractionCode);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "La fracciÃ³n "+  fractionCode + " fue agregada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.drawable.star_filled_48);
                    }
                }
                break;

            case R.id.mFavourite2:
                Boolean resp2 = constructorData.getFavouritesByFrac(fractionCode);
                if (resp2 == true) {
                    Boolean success2 = constructorData.deleteFavouritesByFrac(fractionCode);
                    if (success2) {
                        Toast.makeText(getApplicationContext(), "La fracciÃ³n "+  fractionCode + " fue eliminada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.drawable.star_48);
                    }
                } else {
                    Boolean success = constructorData.insertFavouites(fractionCode);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "La fracciÃ³n "+  fractionCode + " fue agregada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.drawable.star_filled_48);
                    }
                }
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

    public void getFractions(String fractionCode, Context contexto) {
        Intent intent = new Intent(this, FractionInformationActivity.class);
        intent.putExtra("fractionCode", fractionCode);
        intent.putExtra("valTigie", valTigie);

        startActivity(intent);
    }

    public void getFractionWords(String words) {
        Intent intent = new Intent(this, SearchFractionWords.class);
        intent.putExtra("words", words);
        intent.putExtra("valTigie", valTigie);
        startActivity(intent);
    }

}


/*package com.example.sistemascasa.tigie.FragmentsActivity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.SearchFractionWords;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.adapter.PageAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import  com.example.sistemascasa.tigie.settings.Comunicador;

import java.util.ArrayList;

public class FractionInformationActivity extends AppCompatActivity implements Comunicador {

    private Toolbar toolbar;
    public TabLayout tabLayout;
    private ViewPager viewPager;
    Context contexto;
    private ConstructorData constructorData;
    private String email, token = "";
    private Integer id_fraccion;
    private String fractionCode = "";
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction_information);

        contexto = getApplicationContext();
        constructorData = new ConstructorData(contexto);
        ArrayList<Object> gotData = new ArrayList<Object>();
        BaseDatos dataBase = new BaseDatos(contexto);
        gotData = dataBase.getUserData();

        if (gotData != null) {
            int listSize = gotData.size();
            if(listSize > 0) {
                email = gotData.get(0).toString();
                token = gotData.get(1).toString();
            }
        }
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros   = getIntent().getExtras();
        fractionCode        = parametros.getString("fractionCode");
        id_fraccion         = parametros.getInt("id_fraccion");
        valTigie    = parametros.getInt("valTigie");

        toolbar             = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);
        viewPager           = (ViewPager) findViewById(R.id.viewPager);

        TextView fraccion  = (TextView) findViewById(R.id.tvFraccion);
        fraccion.setText(fractionCode);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
        setupViewPager();
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        if(valTigie == 2012){
            fragments.add(new InformationFraccionFragment());
            fragments.add(new TlcFragmen());
            fragments.add(new AladiFragment());
            fragments.add(new TranspacificFragment());
            fragments.add(new AllianceFragment());
            fragments.add(new ModulesFragment());
        }
       else{
            fragments.add(new InformationFraccionFragment());
            fragments.add(new ModulesFragment());
        }
        return fragments;
    }

    private void setupViewPager () {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        if(valTigie == 2012){
            tabLayout.getTabAt(0).setIcon(R.mipmap.ic_inf2);
            tabLayout.getTabAt(1).setIcon(R.mipmap.ic_tlc);
            tabLayout.getTabAt(2).setIcon(R.mipmap.ic_world);
            tabLayout.getTabAt(3).setIcon(R.mipmap.ic_aliance3);
            tabLayout.getTabAt(4).setIcon(R.mipmap.ic_trans);
            tabLayout.getTabAt(5).setIcon(R.mipmap.ic_modules);
        }
        else{
            tabLayout.getTabAt(0).setIcon(R.mipmap.ic_inf2);
            tabLayout.getTabAt(1).setIcon(R.mipmap.ic_modules);
        }

    }

    @Override
    public void responder(String codigo) {

    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Integer getIdFraccion() {
        return id_fraccion;
    }

    public Integer getValTigie() { return valTigie; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Boolean resp = constructorData.getFavouritesByFrac(fractionCode);
        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_headings, menu);
        getMenuInflater().inflate(R.menu.menu_subheadings, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
        if (resp == true) {
            getMenuInflater().inflate(R.menu.menu_star_filled, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_star, menu);
        }
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
                Intent intent_close = new Intent(FractionInformationActivity.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(FractionInformationActivity.this, FractionsActivity.class);
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

            case R.id.mFavourite:
                Boolean resp = constructorData.getFavouritesByFrac(fractionCode);
                if (resp == true) {
                    Boolean success2 = constructorData.deleteFavouritesByFrac(fractionCode);
                    if (success2) {
                        Toast.makeText(getApplicationContext(), "La fracción "+  fractionCode + " fue eliminada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.mipmap.star_48);
                    }
                } else {
                    Boolean success = constructorData.insertFavouites(fractionCode);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "La fracción "+  fractionCode + " fue agregada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.mipmap.star_filled_48);
                    }
                }
                break;

            case R.id.mFavourite2:
                Boolean resp2 = constructorData.getFavouritesByFrac(fractionCode);
                if (resp2 == true) {
                    Boolean success2 = constructorData.deleteFavouritesByFrac(fractionCode);
                    if (success2) {
                        Toast.makeText(getApplicationContext(), "La fracción "+  fractionCode + " fue eliminada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.mipmap.star_48);
                    }
                } else {
                    Boolean success = constructorData.insertFavouites(fractionCode);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "La fracción "+  fractionCode + " fue agregada exitosamente.", Toast.LENGTH_LONG).show();
                        item.setIcon(R.mipmap.star_filled_48);
                    }
                }
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

    public void getFractions(String fractionCode, Context contexto) {
        Intent intent = new Intent(this, FractionInformationActivity.class);
        intent.putExtra("fractionCode", fractionCode);
        intent.putExtra("valTigie", valTigie);

        startActivity(intent);
    }

    public void getFractionWords(String words) {
        Intent intent = new Intent(this, SearchFractionWords.class);
        intent.putExtra("words", words);
        intent.putExtra("valTigie", valTigie);
        startActivity(intent);
    }

}*/
