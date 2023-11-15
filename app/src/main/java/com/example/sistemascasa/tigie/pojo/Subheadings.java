package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 20/07/16.
 */
public class Subheadings {

    @SerializedName("idTariffSubheading")
    @Expose
    private int idTariffSubheading;

    @SerializedName("idTariffPreSubheading")
    @Expose
    private int idTariffPreSubheading;

    @SerializedName("idTariffHeading")
    @Expose
    private int idTariffHeading;

    @SerializedName("tariffSubheadingCode")
    @Expose
    private String tariffSubheadingCode;

    @SerializedName("tariffSubheadingDescription")
    @Expose
    private String tariffSubheadingDescription;

    @SerializedName("tariffHheadingCode")
    @Expose
    private String tariffHheadingCode;

    @SerializedName("tariffHeadingDescription")
    @Expose
    private String tariffHeadingDescription;

    private int tariffSubheadingIcon;

    public  Subheadings () {

    }

    public  Subheadings (int idTariffSubheading, String tariffSubheadingCode, String tariffSubheadingDescription, String tariffHheadingCode, String tariffHeadingDescription) {
        this.idTariffSubheading     = idTariffSubheading;
        this.tariffSubheadingCode   = tariffSubheadingCode;
        this.tariffSubheadingDescription = tariffSubheadingDescription;
        this.tariffHheadingCode = tariffHheadingCode;
        this.tariffHeadingDescription = tariffHeadingDescription;
    }

    public  Subheadings (int idTariffSubheading, String tariffSubheadingCode, String tariffSubheadingDescription) {
        this.idTariffSubheading     = idTariffSubheading;
        this.tariffSubheadingCode   = tariffSubheadingCode;
        this.tariffSubheadingDescription = tariffSubheadingDescription;
    }

    public  Subheadings (int idTariffSubheading, int idTariffPreSubheading, int idTariffHeading, String tariffSubheadingCode, String tariffSubheadingDescription, int tariffSubheadingIcon) {
        this.idTariffSubheading     = idTariffSubheading;
        this.idTariffPreSubheading  = idTariffPreSubheading;
        this.idTariffHeading        = idTariffHeading;
        this.tariffSubheadingCode   = tariffSubheadingCode;
        this.tariffSubheadingDescription = tariffSubheadingDescription;
        this.tariffSubheadingIcon   = tariffSubheadingIcon;
    }

    public int getTariffSubheadingIcon() {
        return tariffSubheadingIcon;
    }

    public void setTariffSubheadingIcon(int tariffSubheadingIcon) {
        this.tariffSubheadingIcon = tariffSubheadingIcon;
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

    public int getIdTariffHeading() {
        return idTariffHeading;
    }

    public void setIdTariffHeading(int idTariffHeading) {
        this.idTariffHeading = idTariffHeading;
    }

    public int getIdTariffPreSubheading() {
        return idTariffPreSubheading;
    }

    public void setIdTariffPreSubheading(int idTariffPreSubheading) {
        this.idTariffPreSubheading = idTariffPreSubheading;
    }

    public int getIdTariffSubheading() {
        return idTariffSubheading;
    }

    public void setIdTariffSubheading(int idTariffSubheading) {
        this.idTariffSubheading = idTariffSubheading;
    }

    public String getTariffHheadingCode() {
        return tariffHheadingCode;
    }

    public void setTariffHheadingCode(String tariffHheadingCode) {
        this.tariffHheadingCode = tariffHheadingCode;
    }

    public String getTariffHeadingDescription() {
        return tariffHeadingDescription;
    }

    public void setTariffHeadingDescription(String tariffHeadingDescription) {
        this.tariffHeadingDescription = tariffHeadingDescription;
    }
}


