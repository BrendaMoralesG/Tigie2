package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by desarrolloweb on 3/08/16.
 */
public class InfFRaction {
    @Expose
    private int idFraction;
    @Expose
    private String tariffChapterCode;
    @Expose
    private String tariffChapterDescription;
    @Expose
    private String tariffHeadingCode;
    @Expose
    private String tariffHeadingDescription;
    @Expose
    private String tariffSubheadingCode;
    @Expose
    private String tariffSubheadingDescription;
    @Expose
    private String tariffFractionCode;
    @Expose
    private String tariffFractionDescription;
    @Expose
    private String tariffPreSubheadingDescription;
    @Expose
    private String  tariffPrincipalPublicationDate;
    @Expose
    private String  measurementUnitDescription;
    @Expose
    private String  impuestoGeneralDeImpo;
    @Expose
    private String  unidadDeMedida;
    @Expose
    private String  impuestoGeneralDeExpo;
    @Expose
    private String  iepsExpo;
    @Expose
    private String	iepsImpo;
    @Expose
    private String  isan;
    @Expose
    private String  otherTaxesDescriptionCode;
    @Expose
    private String  ivaRateAmountCode;

    @Expose
    private String  nico;

    @Expose
    private String  correlacion;

    @SerializedName("correlaciones2020")
    @Expose
    ArrayList<Correlaciones2020> correlaciones2020;

    public String getTariffChapterCode() {
        return tariffChapterCode;
    }

    public void setTariffChapterCode(String tariffChapterCode) {
        this.tariffChapterCode = tariffChapterCode;
    }

    public int getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(int idFraction) {
        this.idFraction = idFraction;
    }

    public String getTariffPreSubheadingDescription() {
        return tariffPreSubheadingDescription;
    }

    public void setTariffPreSubheadingDescription(String tariffPreSubheadingDescription) {
        this.tariffPreSubheadingDescription = tariffPreSubheadingDescription;
    }

    public String getTariffFractionCode() {
        return tariffFractionCode;
    }

    public void setTariffFractionCode(String tariffFractionCode) {
        this.tariffFractionCode = tariffFractionCode;
    }

    public String getTariffSubheadingDescription() {
        return tariffSubheadingDescription;
    }

    public void setTariffSubheadingDescription(String tariffSubheadingDescription) {
        this.tariffSubheadingDescription = tariffSubheadingDescription;
    }

    public String getTariffSubheadingCode() {
        return tariffSubheadingCode;
    }

    public void setTariffSubheadingCode(String tariffSubheadingCode) {
        this.tariffSubheadingCode = tariffSubheadingCode;
    }

    public String getTariffHeadingDescription() {
        return tariffHeadingDescription;
    }

    public void setTariffHeadingDescription(String tariffHeadingDescription) {
        this.tariffHeadingDescription = tariffHeadingDescription;
    }

    public String getTariffHeadingCode() {
        return tariffHeadingCode;
    }

    public void setTariffHeadingCode(String tariffHeadingCode) {
        this.tariffHeadingCode = tariffHeadingCode;
    }

    public String getTariffChapterDescription() {
        return tariffChapterDescription;
    }

    public void setTariffChapterDescription(String tariffChapterDescription) {
        this.tariffChapterDescription = tariffChapterDescription;
    }

    public String getTariffFractionDescription() {
        return tariffFractionDescription;
    }

    public void setTariffFractionDescription(String tariffFractionDescription) {
        this.tariffFractionDescription = tariffFractionDescription;
    }

    public String getTariffPrincipalPublicationDate() {
        return tariffPrincipalPublicationDate;
    }

    public void setTariffPrincipalPublicationDate(String tariffPrincipalPublicationDate) {
        this.tariffPrincipalPublicationDate = tariffPrincipalPublicationDate;
    }

    public String getIvaRateAmountCode() {
        return ivaRateAmountCode;
    }

    public void setIvaRateAmountCode(String ivaRateAmountCode) {
        this.ivaRateAmountCode = ivaRateAmountCode;
    }

    public String getOtherTaxesDescriptionCode() {
        return otherTaxesDescriptionCode;
    }

    public void setOtherTaxesDescriptionCode(String otherTaxesDescriptionCode) {
        this.otherTaxesDescriptionCode = otherTaxesDescriptionCode;
    }

    public String getIsan() {
        return isan;
    }

    public void setIsan(String isan) {
        this.isan = isan;
    }

    public String getIepsImpo() {
        return iepsImpo;
    }

    public void setIepsImpo(String iepsImpo) {
        this.iepsImpo = iepsImpo;
    }

    public String getIepsExpo() {
        return iepsExpo;
    }

    public void setIepsExpo(String iepsExpo) {
        this.iepsExpo = iepsExpo;
    }

    public String getImpuestoGeneralDeExpo() {
        return impuestoGeneralDeExpo;
    }

    public void setImpuestoGeneralDeExpo(String impuestoGeneralDeExpo) {
        this.impuestoGeneralDeExpo = impuestoGeneralDeExpo;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public String getImpuestoGeneralDeImpo() {
        return impuestoGeneralDeImpo;
    }

    public void setImpuestoGeneralDeImpo(String impuestoGeneralDeImpo) {
        this.impuestoGeneralDeImpo = impuestoGeneralDeImpo;
    }

    public String getMeasurementUnitDescription() {
        return measurementUnitDescription;
    }

    public void setMeasurementUnitDescription(String measurementUnitDescription) {
        this.measurementUnitDescription = measurementUnitDescription;
    }

    public String getNico() {
        return nico;
    }

    public void setNico(String nico) {
        this.nico = nico;
    }

    public String getCorrelacion() {
        return correlacion;
    }

    public void setCorrelacion(String correlacion) {
        this.correlacion = correlacion;
    }

    public ArrayList<Correlaciones2020> getCorrelaciones2020() {
        return correlaciones2020;
    }

    public void setCorrelaciones2020(ArrayList<Correlaciones2020> correlaciones2020) {
        this.correlaciones2020 = correlaciones2020;
    }

    @Override
    public String toString() {
        return "InfFRaction{" +
                "idFraction=" + idFraction +
                ", tariffChapterCode='" + tariffChapterCode + '\'' +
                ", tariffChapterDescription='" + tariffChapterDescription + '\'' +
                ", tariffHeadingCode='" + tariffHeadingCode + '\'' +
                ", tariffHeadingDescription='" + tariffHeadingDescription + '\'' +
                ", tariffSubheadingCode='" + tariffSubheadingCode + '\'' +
                ", tariffSubheadingDescription='" + tariffSubheadingDescription + '\'' +
                ", tariffFractionCode='" + tariffFractionCode + '\'' +
                ", tariffFractionDescription='" + tariffFractionDescription + '\'' +
                ", tariffPreSubheadingDescription='" + tariffPreSubheadingDescription + '\'' +
                ", tariffPrincipalPublicationDate='" + tariffPrincipalPublicationDate + '\'' +
                ", measurementUnitDescription='" + measurementUnitDescription + '\'' +
                ", impuestoGeneralDeImpo='" + impuestoGeneralDeImpo + '\'' +
                ", unidadDeMedida='" + unidadDeMedida + '\'' +
                ", impuestoGeneralDeExpo='" + impuestoGeneralDeExpo + '\'' +
                ", iepsExpo='" + iepsExpo + '\'' +
                ", iepsImpo='" + iepsImpo + '\'' +
                ", isan='" + isan + '\'' +
                ", otherTaxesDescriptionCode='" + otherTaxesDescriptionCode + '\'' +
                ", ivaRateAmountCode='" + ivaRateAmountCode + '\'' +
                '}';
    }
}
