package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 8/02/17.
 */
public class ModulesData {

    @SerializedName("module")
    @Expose
    private String 	module;

    @SerializedName("numberElement")
    @Expose
    private int 	numberElement;

    @SerializedName("title")
    @Expose
    private String  title;

    @SerializedName("fraction")
    @Expose
    private String fraction;

    private Integer id_fraccion;

    private int image;

    public ModulesData() {

    }

    public ModulesData (String 	module, int numberElement, String  title, int image, String fraction, Integer id_fraccion) {
        this.module = module;
        this.numberElement = numberElement;
        this.title  = title;
        this.image = image;
        this.fraction = fraction;
        this.id_fraccion = id_fraccion;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getNumberElement() {
        return numberElement;
    }

    public void setNumberElement(int numberElement) {
        this.numberElement = numberElement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public Integer getId_fraccion() {
        return id_fraccion;
    }

    public void setId_fraccion(Integer id_fraccion) {
        this.id_fraccion = id_fraccion;
    }
}
