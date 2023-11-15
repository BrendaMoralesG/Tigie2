package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class EstimatedPrices {
    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("estimatedPrice")
    @Expose
    private String estimatedPrice;

    @SerializedName("estimatedPriceNote")
    @Expose
    private String estimatedPriceNote;

    @SerializedName("estimatedPricePublicationDate")
    @Expose
    private String estimatedPricePublicationDate;

    @SerializedName("estimatedPriceAplicationDate")
    @Expose
    private String estimatedPriceAplicationDate;

    @SerializedName("estimatedPriceEffectiveDate")
    @Expose
    private String estimatedPriceEffectiveDate;

    @SerializedName("nico")
    @Expose
    private String nico;

    public EstimatedPrices () { }

    public EstimatedPrices (int idTariffFraction, String estimatedPrice, String estimatedPriceNote, String estimatedPricePublicationDate, String estimatedPriceAplicationDate, String estimatedPriceEffectiveDate) {
        this.idTariffFraction 				= idTariffFraction;
        this.estimatedPrice 				= estimatedPrice;
        this.estimatedPriceNote 			= estimatedPriceNote;
        this.estimatedPricePublicationDate 	= estimatedPricePublicationDate;
        this.estimatedPriceAplicationDate 	= estimatedPriceAplicationDate;
        this.estimatedPriceEffectiveDate 	= estimatedPriceEffectiveDate;
    }

    public EstimatedPrices (int idTariffFraction, String estimatedPrice, String estimatedPriceNote, String estimatedPricePublicationDate, String estimatedPriceAplicationDate, String estimatedPriceEffectiveDate, String nico) {
        this.idTariffFraction 				= idTariffFraction;
        this.estimatedPrice 				= estimatedPrice;
        this.estimatedPriceNote 			= estimatedPriceNote;
        this.estimatedPricePublicationDate 	= estimatedPricePublicationDate;
        this.estimatedPriceAplicationDate 	= estimatedPriceAplicationDate;
        this.estimatedPriceEffectiveDate 	= estimatedPriceEffectiveDate;
        this.nico = nico;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(String estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getEstimatedPriceNote() {
        return estimatedPriceNote;
    }

    public void setEstimatedPriceNote(String estimatedPriceNote) {
        this.estimatedPriceNote = estimatedPriceNote;
    }

    public String getEstimatedPricePublicationDate() {
        return estimatedPricePublicationDate;
    }

    public void setEstimatedPricePublicationDate(String estimatedPricePublicationDate) {
        this.estimatedPricePublicationDate = estimatedPricePublicationDate;
    }

    public String getEstimatedPriceAplicationDate() {
        return estimatedPriceAplicationDate;
    }

    public void setEstimatedPriceAplicationDate(String estimatedPriceAplicationDate) {
        this.estimatedPriceAplicationDate = estimatedPriceAplicationDate;
    }

    public String getEstimatedPriceEffectiveDate() {
        return estimatedPriceEffectiveDate;
    }

    public void setEstimatedPriceEffectiveDate(String estimatedPriceEffectiveDate) {
        this.estimatedPriceEffectiveDate = estimatedPriceEffectiveDate;
    }

    public String getNico() {
        return nico;
    }

    public void setNico(String nico) {
        this.nico = nico;
    }
}
