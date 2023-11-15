package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by desarrolloweb on 18/08/16.
 */
public class Aladi {
    @SerializedName("idAladiPreference")
    @Expose
    private int idAladiPreference;
    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("countryDescription")
    @Expose
    private String countryDescription;
    @SerializedName("aladiPreferenceRate")
    @Expose
    private String aladiPreferenceRate;
    @SerializedName("aladiNota")
    @Expose
    private String aladiNota;
    @SerializedName("aladiDof")
    @Expose
    private String aladiDof;

    private int flagAladi;

    public Aladi () {

    }

    public Aladi ( int idAladiPreference,  int idTariffFraction, String countryCode, String countryDescription,String aladiPreferenceRate, String aladiNota, String aladiDof, int flagAladi) {
        this.idAladiPreference  = idAladiPreference;
        this.idTariffFraction   = idTariffFraction;
        this.countryCode        = countryCode;
        this.countryDescription = countryDescription;
        this.aladiPreferenceRate= aladiPreferenceRate;
        this.aladiNota          = aladiNota;
        this.aladiDof           = aladiDof;
        this.flagAladi          = flagAladi;
    }

    public int getFlagAladi() {
        return flagAladi;
    }

    public void setFlagAladi(int flagAladi) {
        this.flagAladi = flagAladi;
    }

    public int getIdAladiPreference() {
        return idAladiPreference;
    }

    public void setIdAladiPreference(int idAladiPreference) {
        this.idAladiPreference = idAladiPreference;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    public String getAladiPreferenceRate() {
        return aladiPreferenceRate;
    }

    public void setAladiPreferenceRate(String aladiPreferenceRate) {
        this.aladiPreferenceRate = aladiPreferenceRate;
    }

    public String getAladiNota() {
        return aladiNota;
    }

    public void setAladiNota(String aladiNota) {
        this.aladiNota = aladiNota;
    }

    public String getAladiDof() {
        return aladiDof;
    }

    public void setAladiDof(String aladiDof) {
        this.aladiDof = aladiDof;
    }
}
