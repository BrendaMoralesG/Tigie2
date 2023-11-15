package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/01/17.
 */
public class CompensatoryShares {
    @SerializedName("idCompensatoryShare")
    @Expose
    private int idCompensatoryShare;

    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;

    @SerializedName("compensatoryShareFlag")
    @Expose
    private String compensatoryShareFlag;

    @SerializedName("compensatoryShareCountry")
    @Expose
    private String compensatoryShareCountry;

    @SerializedName("compensatoryShareTasa")
    @Expose
    private String compensatoryShareTasa;

    @SerializedName("compensatoryShareTypeDescription")
    @Expose
    private String compensatoryShareTypeDescription;

    @SerializedName("compensatoryShareMerchandiseDescription")
    @Expose
    private String compensatoryShareMerchandiseDescription;

    @SerializedName("compensatorySharePublicationDate")
    @Expose
    private String compensatorySharePublicationDate;

    @SerializedName("compensatoryShareAplicationDate")
    @Expose
    private String compensatoryShareAplicationDate;

    @SerializedName("compensatoryShareEffectiveDate")
    @Expose
    private String compensatoryShareEffectiveDate;

    @SerializedName("compensatoryFlag")
    @Expose
    private int compensatoryFlag;

    public CompensatoryShares () {

    }

    public CompensatoryShares (int idCompensatoryShare, int idTariffFraction, String compensatoryShareFlag, String compensatoryShareCountry,
                               String compensatoryShareTasa, String compensatoryShareTypeDescription, String compensatoryShareMerchandiseDescription,
                               String compensatorySharePublicationDate, String compensatoryShareAplicationDate,
                               String compensatoryShareEffectiveDate, int compensatoryFlag) {
        this.idCompensatoryShare = idCompensatoryShare;
        this.idTariffFraction = idTariffFraction;
        this.compensatoryShareFlag= compensatoryShareFlag;
        this.compensatoryShareCountry= compensatoryShareCountry;
        this.compensatoryShareTasa = compensatoryShareTasa;
        this.compensatoryShareTypeDescription = compensatoryShareTypeDescription;
        this.compensatoryShareMerchandiseDescription = compensatoryShareMerchandiseDescription;
        this.compensatorySharePublicationDate = compensatorySharePublicationDate;
        this.compensatoryShareAplicationDate = compensatoryShareAplicationDate;
        this.compensatoryShareEffectiveDate = compensatoryShareEffectiveDate;
        this.compensatoryFlag = compensatoryFlag;
    }

    public int getCompensatoryFlag() {
        return compensatoryFlag;
    }

    public void setCompensatoryFlag(int compensatoryFlag) {
        this.compensatoryFlag = compensatoryFlag;
    }

    public int getIdCompensatoryShare() {
        return idCompensatoryShare;
    }

    public void setIdCompensatoryShare(int idCompensatoryShare) {
        this.idCompensatoryShare = idCompensatoryShare;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getCompensatoryShareFlag() {
        return compensatoryShareFlag;
    }

    public void setCompensatoryShareFlag(String compensatoryShareFlag) {
        this.compensatoryShareFlag = compensatoryShareFlag;
    }

    public String getCompensatoryShareCountry() {
        return compensatoryShareCountry;
    }

    public void setCompensatoryShareCountry(String compensatoryShareCountry) {
        this.compensatoryShareCountry = compensatoryShareCountry;
    }

    public String getCompensatoryShareTasa() {
        return compensatoryShareTasa;
    }

    public void setCompensatoryShareTasa(String compensatoryShareTasa) {
        this.compensatoryShareTasa = compensatoryShareTasa;
    }

    public String getCompensatoryShareTypeDescription() {
        return compensatoryShareTypeDescription;
    }

    public void setCompensatoryShareTypeDescription(String compensatoryShareTypeDescription) {
        this.compensatoryShareTypeDescription = compensatoryShareTypeDescription;
    }

    public String getCompensatoryShareMerchandiseDescription() {
        return compensatoryShareMerchandiseDescription;
    }

    public void setCompensatoryShareMerchandiseDescription(String compensatoryShareMerchandiseDescription) {
        this.compensatoryShareMerchandiseDescription = compensatoryShareMerchandiseDescription;
    }

    public String getCompensatorySharePublicationDate() {
        return compensatorySharePublicationDate;
    }

    public void setCompensatorySharePublicationDate(String compensatorySharePublicationDate) {
        this.compensatorySharePublicationDate = compensatorySharePublicationDate;
    }

    public String getCompensatoryShareAplicationDate() {
        return compensatoryShareAplicationDate;
    }

    public void setCompensatoryShareAplicationDate(String compensatoryShareAplicationDate) {
        this.compensatoryShareAplicationDate = compensatoryShareAplicationDate;
    }

    public String getCompensatoryShareEffectiveDate() {
        return compensatoryShareEffectiveDate;
    }

    public void setCompensatoryShareEffectiveDate(String compensatoryShareEffectiveDate) {
        this.compensatoryShareEffectiveDate = compensatoryShareEffectiveDate;
    }

}
