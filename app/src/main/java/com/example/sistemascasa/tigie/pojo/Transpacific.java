package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transpacific {

    @SerializedName("idTipTranspacificACountry")
    @Expose
    private int		idTipTranspacificACountry;

    @SerializedName("idTariffFraction")
    @Expose
    private int 	idTariffFraction;

    @SerializedName("tipTranspacificAFreeCountryName")
    @Expose
    private String 	tipTranspacificAFreeCountryName;

    @SerializedName("tipTranspacificADofDate")
    @Expose
    private String 	tipTranspacificADofDate;

    @SerializedName("transpacificAAdvalCountryAmount")
    @Expose
    private String 	transpacificAAdvalCountryAmount;

    @SerializedName("tipTranspacificAFreeCountryCode")
    @Expose
    private String  tipTranspacificAFreeCountryCode;

    private int flagIcon;

    public Transpacific () {

    }

    public Transpacific (int idTipTranspacificACountry, int idTariffFraction, String tipTranspacificAFreeCountryName, String tipTranspacificADofDate, String transpacificAAdvalCountryAmount, String  tipTranspacificAFreeCountryCode) {
        this.idTipTranspacificACountry= idTipTranspacificACountry;
        this.idTariffFraction= idTariffFraction;
        this.tipTranspacificAFreeCountryName = tipTranspacificAFreeCountryName;
        this.tipTranspacificADofDate = tipTranspacificADofDate;
        this.transpacificAAdvalCountryAmount = transpacificAAdvalCountryAmount;
        this.tipTranspacificAFreeCountryCode = tipTranspacificAFreeCountryCode;
    }

    public Transpacific (int idTipTranspacificACountry, int idTariffFraction, String tipTranspacificAFreeCountryName, String tipTranspacificADofDate, String transpacificAAdvalCountryAmount, String  tipTranspacificAFreeCountryCode, int flagIcon) {
        this.idTipTranspacificACountry= idTipTranspacificACountry;
        this.idTariffFraction= idTariffFraction;
        this.tipTranspacificAFreeCountryName = tipTranspacificAFreeCountryName;
        this.tipTranspacificADofDate = tipTranspacificADofDate;
        this.transpacificAAdvalCountryAmount = transpacificAAdvalCountryAmount;
        this.tipTranspacificAFreeCountryCode = tipTranspacificAFreeCountryCode;
        this.flagIcon               =   flagIcon;
    }

    public int getIdTipTranspacificACountry() {
        return idTipTranspacificACountry;
    }

    public void setIdTipTranspacificACountry(int idTipTranspacificACountry) {
        this.idTipTranspacificACountry = idTipTranspacificACountry;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getTipTranspacificAFreeCountryName() {
        return tipTranspacificAFreeCountryName;
    }

    public void setTipTranspacificAFreeCountryName(String tipTranspacificAFreeCountryName) {
        this.tipTranspacificAFreeCountryName = tipTranspacificAFreeCountryName;
    }

    public String getTipTranspacificADofDate() {
        return tipTranspacificADofDate;
    }

    public void setTipTranspacificADofDate(String tipTranspacificADofDate) {
        this.tipTranspacificADofDate = tipTranspacificADofDate;
    }

    public String getTranspacificAAdvalCountryAmount() {
        return transpacificAAdvalCountryAmount;
    }

    public void setTranspacificAAdvalCountryAmount(String transpacificAAdvalCountryAmount) {
        this.transpacificAAdvalCountryAmount = transpacificAAdvalCountryAmount;
    }

    public String getTipTranspacificAFreeCountryCode() {
        return tipTranspacificAFreeCountryCode;
    }

    public void setTipTranspacificAFreeCountryCode(String tipTranspacificAFreeCountryCode) {
        this.tipTranspacificAFreeCountryCode = tipTranspacificAFreeCountryCode;
    }

    public int getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(int flagIcon) {
        this.flagIcon = flagIcon;
    }
}
