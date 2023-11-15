package com.example.sistemascasa.tigie;

/**
 * Created by desarrolloweb on 23/08/16.
 */
public class Comunes {

    public static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
