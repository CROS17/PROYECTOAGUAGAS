package com.example.usuario.mitiendav1.backend.model;

public class Compra {
    private int idCompra,idTrabajador,cantidadProductos;
    private String direccion;
    private Double montoTotal,precio,igv;

    public int getIdCompra(){return idCompra;}

    public void setIdCompra(int idCompra){this.idCompra = idCompra;}

    public int getIdTrabajador(){return idTrabajador;}

    public void setIdTrabajador(int idTrabajador){this.idTrabajador = idTrabajador;}

    public int getCantidadProductos(){return cantidadProductos;}

    public void setCantidadProductos(int cantidadProductos){this.cantidadProductos = cantidadProductos;}

    public String getDireccion(){return direccion;}

    public void setDireccion(String direccion){this.direccion = direccion;}

    public Double getMontoTotal(){return montoTotal;}

    public void setMontoTotal(Double montoTotal){this.montoTotal = montoTotal;}

    public Double getPrecio(){return precio;}

    public void setPrecio(Double precio){this.precio = precio;}

    public Double getIgv(){return igv;}

    public void setIgv(Double igv){this.igv = igv;}
}
