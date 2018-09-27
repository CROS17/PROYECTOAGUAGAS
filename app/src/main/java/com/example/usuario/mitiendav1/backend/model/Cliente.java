package com.example.usuario.mitiendav1.backend.model;

public class Cliente {
    private int idCliente,idTipoCliente,estado;

    private String nombres,razonsocial,ruc,dni,telefono,fechaNacimiento,correo,direccion,referencia,usuario,clave;

    public int getIdcliente(){return idCliente;}

    public void setIdcliente(int idCliente){this.idCliente = idCliente;}

    public int getIdtipocliente(){return idTipoCliente;}

    public void setIdtipocliente(int idTipoCliente){this.idTipoCliente = idTipoCliente;}

    public String getNombres(){return nombres;}

    public void setNombres(String nombres){this.nombres = nombres;}

    public String getRazonsocial(){return razonsocial;}

    public void setRazonsocial(String razonsocial){this.razonsocial = razonsocial;}

    public String getRuc(){return ruc;}

    public void setRuc(String ruc){this.ruc = ruc; }

    public String getDni(){return dni;}

    public void setDni(String dni){this.dni = dni;}

    public String getTelefono(){return telefono;}

    public void setTelefono(String telefono){this.telefono = telefono;}

    public String getFechaNacimiento(){return fechaNacimiento;}

    public void setFechaNacimiento(String fechaNacimiento){this.fechaNacimiento = fechaNacimiento;}

    public String getCorreo(){return correo;}

    public void setCorreo(String correo){this.correo = correo;}

    public String getDireccion(){return direccion;}

    public void setDireccion(String direccion){this.direccion = direccion;}

    public String getReferencia(){return referencia;}

    public void setReferencia(String referencia){this.referencia = referencia;}

    public String getUsuario(){return usuario;}

    public void setUsuario(String usuario){this.usuario = usuario;}

    public String getClave(){return clave;}

    public void setClave(String clave){this.clave = clave;}

    public int getEstado(){return estado;}

    public void setEstado(int estado){this.estado = estado; }
}
