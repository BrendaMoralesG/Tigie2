package com.example.sistemascasa.tigie.pojo;

/**
 * Created by desarrolloweb on 20/08/16.
 */
public class AlliancePacific {

    private int 	idTlcPaificAlliance;
    private int 	idTariffFraction;
    private String 	tlcPacificAllianceCountryName;
    private String 	tlcPacificAllianceAdvalDof;
    private String 	pacificAllianceAdvalCountry;
    private String  tlcPacificAllianceFreeCountryCode;
    private int     tlcPacificAllianceFlag;

    public AlliancePacific () {

    }

    public AlliancePacific (int idTlcPaificAlliance, int idTariffFraction, String tlcPacificAllianceCountryName, String pacificAllianceAdvalCountry, String  tlcPacificAllianceFreeCountryCode, String tlcPacificAllianceAdvalDof, int tlcPacificAllianceFlag) {
        this.idTlcPaificAlliance 				= idTlcPaificAlliance;
        this.idTariffFraction 					= idTariffFraction;
        this.tlcPacificAllianceCountryName		= tlcPacificAllianceCountryName;
        this.pacificAllianceAdvalCountry 		= pacificAllianceAdvalCountry;
        this.tlcPacificAllianceFreeCountryCode 	= tlcPacificAllianceFreeCountryCode;
        this.tlcPacificAllianceAdvalDof			= tlcPacificAllianceAdvalDof;
        this.tlcPacificAllianceFlag             = tlcPacificAllianceFlag;
    }


    public int getTlcPacificAllianceFlag() {
        return tlcPacificAllianceFlag;
    }

    public void setTlcPacificAllianceFlag(int tlcPacificAllianceFlag) {
        this.tlcPacificAllianceFlag = tlcPacificAllianceFlag;
    }

    public int getIdTlcPaificAlliance() {
        return idTlcPaificAlliance;
    }

    public void setIdTlcPaificAlliance(int idTlcPaificAlliance) {
        this.idTlcPaificAlliance = idTlcPaificAlliance;
    }

    public int getIdTariffFraction() {
        return idTariffFraction;
    }

    public void setIdTariffFraction(int idTariffFraction) {
        this.idTariffFraction = idTariffFraction;
    }

    public String getTlcPacificAllianceCountryName() {
        return tlcPacificAllianceCountryName;
    }

    public void setTlcPacificAllianceCountryName(String tlcPacificAllianceCountryName) {
        this.tlcPacificAllianceCountryName = tlcPacificAllianceCountryName;
    }

    public String getTlcPacificAllianceAdvalDof() {
        return tlcPacificAllianceAdvalDof;
    }

    public void setTlcPacificAllianceAdvalDof(String tlcPacificAllianceAdvalDof) {
        this.tlcPacificAllianceAdvalDof = tlcPacificAllianceAdvalDof;
    }

    public String getPacificAllianceAdvalCountry() {
        return pacificAllianceAdvalCountry;
    }

    public void setPacificAllianceAdvalCountry(String pacificAllianceAdvalCountry) {
        this.pacificAllianceAdvalCountry = pacificAllianceAdvalCountry;
    }

    public String getTlcPacificAllianceFreeCountryCode() {
        return tlcPacificAllianceFreeCountryCode;
    }

    public void setTlcPacificAllianceFreeCountryCode(String tlcPacificAllianceFreeCountryCode) {
        this.tlcPacificAllianceFreeCountryCode = tlcPacificAllianceFreeCountryCode;
    }
}
