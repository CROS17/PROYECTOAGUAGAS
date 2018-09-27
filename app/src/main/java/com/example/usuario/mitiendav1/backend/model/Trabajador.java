package com.example.usuario.mitiendav1.backend.model;

public class Trabajador {
    private int idTrabajador,idTipoTrabajador,sexo;
    private String nombres,apellidos,correo,telefono,fechaNacimiento,fechaRegistro;

    public int getIdTrabajador(){return idTrabajador;}

    public void setIdTrabajador(int idTrabajador){this.idTrabajador = idTrabajador;}

    public int getIdTipoTrabajador(){return idTipoTrabajador;}

    public void setIdTipoTrabajador(int idTipoTrabajador){this.idTipoTrabajador = idTipoTrabajador;}

    public int getSexo(){return sexo;}

    public void setSexo(int sexo){this.sexo = sexo;}

    public String getNombres(){return nombres;}

    public void setNombres(String nombres){this.nombres = nombres;}

    public String getApellidos(){return apellidos;}

    public void setApellidos(String apellidos){this.apellidos = apellidos;}

    public String getCorreo(){return correo;}

    public void setCorreo(String correo){this.correo = correo;}

    public String getTelefono(){return telefono;}

    public void setTelefono(String telefono){this.telefono = telefono;}

    public String getFechaNacimiento(){return fechaNacimiento;}

    public void setFechaNacimiento(String fechaNacimiento){this.fechaNacimiento = fechaNacimiento;}

    public String getFechaRegistro(){return fechaRegistro;}

    public void setFechaRegistro(String fechaRegistro){this.fechaRegistro = fechaRegistro;}
}
