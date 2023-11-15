package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 20/07/16.
 */
public class Fractions {

    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;
    private int idTariffChapter;
    private int idTariffHeading;
    private int idTariffSubheading;

    @SerializedName("tariffFractionCode")
    @Expose
    private String tariffFractionCode;

    @SerializedName("tariffFractionDescription")
    @Expose
    private String tariffFractionDescription;

    private int tariffFractionIcon;
    private String tariffSubheadingCode;
    private String tariffSubheadingDescription;
    private String tariffHeadingCode;
    private String tariffHeadingDescription;
    private String tariffChapterCode;
    private String tariffChapterDescription;

    @SerializedName("tariffSubHeadCode")
    @Expose
    private String tariffSubHeadCode;

    @SerializedName("tariffSubHeadDescription")
    @Expose
    private String tariffSubHeadDescription;


    @SerializedName("nico")
    @Expose
    private String nico;

    public String getTariffSubheadingCode() {
        return tariffSubheadingCode;
    }

    public void setTariffSubheadingCode(String tariffSubheadingCode) {
        this.tariffSubheadingCode = tariffSubheadingCode;
    }

    public String getTariffSubheadingDescription() {
        return tariffSubheadingDescription;
    }

    public void setTariffSubheadingDescription(String tariffSubheadingDescription) {
        this.tariffSubheadingDescription = tariffSubheadingDescription;
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

    public String getTariffChapterCode() {
        return tariffChapterCode;
    }

    public void setTariffChapterCode(String tariffChapterCode) {
        this.tariffChapterCode = tariffChapterCode;
    }

    public String getTariffHeadingDescription() {
        return tariffHeadingDescription;
    }

    public void setTariffHeadingDescription(String tariffHeadingDescription) {
        this.tariffHeadingDescription = tariffHeadingDescription;
    }

    public Fractions () {

    }

    public Fractions (int idTariffFraction, int idTariffChapter, int idTariffHeading, int idTariffSubheading, String tariffFractionCode, String tariffFractionDescription, int tariffFractionIcon) {
        this.idTariffFraction = idTariffFraction;
        this.idTariffChapter = idTariffChapter;
        this.idTariffHeading = idTariffHeading;
        this.idTariffSubheading = idTariffSubheading;
        this.tariffFractionCode = tariffFractionCode;
        this.tariffFractionDescription = tariffFractionDescription;
        this.tariffFractionIcon= tariffFractionIcon;
    }

    public Fractions (Integer idTariffFraction, String tariffFractionCode, String tariffFractionDescription, String tariffSubHeadCode, String tariffSubHeadDescription) {
        this.idTariffFraction = idTariffFraction;
        this.tariffFractionCode = tariffFractionCode;
        this.tariffFractionDescription = tariffFractionDescription;
        this.tariffSubHeadCode = tariffSubHeadCode;
        this.tariffSubHeadDescription = tariffSubHeadDescription;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public int getTariffFractionIcon() {
        return tariffFractionIcon;
    }

    public void setTariffFractionIcon(int tariffFractionIcon) {
        this.tariffFractionIcon = tariffFractionIcon;
    }

    public String getTariffFractionDescription() {
        return tariffFractionDescription;
    }

    public void setTariffFractionDescription(String tariffFractionDescription) {
        this.tariffFractionDescription = tariffFractionDescription;
    }

    public String getTariffFractionCode() {
        return tariffFractionCode;
    }

    public void setTariffFractionCode(String tariffFractionCode) {
        this.tariffFractionCode = tariffFractionCode;
    }

    public int getIdTariffSubheading() {
        return idTariffSubheading;
    }

    public void setIdTariffSubheading(int idTariffSubheading) {
        this.idTariffSubheading = idTariffSubheading;
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

    public String getTariffSubHeadCode() {
        return tariffSubHeadCode;
    }

    public void setTariffSubHeadCode(String tariffSubHeadCode) {
        this.tariffSubHeadCode = tariffSubHeadCode;
    }

    public String getTariffSubHeadDescription() {
        return tariffSubHeadDescription;
    }

    public void setTariffSubHeadDescription(String tariffSubHeadDescription) {
        this.tariffSubHeadDescription = tariffSubHeadDescription;
    }

    public String getNico() {
        return nico;
    }

    public void setNico(String nico) {
        this.nico = nico;
    }
}
