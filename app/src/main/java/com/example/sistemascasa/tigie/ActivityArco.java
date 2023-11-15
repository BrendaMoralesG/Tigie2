package com.example.sistemascasa.tigie;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Browser;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sistemascasa.tigie.databinding.ActivityArcoBinding;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstantesBaseDatos;

import java.util.ArrayList;

public class ActivityArco extends AppCompatActivity {
    private Toolbar toolbar;
    private Context context;
    private Activity activity;
    String value ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arco);
        context = getApplicationContext();
        activity = this;

        androidx.appcompat.widget.Toolbar miActionBar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar = (Toolbar) findViewById(R.id.miActionBar2);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClickCancel(View v) {
        try {

            ArrayList<Object> gotData = new ArrayList<Object>();
            BaseDatos dataBase = new BaseDatos(context);
            gotData = dataBase.getUserData();
            String email = "";

            if (gotData != null) {
                int listSize = gotData.size();
                if(listSize > 0) {
                    email = gotData.get(0).toString();
                }
            }

            String url = "https://casa.sistemascasa.com:5174/ARCO/:" + email+"/";
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                intent.setPackage(null);
                context.startActivity(intent);
            }


        }catch (Exception e){
            mandarError();
        }


    }

    public void mandarError() {
        Toast.makeText(getApplicationContext(), "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void onClickAvisoArco(View v) {
        Intent intent_aviso = new Intent(this, AvisoDePrivacidadActivity.class);
        startActivity(intent_aviso);
    }
}