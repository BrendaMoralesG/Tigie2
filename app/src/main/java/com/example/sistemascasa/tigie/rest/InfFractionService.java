package com.example.sistemascasa.tigie.rest;

import com.example.sistemascasa.tigie.pojo.Pdf;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by desarrolloweb on 3/08/16.
 */
public interface InfFractionService {

    @GET("/constructor")
    void getClient( @Query("fraction") String fraccion, Callback<Pdf> callback);

}
