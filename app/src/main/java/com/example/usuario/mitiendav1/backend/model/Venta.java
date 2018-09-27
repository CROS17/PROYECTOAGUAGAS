package com.example.usuario.mitiendav1.backend.model;

public class Venta {
    private int idVenta,idCliente,cantidadProductos;
    private String direccion;
    private Double montoTotal,precio,igv;

    public int getIdVenta(){return idVenta;}

    public void setIdVenta(int idVenta){this.idVenta = idVenta;}

    public int getIdCliente(){return idCliente;}

    public void setIdCliente(int idCliente){this.idCliente = idCliente;}

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
