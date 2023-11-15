package com.example.sistemascasa.tigie.rest;

import com.example.sistemascasa.tigie.pojo.Aladi;
import com.example.sistemascasa.tigie.pojo.AlliancePacific;
import com.example.sistemascasa.tigie.pojo.AndroidUsers;
import com.example.sistemascasa.tigie.pojo.Annexes;
import com.example.sistemascasa.tigie.pojo.BorderStrip;
import com.example.sistemascasa.tigie.pojo.CompensatoryShares;
import com.example.sistemascasa.tigie.pojo.Correlations;
import com.example.sistemascasa.tigie.pojo.EstimatedPrices;
import com.example.sistemascasa.tigie.pojo.Fractions;
import com.example.sistemascasa.tigie.pojo.Headings;
import com.example.sistemascasa.tigie.pojo.ImportPermits;
import com.example.sistemascasa.tigie.pojo.InfFRaction;
import com.example.sistemascasa.tigie.pojo.ModulesData;
import com.example.sistemascasa.tigie.pojo.OtherTaxes;
import com.example.sistemascasa.tigie.pojo.Params;
import com.example.sistemascasa.tigie.pojo.Prosec;
import com.example.sistemascasa.tigie.pojo.Subheadings;
import com.example.sistemascasa.tigie.pojo.Tlc;
import com.example.sistemascasa.tigie.pojo.TlcNotes;
import com.example.sistemascasa.tigie.pojo.Transpacific;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by desarrolloweb on 10/08/16.
 */
public interface EndpointsService {
    /**
     * Enviar correo
     */
    @POST("/sendEmail")
    Call<String> sendEmail(@Body Params params, @Header("Access") String access, @Header("Content-Type") String content_type);
    //Call<Boolean> sendEmail(@Query("email") String email, @Query("tokenSecurity") String tokenSecurity);

    /**
     * Obtener modulos principales
     */
    @GET("/informationdata")
    Call<InfFRaction> getInformationData(@Query("id_fraccion") Integer id_fraccion, @Query("fraccion") String fraccion, @Header("word1") String email, @Header("word2") String token, @Query("tigie") Integer tigie);

    @GET("gettlc")
    Call<ArrayList<Tlc>> getTlc(@Query("id_fraccion") Integer id_fraccion, @Header("word1") String email, @Header("word2") String token, @Query("tigie") Integer tigie);

    @GET("getaladipreference")
    Call<ArrayList<Aladi>> getAladi(@Query("id_fraccion") Integer id_fraccion, @Header("word1") String email, @Header("word2") String token, @Query("tigie") Integer tigie);

    @GET("getTlcTranspacific")
    Call<ArrayList<Transpacific>> getTlcTranspacific(@Query("id_fraccion") Integer id_fraccion, @Header("word1") String email, @Header("word2") String token, @Query("tigie") Integer tigie);

    @GET("gettlcpacificalliance")
    Call<ArrayList<AlliancePacific>> getAlliancePacific(@Query("id_fraccion") Integer id_fraccion, @Header("word1") String email, @Header("word2") String token, @Query("tigie") Integer tigie);

    @GET("getmodules")
    Call<ArrayList<ModulesData>> getmodules(@Query("id_fraccion") Integer id_fraccion, @Header("word1") String email, @Header("word2") String token, @Query("tigie") Integer tigie);

    /**
     * Inciar Sesion
     */
    @GET("validateemail")
    Call<Boolean> validateEmail(@Query("email") String email);

    @GET("getandroiduser")
    Call<ArrayList<AndroidUsers>> getandroiduser(@Query("name") String name, @Query("password") String password, @Query("email") String email, @Query("ocupacion") String ocupacion,
                                                 @Query("username2") String username2, @Query("password2") String password2, @Query("grant_type2") String grant_type2);

    @GET("validateuser")
    Call<ArrayList<AndroidUsers>> validateUser(@Query("emailLogin") String emailLogin, @Query("passwordLogin") String passwordLogin);

    /**
     * Obtener Modulos
     */
    @GET("getcompensatoryshares")
    Call<ArrayList<CompensatoryShares>> getcompensatoryshares(@Query("idTariffFraction") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getprosec")
    Call<ArrayList<Prosec>> getprosec(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getannexes")
    Call<ArrayList<Annexes>> getannexes(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getestimatedprices")
    Call<ArrayList<EstimatedPrices>> getestimatedprices(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getimportpermits")
    Call<ArrayList<ImportPermits>> getimportpermits(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getborderstrip")
    Call<ArrayList<BorderStrip>> getborderstrip(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("gettlcnotes")
    Call<ArrayList<TlcNotes>> gettlcnotes(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getothertaxes")
    Call<ArrayList<OtherTaxes>> getothertaxes(@Query("id_fraccion") Integer id_fraccion, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    /**
     *
     */
    @GET("getHeadings")
    Call<ArrayList<Headings>> getHeadings(@Query("idTariffChapter") Integer idTariffChapter, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getHeadingsByCode")
    Call<ArrayList<Headings>> getHeadingsByCode(@Query("tariffHeadingCode") String tariffHeadingCode, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getSubHeadings")
    Call<ArrayList<Subheadings>> getSubHeadings(@Query("idTariffHeading") Integer idTariffHeading, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getSubHeadingsByCode")
    Call<ArrayList<Subheadings>> getSubHeadingsByCode(@Query("codeSubHeading") String codeSubHeading, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getFractions")
    Call<ArrayList<Fractions>> getFractions(@Query("idTariffSubheading") Integer idTariffSubheading, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getFractionsByCode")
    Call<ArrayList<Fractions>> getFractionsByCode(@Query("tariffFractionCode") String fraccionCode, @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getFractionsByString")
    Call<ArrayList<Fractions>> getFractionsByString(@Query("tariffFractionDescription") String fraccion,  @Query("tigie") Integer tigie, @Header("word1") String email, @Header("word2") String token);

    @GET("getCorrelations")
    Call<ArrayList<Correlations>> getCorrelations(@Query("id_fraction") Integer id_fraction, @Header("word1") String email, @Header("word2") String token);

}
