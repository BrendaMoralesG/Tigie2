package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class BorderStrip {

    @SerializedName("idBorderStripPreference")
    @Expose
    private int idBorderStripPreference;

    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("borderStripAccordanceTariffRate")
    @Expose
    private String borderStripAccordanceTariffRate;

    @SerializedName("borderStripAccordanceDescription")
    @Expose
    private String borderStripAccordanceDescription;

    @SerializedName("borderStripPreferenceAgreementDate")
    @Expose
    private String borderStripPreferenceAgreementDate;

    @SerializedName("efectiveDate")
    @Expose
    private String efectiveDate;

    @SerializedName("borderStripNoteDescription")
    @Expose
    private String borderStripNoteDescription;

    public BorderStrip () { }

    public BorderStrip (int idBorderStripPreference, int idTariffFraction, String borderStripAccordanceTariffRate, String borderStripAccordanceDescription, String borderStripPreferenceAgreementDate, String efectiveDate) {
        this.idBorderStripPreference 			= idBorderStripPreference;
        this.idTariffFraction 					= idTariffFraction;
        this.borderStripAccordanceTariffRate 	= borderStripAccordanceTariffRate;
        this.borderStripAccordanceDescription 	= borderStripAccordanceDescription;
        this.borderStripPreferenceAgreementDate = borderStripPreferenceAgreementDate;
        this.efectiveDate                       = efectiveDate;
    }

    public BorderStrip (int idBorderStripPreference, int idTariffFraction, String borderStripAccordanceTariffRate, String borderStripAccordanceDescription, String borderStripPreferenceAgreementDate, String efectiveDate,
                        String borderStripNoteDescription) {
        this.idBorderStripPreference 			= idBorderStripPreference;
        this.idTariffFraction 					= idTariffFraction;
        this.borderStripAccordanceTariffRate 	= borderStripAccordanceTariffRate;
        this.borderStripAccordanceDescription 	= borderStripAccordanceDescription;
        this.borderStripPreferenceAgreementDate = borderStripPreferenceAgreementDate;
        this.efectiveDate                       = efectiveDate;
        this.borderStripNoteDescription = borderStripNoteDescription;
    }

    public String getEfectiveDate() {
        return efectiveDate;
    }

    public void setEfectiveDate(String efectiveDate) {
        this.efectiveDate = efectiveDate;
    }

    public int getIdBorderStripPreference() {
        return idBorderStripPreference;
    }


    public void setIdBorderStripPreference(int idBorderStripPreference) {
        this.idBorderStripPreference = idBorderStripPreference;
    }


    public int getIdTariffFraction() {
        return idTariffFraction;
    }


    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }


    public String getBorderStripAccordanceTariffRate() {
        return borderStripAccordanceTariffRate;
    }


    public void setBorderStripAccordanceTariffRate(String borderStripAccordanceTariffRate) {
        this.borderStripAccordanceTariffRate = borderStripAccordanceTariffRate;
    }


    public String getBorderStripAccordanceDescription() {
        return borderStripAccordanceDescription;
    }


    public void setBorderStripAccordanceDescription(String borderStripAccordanceDescription) {
        this.borderStripAccordanceDescription = borderStripAccordanceDescription;
    }


    public String getBorderStripPreferenceAgreementDate() {
        return borderStripPreferenceAgreementDate;
    }


    public void setBorderStripPreferenceAgreementDate(String borderStripPreferenceAgreementDate) {
        this.borderStripPreferenceAgreementDate = borderStripPreferenceAgreementDate;
    }

    public String getBorderStripNoteDescription() {
        return borderStripNoteDescription;
    }

    public void setBorderStripNoteDescription(String borderStripNoteDescription) {
        this.borderStripNoteDescription = borderStripNoteDescription;
    }
}
