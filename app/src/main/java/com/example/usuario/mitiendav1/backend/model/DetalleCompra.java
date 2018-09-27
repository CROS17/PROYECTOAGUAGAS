package com.example.usuario.mitiendav1.backend.model;

public class DetalleCompra {
    private int idDetalleCompra,idCompra,idProducto,cantidad;
    private Double prcio;

    public int getIdDetalleCompra(){return idDetalleCompra;}

    public void setIdDetalleCompra(int idDetalleCompra){this.idDetalleCompra = idDetalleCompra;}

    public int getIdCompra(){return idCompra;}

    public void setIdCompra(int idCompra){this.idCompra = idCompra;}

    public int getIdProducto(){return idProducto;}

    public void setIdProducto(int idProducto){this.idProducto = idProducto;}

    public int getCantidad(){return cantidad;}

    public void setCantidad(int cantidad){this.cantidad = cantidad;}

    public Double getPrcio(){return prcio;}

    public void setPrcio(Double prcio){this.prcio = prcio;}
}
