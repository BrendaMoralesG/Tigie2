package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class TlcNotes {

    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("tlcNoteFreeCountryCode")
    @Expose
    private String tlcNoteFreeCountryCode 		= "";

    @SerializedName("tlcNoteTitle")
    @Expose
    private String tlcNoteTitle 				= "";

    @SerializedName("tlcNoteDescription")
    @Expose
    private String tlcNoteDescription 			= "";

    @SerializedName("tlcNoteAplicationDate")
    @Expose
    private String tlcNoteAplicationDate		= "";

    @SerializedName("tlcNoteEfectiveDate")
    @Expose
    private String tlcNoteEfectiveDate			= "";

    @SerializedName("ivFlagTlcNote")
    @Expose
    private int ivFlagTlcNote;

    public TlcNotes () { }

    public TlcNotes (int idTariffFraction, String tlcNoteFreeCountryCode, String tlcNoteTitle, String tlcNoteDescription, String tlcNoteAplicationDate, String tlcNoteEfectiveDate, int ivFlagTlcNote) {
        this.idTariffFraction 			= idTariffFraction;
        this.tlcNoteFreeCountryCode 	= tlcNoteFreeCountryCode;
        this.tlcNoteTitle 				= tlcNoteTitle;
        this.tlcNoteDescription		    = tlcNoteDescription;
        this.tlcNoteAplicationDate 	    = tlcNoteAplicationDate;
        this.tlcNoteEfectiveDate		= tlcNoteEfectiveDate;
        this.ivFlagTlcNote              = ivFlagTlcNote;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public int getIvFlagTlcNote() {
        return ivFlagTlcNote;
    }

    public void setIvFlagTlcNote(int ivFlagTlcNote) {
        this.ivFlagTlcNote = ivFlagTlcNote;
    }

    public String getTlcNoteFreeCountryCode() {
        return tlcNoteFreeCountryCode;
    }

    public void setTlcNoteFreeCountryCode(String tlcNoteFreeCountryCode) {
        this.tlcNoteFreeCountryCode = tlcNoteFreeCountryCode;
    }

    public String getTlcNoteTitle() {
        return tlcNoteTitle;
    }

    public void setTlcNoteTitle(String tlcNoteTitle) {
        this.tlcNoteTitle = tlcNoteTitle;
    }

    public String getTlcNoteDescription() {
        return tlcNoteDescription;
    }

    public void setTlcNoteDescription(String tlcNoteDescription) {
        this.tlcNoteDescription = tlcNoteDescription;
    }

    public String getTlcNoteAplicationDate() {
        return tlcNoteAplicationDate;
    }

    public void setTlcNoteAplicationDate(String tlcNoteAplicationDate) {
        this.tlcNoteAplicationDate = tlcNoteAplicationDate;
    }

    public String getTlcNoteEfectiveDate() {
        return tlcNoteEfectiveDate;
    }

    public void setTlcNoteEfectiveDate(String tlcNoteEfectiveDate) {
        this.tlcNoteEfectiveDate = tlcNoteEfectiveDate;
    }

}
