package com.example.sistemascasa.tigie.settings;

/**
 * Created by desarrolloweb on 24/08/16.
 */
public interface Comunicador {

    public void responder(String codigo);

    public String getEmail ();

    public String getToken ();

    public Integer getIdFraccion();

}
