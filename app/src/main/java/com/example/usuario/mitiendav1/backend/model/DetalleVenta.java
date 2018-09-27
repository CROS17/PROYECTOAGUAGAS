package com.example.usuario.mitiendav1.backend.model;

public class DetalleVenta {
    private int idDetalleVenta,idVenta,idProducto,cantidad;
    private Double prcio;

    public int getIdDetalleVenta(){return idDetalleVenta;}

    public void setIdDetalleVenta(int idDetalleVenta){this.idDetalleVenta =idDetalleVenta;}

    public int getIdVenta(){return idVenta;}

    public void setIdVenta(int idVenta){this.idVenta = idVenta;}

    public int getIdProducto(){return idProducto;}

    public void setIdProducto(int idProducto){this.idProducto = idProducto;}

    public int getCantidad(){return cantidad;}

    public void setCantidad(int cantidad){this.cantidad = cantidad;}

    public Double getPrcio(){return prcio;}

    public void setPrcio(Double prcio){this.prcio = prcio;}
}
