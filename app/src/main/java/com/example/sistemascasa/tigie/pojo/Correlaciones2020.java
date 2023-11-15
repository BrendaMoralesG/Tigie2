package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Correlaciones2020 {

    @SerializedName("idTariffFraction")
    @Expose
    private Integer idTariffFraction;

    @SerializedName("tariffFractionCode")
    @Expose
    private String tariffFractionCode;

    @SerializedName("tariffNicoCode")
    @Expose
    private String tariffNicoCode;

    @SerializedName("tigie")
    @Expose
    private Integer tigie;

    public Correlaciones2020 () {

    }

    public Correlaciones2020 (Integer idTariffFraction, String tariffFractionCode, String tariffNicoCode, Integer tigie) {
        this.idTariffFraction = idTariffFraction;
        this.tariffFractionCode = tariffFractionCode;
        this.tariffNicoCode = tariffNicoCode;
        this.tigie = tigie;
    }

    public Integer getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(Integer idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getTariffFractionCode() {
        return tariffFractionCode;
    }

    public void setTariffFractionCode(String tariffFractionCode) {
        this.tariffFractionCode = tariffFractionCode;
    }

    public String getTariffNicoCode() {
        return tariffNicoCode;
    }

    public void setTariffNicoCode(String tariffNicoCode) {
        this.tariffNicoCode = tariffNicoCode;
    }

    public Integer getTigie() {
        return tigie;
    }

    public void setTigie(Integer tigie) {
        this.tigie = tigie;
    }
}
