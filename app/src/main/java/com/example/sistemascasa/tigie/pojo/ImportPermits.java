package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class ImportPermits {
    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("importPermitTitle")
    @Expose
    private String importPermitTitle;

    @SerializedName("importPermitDescription")
    @Expose
    private String importPermitDescription;

    @SerializedName("importPermitNote")
    @Expose
    private String importPermitNote;

    @SerializedName("importPermitPublicationDate")
    @Expose
    private String importPermitPublicationDate;

    @SerializedName("importPermitAplicationDate")
    @Expose
    private String importPermitAplicationDate;

    @SerializedName("importPermitEffectiveDate")
    @Expose
    private String importPermitEffectiveDate;

    @SerializedName("legislation")
    @Expose
    private String legislation;

    @SerializedName("arcticle")
    @Expose
    private String arcticle;

    @SerializedName("arcticleDesc")
    @Expose
    private String arcticleDesc;


    public ImportPermits (int idTariffFraction, String importPermitTitle, String importPermitDescription, String importPermitNote, String importPermitPublicationDate, String importPermitAplicationDate, String importPermitEffectiveDate,
                          String legislation, String arcticle, String arcticleDesc) {
        this.idTariffFraction 				= idTariffFraction;
        this.importPermitTitle 				= importPermitTitle;
        this.importPermitDescription		= importPermitDescription;
        this.importPermitNote 				= importPermitNote;
        this.importPermitPublicationDate 	= importPermitPublicationDate;
        this.importPermitAplicationDate 	= importPermitAplicationDate;
        this.importPermitEffectiveDate 		= importPermitEffectiveDate;
        this.legislation					= legislation;
        this.arcticle						= arcticle;
        this.arcticleDesc 					= arcticleDesc;
    }


    public int getIdTariffFraction() {
        return idTariffFraction;
    }


    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }


    public String getImportPermitTitle() {
        return importPermitTitle;
    }


    public void setImportPermitTitle(String importPermitTitle) {
        this.importPermitTitle = importPermitTitle;
    }


    public String getImportPermitDescription() {
        return importPermitDescription;
    }


    public void setImportPermitDescription(String importPermitDescription) {
        this.importPermitDescription = importPermitDescription;
    }


    public String getImportPermitNote() {
        return importPermitNote;
    }


    public void setImportPermitNote(String importPermitNote) {
        this.importPermitNote = importPermitNote;
    }


    public String getImportPermitPublicationDate() {
        return importPermitPublicationDate;
    }


    public void setImportPermitPublicationDate(String importPermitPublicationDate) {
        this.importPermitPublicationDate = importPermitPublicationDate;
    }


    public String getImportPermitAplicationDate() {
        return importPermitAplicationDate;
    }


    public void setImportPermitAplicationDate(String importPermitAplicationDate) {
        this.importPermitAplicationDate = importPermitAplicationDate;
    }


    public String getImportPermitEffectiveDate() {
        return importPermitEffectiveDate;
    }


    public void setImportPermitEffectiveDate(String importPermitEffectiveDate) {
        this.importPermitEffectiveDate = importPermitEffectiveDate;
    }


    public String getLegislation() {
        return legislation;
    }


    public void setLegislation(String legislation) {
        this.legislation = legislation;
    }


    public String getArcticle() {
        return arcticle;
    }


    public void setArcticle(String arcticle) {
        this.arcticle = arcticle;
    }


    public String getArcticleDesc() {
        return arcticleDesc;
    }


    public void setArcticleDesc(String arcticleDesc) {
        this.arcticleDesc = arcticleDesc;
    }
}
