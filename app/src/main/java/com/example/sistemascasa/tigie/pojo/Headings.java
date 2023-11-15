package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 16/07/16.
 */
public class Headings {

    @SerializedName("idTariffHeading")
    @Expose
    private int idTariffHeading;

    @SerializedName("idTariffChapter")
    @Expose
    private int idTariffChapter;

    @SerializedName("tariffHeadingCode")
    @Expose
    private String tariffHeadingCode;

    @SerializedName("tariffHeadingDescription")
    @Expose
    private String tariffHeadingDescription;

    @SerializedName("cap")
    @Expose
    private String cap;

    @SerializedName("capDes")
    @Expose
    private String capDes;

    private int tariffHeadingIcon;

    private int idChapterTmp;

    public Headings () {

    }

    public Headings (int idTariffHeading, String tariffHeadingCode, String tariffHeadingDescription) {
        this.idTariffHeading            = idTariffHeading;
        this.tariffHeadingCode          = tariffHeadingCode;
        this.tariffHeadingDescription   = tariffHeadingDescription;
    }

    public Headings (int idTariffHeading, int idTariffChapter, String tariffHeadingCode, String tariffHeadingDescription, int tariffHeadingIcon) {
        this.idTariffHeading            = idTariffHeading;
        this.idTariffChapter            = idTariffChapter;
        this.tariffHeadingCode          = tariffHeadingCode;
        this.tariffHeadingDescription   = tariffHeadingDescription;
        this.tariffHeadingIcon          = tariffHeadingIcon;
    }

    public Headings (Integer idTariffHeading, String tariffHeadingCode, String tariffHeadingDescription, String cap, String capDes) {
        this.idTariffHeading = idTariffHeading;
        this.tariffHeadingCode= tariffHeadingCode;
        this.tariffHeadingDescription = tariffHeadingDescription;
        this.cap= cap;
        this.capDes = capDes;
    }

    public int getIdTariffHeading() {
        return idTariffHeading;
    }

    public void setIdTariffHeading(int idTariffHeading) {
        this.idTariffHeading = idTariffHeading;
    }

    public int getIdTariffChapter() {
        return idTariffChapter;
    }

    public void setIdTariffChapter(int idTariffChapter) {
        this.idTariffChapter = idTariffChapter;
    }

    public String getTariffHeadingCode() {
        return tariffHeadingCode;
    }

    public void setTariffHeadingCode(String tariffHeadingCode) {
        this.tariffHeadingCode = tariffHeadingCode;
    }

    public String getTariffHeadingDescription() {
        return tariffHeadingDescription;
    }

    public void setTariffHeadingDescription(String tariffHeadingDescription) {
        this.tariffHeadingDescription = tariffHeadingDescription;
    }

    public int getTariffHeadingIcon() {
        return tariffHeadingIcon;
    }

    public void setTariffHeadingIcon(int tariffHeadingIcon) {
        this.tariffHeadingIcon = tariffHeadingIcon;
    }

    public int getIdChapterTmp() {
        return idChapterTmp;
    }

    public void setIdChapterTmp(int idChapterTmp) {
        this.idChapterTmp = idChapterTmp;
    }


    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCapDes() {
        return capDes;
    }

    public void setCapDes(String capDes) {
        this.capDes = capDes;
    }
}
