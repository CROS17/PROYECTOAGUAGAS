package com.example.usuario.mitiendav1.backend.model;

public class Categoria {
    private int idCategoria;
    private String nombre,descripcion;

    public int getIdcategoria(){return idCategoria;}

    public void setIdcategoria(int idCategoria){this.idCategoria = idCategoria;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){this.nombre = nombre;}

    public String getDescripcion(){return descripcion;}

    public void setDescripcion(String descripcion){this.descripcion = descripcion;}

    public void add(Categoria tipos) {
    }
}
