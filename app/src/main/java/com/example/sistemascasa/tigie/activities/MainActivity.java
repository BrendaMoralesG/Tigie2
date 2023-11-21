package com.example.sistemascasa.tigie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemascasa.tigie.AvisoDePrivacidadActivity;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.adapter.TigiesAdapter;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.AndroidUsers;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;
import com.example.sistemascasa.tigie.settings.ActivityEmail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  {
    private Button btnSigIn;
    private Button btnFormEmail;
    private Context context;
    private EditText emailLogin;
    private  EditText passwordLogin;
    private BaseDatos dataBase;
    private Activity activity;
    private ConstructorData constructorData;
    Boolean success;

    String email;
    String pass;
    String status;
    String token;

    public static MainActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBase = null;
        context  = getApplicationContext();
        activity = this;
        constructorData = new ConstructorData(context);
        constructorData.startDb();
        ArrayList<Object> gotData = new ArrayList<Object>();
        dataBase = new BaseDatos(context);
        gotData = dataBase.getUserData();
        if (gotData == null) {
            status = "0";
        } else {
            int listSize = gotData.size();

            if(listSize > 0) {
                status = "1" ;
                email = gotData.get(0).toString();
                token = gotData.get(1).toString();
            } else {
                status = "0" ;
                email = "";
                token = "";
            }
        }
        super.onCreate(savedInstanceState);

        if (status.equals("1") || status.equals(1)) {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsService service1 = restApiAdapter.establecerConexionRestApi();
            Call<ArrayList<AndroidUsers>> servic = service1.validateUser(email, token);

            servic.enqueue(new Callback<ArrayList<AndroidUsers>>() {
                @Override
                public void onResponse(Call<ArrayList<AndroidUsers>> call, Response<ArrayList<AndroidUsers>> response) {
                    int tamanio = response.body().size();

                    if (tamanio == 2) {
                        setContentView(R.layout.activity_main);
                        ArrayList<String> tigies = new ArrayList<String>();
                        tigies.add("TIGIE 2012: Quinta Enmienda");
                        tigies.add("TIGIE 2020: Sexta Enmienda, Correlacionada");
                        RecyclerView rvC = (RecyclerView) findViewById(R.id.rvTigies);
                        LinearLayoutManager llm = new LinearLayoutManager(context);
                        rvC.setLayoutManager(llm);

                        TigiesAdapter adapter = new TigiesAdapter(tigies, activity);
                        rvC.setAdapter(adapter);
                    } else if (tamanio == 3 || tamanio == 1 || tamanio == 0) {
                        buttons ();
                    }
                }
                @Override
                public void onFailure(Call<ArrayList<AndroidUsers>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error de conexión. Por favor vuelva a intentarlo.", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            buttons ();
        }


    }

    public void buttons () {
        setContentView(R.layout.activity_login);
        btnSigIn = (Button) findViewById(R.id.sign_in);
        btnSigIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == btnSigIn){
                    emailLogin      = (EditText) findViewById(R.id.email);
                    passwordLogin   = (EditText) findViewById(R.id.password);

                    if (emailLogin.getText().toString().equals("") || passwordLogin.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Los sentimos, debe ingresar las credenciales para acceder.", Toast.LENGTH_LONG).show();
                    } else {
                        validationCredentials(emailLogin.getText().toString(),  passwordLogin.getText().toString());
                    }
                }
            }
        });

        btnFormEmail = (Button) findViewById(R.id.send_email);
        btnFormEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_chapters = new Intent(getApplicationContext(), ActivityEmail.class);
                startActivity(intent_chapters);
            }
        });
    }

    public void validationCredentials (String emailLogin, String passwordLogin) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsService service1 = restApiAdapter.establecerConexionRestApi();
        Call<ArrayList<AndroidUsers>> servic = service1.validateUser(emailLogin,  passwordLogin);

        email= emailLogin;
        pass =passwordLogin;

        servic.enqueue(new Callback<ArrayList<AndroidUsers>>() {
            @Override
            public void onResponse(Call<ArrayList<AndroidUsers>> call, Response<ArrayList<AndroidUsers>> response) {
                int tamanio = response.body().size();
                if (tamanio == 2) {
                    setContentView(R.layout.activity_main);
                    context = getApplicationContext();
                    constructorData = new ConstructorData(context);
                    success = constructorData.insertUserData(email, pass, 1);
                    Intent intent_chapters = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent_chapters);
                    finish();
                } else if (tamanio == 3) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, sus credeciales expiraron.", Toast.LENGTH_LONG).show();
                } else if (tamanio == 1 || tamanio == 0) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, sus credeciales son incorrectas.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AndroidUsers>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexión. Por favor vuelva a intentarlo.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickAviso(View v) {
        Intent intent_aviso = new Intent(this, AvisoDePrivacidadActivity.class);
        startActivity(intent_aviso);
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
