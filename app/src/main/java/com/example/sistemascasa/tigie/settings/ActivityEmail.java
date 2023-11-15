package com.example.sistemascasa.tigie.settings;

import androidx.core.widget.ContentLoadingProgressBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.pojo.AndroidUsers;
import com.example.sistemascasa.tigie.pojo.Params;
import com.example.sistemascasa.tigie.rest.EndpointsService;
import com.example.sistemascasa.tigie.rest.RestApiAdapter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class ActivityEmail extends AppCompatActivity {

    private EditText edEmail;
    private EditText edOcupacion;
    private EditText edEmpresa, edPass;

    private  EditText edName;
    String tokenSecurity;
    String name;
    String email;
    String cadena;
    String cadena2;
    String ocupacion;
    String empresa;
    String concatenacion;
    String pass;
    private ContentLoadingProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_email);

        edEmail     = (EditText) findViewById(R.id.editTextEmail);
        edPass      = (EditText) findViewById(R.id.editTextPass);
        edName      = (EditText) findViewById(R.id.editTextName);
        edOcupacion = (EditText) findViewById(R.id.editTextWorking);
        edEmpresa   = (EditText) findViewById(R.id.editTextEnterprice);
        progress  = (ContentLoadingProgressBar) findViewById(R.id.progressBar);

        Button btnSend = (Button) findViewById(R.id.ButtonSendFeedback);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email       = edEmail.getText().toString();
                name        = edName.getText().toString();
                ocupacion   = edOcupacion.getText().toString();
                empresa     = edEmpresa.getText().toString();
                pass        = edPass.getText().toString();

                concatenacion = ocupacion + "  " + empresa;

                if(ocupacion.equals("") || ocupacion == null ) {
                    ocupacion = "Sin ocupacion";
                }

                if(empresa.equals("") || empresa == null ) {
                    empresa = "Sin empresa";
                }

                Random rnd = new Random();
                cadena = String.valueOf(rnd.nextDouble()).substring(0, 5);
                cadena2 = String.valueOf(rnd.nextDouble()).substring(0, 3);

                if(email.equals("")) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, el Email es obligatorio.", Toast.LENGTH_LONG).show();
                }  else if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, el Nombre es obligatorio.", Toast.LENGTH_LONG).show();
                }  else if (pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, la Contraseña es obligatoria.", Toast.LENGTH_LONG).show();
                } else {
                    Boolean response = isEmailValid(email);
                    if (response) {
                        progress.show();
                        final RestApiAdapter restApiAdapter        = new RestApiAdapter();
                        final EndpointsService service1            = restApiAdapter.establecerConexionRestApi();
                        Call<Boolean> servic = service1.validateEmail(email);
                        servic.enqueue(new Callback<Boolean>() {

                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean resp = response.body();
                                if(resp) {
                                    tokenSecurity = "";
                                    try {
                                        Params params = new Params();
                                        params.setParam1(email);
                                        params.setParam2(tokenSecurity);
                                        params.setUsername("us3r4ppm0v1l");
                                        params.setPassword("mb$,W<V[hx,v6bEK");
                                        params.setGrant_type("appmovil");

                                        String valor = email+tokenSecurity;
                                        String val = sha256String(valor);

                                        Call<String> servic = service1.sendEmail(params, val, "application/json");
                                        servic.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                String resp = response.body();
                                                if (resp.toLowerCase().contains("exitosa")) {
                                                    Toast.makeText(getApplicationContext(), "Operación exitosa.", Toast.LENGTH_LONG).show();
                                                    finish();
                                                } else {
                                                    mandarError();
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                mandarError();
                                            }
                                        });
                                    } catch (Exception e) {
                                    }
                                } else {
                                    tokenSecurity = pass;
                                    String username = "us3r4ppm0v1l";
                                    String password = "mb$,W<V[hx,v6bEK";
                                    String grant_type = "appmovil";

                                    RestApiAdapter restApiAdapter = new RestApiAdapter();
                                    EndpointsService tlcService = restApiAdapter.establecerConexionRestApi();
                                    Call<ArrayList<AndroidUsers>> tlcCall = tlcService.getandroiduser(name, tokenSecurity, email, concatenacion, username, password, grant_type);

                                    tlcCall.enqueue(new Callback<ArrayList<AndroidUsers>>() {
                                        @Override
                                        public void onResponse(Call<ArrayList<AndroidUsers>> call, Response<ArrayList<AndroidUsers>> response) {
                                            int tamanio = response.body().size();

                                            if (tamanio == 2) {
                                                try {
                                                    RestApiAdapter restApiAdapter = new RestApiAdapter();
                                                    EndpointsService service1     = restApiAdapter.establecerConexionRestApi();

                                                    Params params = new Params();
                                                    params.setParam1(email);
                                                    params.setParam2(tokenSecurity);
                                                    params.setUsername("us3r4ppm0v1l");
                                                    params.setPassword("mb$,W<V[hx,v6bEK");
                                                    params.setGrant_type("appmovil");

                                                    String valor = email+tokenSecurity;
                                                    String val = sha256String(valor);

                                                    Call<String> servic           = service1.sendEmail(params, val, "application/json");
                                                    servic.enqueue(new Callback<String>() {
                                                        @Override
                                                        public void onResponse(Call<String> call, Response<String> response) {
                                                            String resp = response.body();
                                                            if (resp.toLowerCase().contains("exitosa")) {
                                                                Toast.makeText(getApplicationContext(), "Operación exitosa.", Toast.LENGTH_LONG).show();
                                                                finish();
                                                            } else {
                                                                mandarError();
                                                            }
                                                        }
                                                        @Override
                                                        public void onFailure(Call<String> call, Throwable t) {
                                                            mandarError();
                                                        }
                                                    });
                                                } catch (Exception e) {
                                                }
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ArrayList<AndroidUsers>> call, Throwable t) {
                                            mandarError();
                                        }
                                    });
                                }
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                mandarError();
                            }
                        });
                    } else  {
                        Toast.makeText(getApplicationContext(), "Lo sentimos, el Email es incorrecto.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public static String sha256String(String source) {
        byte[] hash = null;
        String hashCode = null;// w  ww  .  j  a va 2 s.c  o m
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(source.getBytes());
        } catch (NoSuchAlgorithmException e) {

        }

        if (hash != null) {
            StringBuilder hashBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1) {
                    hashBuilder.append("0");
                    hashBuilder.append(hex.charAt(hex.length() - 1));
                } else {
                    hashBuilder.append(hex.substring(hex.length() - 2));
                }
            }
            hashCode = hashBuilder.toString();
        }

        return hashCode;
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    public void mandarError() {
        Toast.makeText(getApplicationContext(), "¡ Fallo de Conexión ! ... Inténtelo más tarde, por favor.", Toast.LENGTH_LONG).show();
        progress.hide();
    }
}

