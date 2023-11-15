package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;

/**
 * Created by desarrolloweb on 17/02/17.
 */
public class Pdf {

    @Expose
    private Boolean success;
    @Expose
    private String fraction;
    @Expose
    private String urlPdf;


    public Pdf ( Boolean success, String fraction, String urlPdf) {
        this.success = success;
        this.fraction = fraction;
        this.urlPdf   = urlPdf;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
