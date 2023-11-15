package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Correlations {
    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("tariffFractionCode")
    @Expose
    private String tariffFractionCode;

    @SerializedName("tariffFractionDescription")
    @Expose
    private String tariffFractionDescription;

    @SerializedName("tariffPrincipalImport")
    @Expose
    private String tariffPrincipalImport;

    @SerializedName("tariffPrincipalExport")
    @Expose
    private String tariffPrincipalExport;

    @SerializedName("measurementUnitDescription")
    @Expose
    private String measurementUnitDescription;

    public Correlations(){

    }

    public Correlations(int idTariffFraction, String tariffFractionCode, String tariffFractionDescription, String tariffPrincipalImport, String tariffPrincipalExport, String measurementUnitDescription) {
        this.idTariffFraction = idTariffFraction;
        this.tariffFractionCode = tariffFractionCode;
        this.tariffFractionDescription = tariffFractionDescription;
        this.tariffPrincipalImport = tariffPrincipalImport;
        this.tariffPrincipalExport = tariffPrincipalExport;
        this.measurementUnitDescription = measurementUnitDescription;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getTariffFractionCode() {
        return tariffFractionCode;
    }

    public void setTariffFractionCode(String tariffFractionCode) {
        this.tariffFractionCode = tariffFractionCode;
    }

    public String getTariffFractionDescription() {
        return tariffFractionDescription;
    }

    public void setTariffFractionDescription(String tariffFractionDescription) {
        this.tariffFractionDescription = tariffFractionDescription;
    }

    public String getTariffPrincipalImport() {
        return tariffPrincipalImport;
    }

    public void setTariffPrincipalImport(String tariffPrincipalImport) {
        this.tariffPrincipalImport = tariffPrincipalImport;
    }

    public String getTariffPrincipalExport() {
        return tariffPrincipalExport;
    }

    public void setTariffPrincipalExport(String tariffPrincipalExport) {
        this.tariffPrincipalExport = tariffPrincipalExport;
    }

    public String getMeasurementUnitDescription() {
        return measurementUnitDescription;
    }

    public void setMeasurementUnitDescription(String measurementUnitDescription) {
        this.measurementUnitDescription = measurementUnitDescription;
    }
}
