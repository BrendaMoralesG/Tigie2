package com.example.sistemascasa.tigie.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by desarrolloweb on 10/08/16.
 */
public class RestApiAdapter {

    public static final String BASE_URL = "https://movil.sistemascasa.com:8085/";
    //public static final String BASE_URL = "http://192.168.137.140:8085/";
    //public static final String BASE_URL = "http://10.40.65.16:8085/";
    //public static final String BASE_URL ="http://192.168.1.65:8085/";

    okhttp3.OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(55, TimeUnit.SECONDS)
            .build();

    public EndpointsService establecerConexionRestApi () {

        OkHttpClient client=new OkHttpClient();
        try {
            TLSSocketFactory tlsSocketFactory=new TLSSocketFactory();
            if (tlsSocketFactory.getTrustManager()!=null) {
                client = new OkHttpClient.Builder()
                        .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.getTrustManager())
                        .build();
            }
        } catch (KeyManagementException e) {
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsService.class);
    }

}
