package com.example.usuario.mitiendav1.backend.model;

public class Usuario {
    private int idUsuario,idTrabajador,estado;
    private String username,clave;

    public int getIdusuario(){return idUsuario;}

    public void setIdusuario(int idUsuario){this.idUsuario = idUsuario;}

    public int getIdtrabajador(){return idTrabajador;}

    public void setIdtrabajador(int idTrabajador){this.idTrabajador = idTrabajador;}

    public int getEstado(){return estado;}

    public void setEstado(int estado){this.estado = estado;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getClave(){return clave;}

    public void setClave(String clave){this.clave = clave; }
}
