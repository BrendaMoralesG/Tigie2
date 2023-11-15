package com.example.sistemascasa.tigie.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrolloweb on 20/09/16.
 */
public class AndroidUsers {

    @SerializedName("idUsuario")
    @Expose
    private int idUsuario;

    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;

    @SerializedName("emailUsuario")
    @Expose
    private String emailUsuario;

    @SerializedName("empresaUsuario")
    @Expose
    private String empresaUsuario;

    @SerializedName("duracionUsuario")
    @Expose
    private String duracionUsuario;

    @SerializedName("fecregUsuario")
    @Expose
    private String fecregUsuario;

    @SerializedName("password_usuario")
    @Expose
    private String passwordUsuario;

    @SerializedName("statusUsuario")
    @Expose
    private Boolean statusUsuario;


    public AndroidUsers() {

    }

    public AndroidUsers(int idUsuario, String nombreUsuario, String emailUsuario, String empresaUsuario, String duracionUsuario, String fecregUsuario, String passwordUsuario, Boolean statusUsuario) {
        this.idUsuario       = idUsuario;
        this.nombreUsuario   = nombreUsuario;
        this.emailUsuario    = emailUsuario;
        this.empresaUsuario  = empresaUsuario;
        this.duracionUsuario = duracionUsuario;
        this.fecregUsuario   = fecregUsuario;
        this.passwordUsuario = passwordUsuario;
        this.statusUsuario   = statusUsuario;
    }

    public int getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_usuario() {
        return nombreUsuario;
    }

    public void setNombre_usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail_usuario() {
        return emailUsuario;
    }

    public void setEmail_usuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getEmpresa_usuario() {
        return empresaUsuario;
    }

    public void setEmpresa_usuario(String empresaUsuario) {
        this.empresaUsuario = empresaUsuario;
    }

    public String getDuracion_usuario() {
        return duracionUsuario;
    }

    public void setDuracion_usuario(String duracionUsuario) {
        this.duracionUsuario = duracionUsuario;
    }

    public String getFecreg_usuario() {
        return fecregUsuario;
    }

    public void setFecreg_usuario(String fecregUsuario) {
        this.fecregUsuario = fecregUsuario;
    }

    public String getPassword_usuario() {
        return passwordUsuario;
    }

    public void setPassword_usuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public Boolean getStatus_usuario() {
        return statusUsuario;
    }

    public void setStatus_usuario(Boolean statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
