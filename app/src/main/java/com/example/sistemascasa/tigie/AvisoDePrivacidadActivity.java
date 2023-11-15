package com.example.sistemascasa.tigie;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class AvisoDePrivacidadActivity extends AppCompatActivity {

    private WebView webview;
    private Toolbar toolbar;
    private Context context;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_de_privacidad);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        toolbar             = (Toolbar) findViewById(R.id.miActionBar2);
        tabLayout           = (TabLayout) findViewById(R.id.tabLayout);

        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl("https://e-casa.com.mx/avisos/AVISO_DE_PRIVACIDAD_E-TIGIE_M%C3%93VIL.html");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}