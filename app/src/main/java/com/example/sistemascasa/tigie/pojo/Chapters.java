package com.example.sistemascasa.tigie.pojo;

/**
 * Created by desarrolloweb on 12/07/16.
 */
public class Chapters {

    private int id;
    private String codigo;
    private String descripcion;
    private int icono;

    public Chapters() {

    }

    public int getId() {
        return id;
    }

    public void setId(int foto) {
        this.id = foto;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Chapters (int id, String codigo, String descripcion, int icono) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.icono = icono;
    }

}
