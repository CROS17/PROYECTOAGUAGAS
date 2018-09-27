package com.example.usuario.mitiendav1.backend.model;

public class TipoTrabajador {
    private int idTipoTrabajador;
    private String descripcion;

    public int getTipoTrabajador(){return idTipoTrabajador;}

    public void setTipoTrabajador(int idtipoTrabajador){this.idTipoTrabajador = idTipoTrabajador;}

    public String getDescripcion(){return descripcion;}

    public void setDescripcion(String descripcion){this.descripcion = descripcion;}

}
