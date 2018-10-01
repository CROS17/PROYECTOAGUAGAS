package com.example.usuario.mitiendav1.backend.model;

public class Cliente {
    private int idCliente;

    private String nombres,telefono,correo,usuario,clave;

    public int getIdcliente(){return idCliente;}

    public void setIdcliente(int idCliente){this.idCliente = idCliente;}

    public String getNombres(){return nombres;}

    public void setNombres(String nombres){this.nombres = nombres;}

    public String getTelefono(){return telefono;}

    public void setTelefono(String telefono){this.telefono = telefono;}

    public String getCorreo(){return correo;}

    public void setCorreo(String correo){this.correo = correo;}

    public String getUsuario(){return usuario;}

    public void setUsuario(String usuario){this.usuario = usuario;}

    public String getClave(){return clave;}

    public void setClave(String clave){this.clave = clave;}

}
