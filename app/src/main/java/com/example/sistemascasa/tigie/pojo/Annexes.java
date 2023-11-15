package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class Annexes {
    @SerializedName("idAnnexFraction")
    @Expose
    private int idAnnexFraction;

    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("annexDescription")
    @Expose
    private String annexDescription;

    @SerializedName("annexNoteDescription")
    @Expose
    private String annexNoteDescription;

    @SerializedName("annexPublicationDate")
    @Expose
    private String annexPublicationDate;

    @SerializedName("annexApplicationDate")
    @Expose
    private String annexApplicationDate;

    @SerializedName("annexEffectiveDate")
    @Expose
    private String annexEffectiveDate;

    public Annexes () { }

    public Annexes (int idAnnexFraction, int idTariffFraction, String title, String annexDescription, String annexNoteDescription, String annexPublicationDate, String annexApplicationDate, String annexEffectiveDate) {
        this.idAnnexFraction 		= idAnnexFraction;
        this.idTariffFraction 		= idTariffFraction;
        this.title 					= title;
        this.annexDescription		= annexDescription;
        this.annexNoteDescription	= annexNoteDescription;
        this.annexPublicationDate 	= annexPublicationDate;
        this.annexApplicationDate   = annexApplicationDate;
        this.annexEffectiveDate     = annexEffectiveDate;

    }


    public int getIdAnnexFraction() {
        return idAnnexFraction;
    }


    public void setIdAnnexFraction(int idAnnexFraction) {
        this.idAnnexFraction = idAnnexFraction;
    }


    public int getIdTariffFraction() {
        return idTariffFraction;
    }


    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAnnexDescription() {
        return annexDescription;
    }


    public void setAnnexDescription(String annexDescription) {
        this.annexDescription = annexDescription;
    }


    public String getAnnexNoteDescription() {
        return annexNoteDescription;
    }


    public void setAnnexNoteDescription(String annexNoteDescription) {
        this.annexNoteDescription = annexNoteDescription;
    }


    public String getAnnexPublicationDate() {
        return annexPublicationDate;
    }


    public void setAnnexPublicationDate(String annexPublicationDate) {
        this.annexPublicationDate = annexPublicationDate;
    }


    public String getAnnexApplicationDate() {
        return annexApplicationDate;
    }


    public void setAnnexApplicationDate(String annexApplicationDate) {
        this.annexApplicationDate = annexApplicationDate;
    }


    public String getAnnexEffectiveDate() {
        return annexEffectiveDate;
    }


    public void setAnnexEffectiveDate(String annexEffectiveDate) {
        this.annexEffectiveDate = annexEffectiveDate;
    }

}
