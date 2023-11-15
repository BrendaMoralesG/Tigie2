package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class OtherTaxes {

    @SerializedName("idOtherTaxes")
    @Expose
    private int    idOtherTaxes;

    @SerializedName("idTariffFraction")
    @Expose
    private int    idTariffFraction;

    @SerializedName("titleOtherTaxes")
    @Expose
    private String titleOtherTaxes;

    @SerializedName("descriptionOtherTaxes")
    @Expose
    private String descriptionOtherTaxes;

    @SerializedName("publicationDateOtherTaxes")
    @Expose
    private String publicationDateOtherTaxes;

    @SerializedName("aplicationDateOtherTaxes")
    @Expose
    private String aplicationDateOtherTaxes;

    @SerializedName("effectiveDateOtherTaxes")
    @Expose
    private String effectiveDateOtherTaxes;

    @SerializedName("ivaNoteDescription")
    @Expose
    private String ivaNoteDescription;

    public OtherTaxes () {

    }

    public OtherTaxes ( int idOtherTaxes, int idTariffFraction, String titleOtherTaxes, String descriptionOtherTaxes,
                        String publicationDateOtherTaxes, String aplicationDateOtherTaxes, String effectiveDateOtherTaxes) {
        this.idOtherTaxes	  			= idOtherTaxes;
        this.idTariffFraction 			= idTariffFraction;
        this.titleOtherTaxes  			= titleOtherTaxes;
        this.descriptionOtherTaxes 		= descriptionOtherTaxes;
        this.publicationDateOtherTaxes 	= publicationDateOtherTaxes;
        this.aplicationDateOtherTaxes	= aplicationDateOtherTaxes;
        this.effectiveDateOtherTaxes	= effectiveDateOtherTaxes;
    }

    public OtherTaxes ( int idOtherTaxes, int idTariffFraction, String titleOtherTaxes, String descriptionOtherTaxes,
                        String publicationDateOtherTaxes, String aplicationDateOtherTaxes, String effectiveDateOtherTaxes, String ivaNoteDescription) {
        this.idOtherTaxes	  			= idOtherTaxes;
        this.idTariffFraction 			= idTariffFraction;
        this.titleOtherTaxes  			= titleOtherTaxes;
        this.descriptionOtherTaxes 		= descriptionOtherTaxes;
        this.publicationDateOtherTaxes 	= publicationDateOtherTaxes;
        this.aplicationDateOtherTaxes	= aplicationDateOtherTaxes;
        this.effectiveDateOtherTaxes	= effectiveDateOtherTaxes;
        this.ivaNoteDescription = ivaNoteDescription;
    }

    public int getIdOtherTaxes() {
        return idOtherTaxes;
    }


    public void setIdOtherTaxes(int idOtherTaxes) {
        this.idOtherTaxes = idOtherTaxes;
    }


    public int getIdTariffFraction() {
        return idTariffFraction;
    }


    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }


    public String getTitleOtherTaxes() {
        return titleOtherTaxes;
    }


    public void setTitleOtherTaxes(String titleOtherTaxes) {
        this.titleOtherTaxes = titleOtherTaxes;
    }


    public String getDescriptionOtherTaxes() {
        return descriptionOtherTaxes;
    }


    public void setDescriptionOtherTaxes(String descriptionOtherTaxes) {
        this.descriptionOtherTaxes = descriptionOtherTaxes;
    }


    public String getPublicationDateOtherTaxes() {
        return publicationDateOtherTaxes;
    }


    public void setPublicationDateOtherTaxes(String publicationDateOtherTaxes) {
        this.publicationDateOtherTaxes = publicationDateOtherTaxes;
    }


    public String getAplicationDateOtherTaxes() {
        return aplicationDateOtherTaxes;
    }


    public void setAplicationDateOtherTaxes(String aplicationDateOtherTaxes) {
        this.aplicationDateOtherTaxes = aplicationDateOtherTaxes;
    }


    public String getEffectiveDateOtherTaxes() {
        return effectiveDateOtherTaxes;
    }


    public void setEffectiveDateOtherTaxes(String effectiveDateOtherTaxes) {
        this.effectiveDateOtherTaxes = effectiveDateOtherTaxes;
    }

    public String getIvaNoteDescription() {
        return ivaNoteDescription;
    }

    public void setIvaNoteDescription(String ivaNoteDescription) {
        this.ivaNoteDescription = ivaNoteDescription;
    }
}
