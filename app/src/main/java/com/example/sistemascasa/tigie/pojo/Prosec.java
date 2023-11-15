package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class Prosec {
    @SerializedName("idProsecPreference")
    @Expose
    private int idProsecPreference;

    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("prosecDescriptionName")
    @Expose
    private String prosecDescriptionName;

    @SerializedName("prosecPreferenceAdualRate")
    @Expose
    private String prosecPreferenceAdualRate;

    @SerializedName("prosecNoteDescription")
    @Expose
    private String prosecNoteDescription;

    @SerializedName("prosecPreferenceAgreementDate")
    @Expose
    private String prosecPreferenceAgreementDate;

    public Prosec () {

    }

    public Prosec (int idProsecPreference, int idTariffFraction, String prosecDescriptionName, String prosecPreferenceAdualRate, String prosecNoteDescription, String prosecPreferenceAgreementDate) {
        this.idProsecPreference 		= idProsecPreference;
        this.idTariffFraction 			= idTariffFraction;
        this.prosecDescriptionName 		= prosecDescriptionName;
        this.prosecPreferenceAdualRate 	= prosecPreferenceAdualRate;
        this.prosecNoteDescription 		= prosecNoteDescription;
        this.prosecPreferenceAgreementDate = prosecPreferenceAgreementDate;
    }

    public int getIdProsecPreference() {
        return idProsecPreference;
    }

    public void setIdProsecPreference(int idProsecPreference) {
        this.idProsecPreference = idProsecPreference;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getProsecDescriptionName() {
        return prosecDescriptionName;
    }

    public void setProsecDescriptionName(String prosecDescriptionName) {
        this.prosecDescriptionName = prosecDescriptionName;
    }

    public String getProsecPreferenceAdualRate() {
        return prosecPreferenceAdualRate;
    }

    public void setProsecPreferenceAdualRate(String prosecPreferenceAdualRate) {
        this.prosecPreferenceAdualRate = prosecPreferenceAdualRate;
    }

    public String getProsecNoteDescription() {
        return prosecNoteDescription;
    }

    public void setProsecNoteDescription(String prosecNoteDescription) {
        this.prosecNoteDescription = prosecNoteDescription;
    }

    public String getProsecPreferenceAgreementDate() {
        return prosecPreferenceAgreementDate;
    }

    public void setProsecPreferenceAgreementDate(String prosecPreferenceAgreementDate) {
        this.prosecPreferenceAgreementDate = prosecPreferenceAgreementDate;
    }
}
