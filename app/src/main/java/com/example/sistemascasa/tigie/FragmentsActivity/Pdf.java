package com.example.sistemascasa.tigie.FragmentsActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.activities.FractionsActivity;
import com.example.sistemascasa.tigie.activities.ChapterActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.SubheadingActivity;
import com.example.sistemascasa.tigie.rest.InfFractionService;
import com.example.sistemascasa.tigie.settings.ActivityFraccionFavoritos;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class Pdf extends AppCompatActivity {
    private Toolbar toolbar;
    public TabLayout tabLayout;
    private WebView mWebView;
    private ProgressDialog progress;
    private Integer valTigie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar    = (Toolbar)   findViewById(R.id.miActionBar2);
        tabLayout  = (TabLayout) findViewById(R.id.tabLayout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        Bundle parametros2  = getIntent().getExtras();
        String fraccionCode = parametros2.getString("fraccionCode2");
        valTigie     = parametros2.getInt("valTigie");
        if(valTigie == null || valTigie == 0){
            valTigie = 2012;
        }
        getUrlWs(fraccionCode);
    }

    public void getUrlWs(String fraccionCode) {
        final String fracc = fraccionCode;
        progress = new ProgressDialog(this);
        progress.setMessage("Generando  PDF ....");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.show();

        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint("https://e-casa.com.mx/pdfConstructor/public/pdfconstructor/").build();
        InfFractionService service = restAdapter.create(InfFractionService.class);
        service.getClient(fracc, new Callback<com.example.sistemascasa.tigie.pojo.Pdf>() {
            @Override
            public void success(com.example.sistemascasa.tigie.pojo.Pdf pdf, retrofit.client.Response response) {
                showPdf (fracc);
            }
            @Override
            public void failure(RetrofitError error) {
                showPdf (fracc);
            }
        });
    }

    public void showPdf (String fraccionCode) {
        mWebView        = (WebView) findViewById(R.id.activity_main_webview2);
        String name     = "Fraccion_" + fraccionCode + ".pdf" ;
        String path     = "https://e-casa.com.mx/pdfConstructor/files/" + name;
        String url      = "http://docs.google.com/gview?embedded=true&url=" + path;

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.loadUrl(url);
        mWebView.getProgress();

        final int totalProgressTime = 200;

        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        sleep(900);
                    }
                    catch (InterruptedException e) {
                        mandarErrorPdf();
                    }
                }
                progress.dismiss();
            }
        };
        t.start();
    }

    public void mandarErrorPdf() {
        Toast.makeText(Pdf.this , "¡ Fallo de Conexión PDF ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

    @Override public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chapters, menu);
        getMenuInflater().inflate(R.menu.menu_headings, menu);
        getMenuInflater().inflate(R.menu.menu_subheadings, menu);
        getMenuInflater().inflate(R.menu.menu_favourites, menu);

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
                Intent intent_close = new Intent(Pdf.this, SubheadingActivity.class);
                intent_close.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_close.putExtra("CLOSE", true);
                intent_close.putExtra("valTigie", valTigie);
                startActivity(intent_close);
                this.finish();
                break;

            case R.id.mSubheadings:
                Intent intent_sh = new Intent(Pdf.this, FractionsActivity.class);
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
        }

        return super.onOptionsItemSelected(item);
    }
}




