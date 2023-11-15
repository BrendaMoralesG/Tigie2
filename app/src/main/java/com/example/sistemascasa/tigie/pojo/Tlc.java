package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 10/08/16.
 */
public class Tlc {
    @SerializedName("idTlcCountry")
    @Expose
    private int idTlcCountry;
    @SerializedName("idTariffFraction")
    @Expose
    private int idTariffFraction;
    @SerializedName("tlcCountry")
    @Expose
    private String tlcCountry;
    @SerializedName("tlcDof")
    @Expose
    private String tlcDof;
    @SerializedName("tlcAdval")
    @Expose
    private String tlcAdval;
    @SerializedName("tlcFlag")
    @Expose
    private int tlcFlag;
    @SerializedName("tlcCode")
    @Expose
    private String tlcCode;


    public Tlc () {

    }

    public Tlc (int idTlcCountry, int idTariffFraction, String tlcCountry, String tlcDof, String tlcAdval, int tlcFlag, String tlcCode) {
        this.idTlcCountry       = idTlcCountry;
        this.idTariffFraction   = idTariffFraction;
        this.tlcCountry         = tlcCountry;
        this.tlcDof             = tlcDof;
        this.tlcAdval           = tlcAdval;
        this.tlcFlag            = tlcFlag;
        this.tlcCode            = tlcCode;
    }


    public int getIdTlcCountry() {
        return idTlcCountry;
    }

    public void setIdTlcCountry(int idTlcCountry) {
        this.idTlcCountry = idTlcCountry;
    }

    public String getTlcCode() {
        return tlcCode;
    }

    public void setTlcCode(String tlcCode) {
        this.tlcCode = tlcCode;
    }

    public int getTlcFlag() {
        return tlcFlag;
    }

    public void setTlcFlag(int tlcFlag) {
        this.tlcFlag = tlcFlag;
    }

    public String getTlcAdval() {
        return tlcAdval;
    }

    public void setTlcAdval(String tlcAdval) {
        this.tlcAdval = tlcAdval;
    }

    public String getTlcDof() {
        return tlcDof;
    }

    public void setTlcDof(String tlcDof) {
        this.tlcDof = tlcDof;
    }

    public String getTlcCountry() {
        return tlcCountry;
    }

    public void setTlcCountry(String tlcCountry) {
        this.tlcCountry = tlcCountry;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    @Override
    public String toString() {
        return "Tlc{" +
                "idTlcCountry=" + idTlcCountry +
                ", idTariffFraction=" + idTariffFraction +
                ", tlcCountry='" + tlcCountry + '\'' +
                ", tlcDof='" + tlcDof + '\'' +
                ", tlcAdval='" + tlcAdval + '\'' +
                ", tlcFlag='" + tlcFlag + '\'' +
                ", tlcCode='" + tlcCode + '\'' +
                '}';
    }
}
