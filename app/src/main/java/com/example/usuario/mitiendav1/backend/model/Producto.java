package com.example.usuario.mitiendav1.backend.model;

public class Producto {
    private int idProducto,idCategoria,stock;
    private String nombre,fechaNacimiento,img,nombrecategoria;
    private Double precioVenta,precioCompra;

    public String getNombrecategoria() {return nombrecategoria;}

    public void setNombrecategoria(String nombrecategoria) {this.nombrecategoria = nombrecategoria;}

    public int getIdProducto(){return idProducto;}

    public void setIdProducto(int idProducto){this.idProducto = idProducto;}

    public int getIdCategoria(){return idCategoria;}

    public void setIdCategoria(int idCategoria){this.idCategoria = idCategoria;}

    public int getStock(){return stock;}

    public void setStock(int stock){this.stock = stock;}

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){this.nombre = nombre;}

    public String getFechaNacimiento(){return fechaNacimiento;}

    public void setFechaNacimiento(String fechaNacimiento){this.fechaNacimiento = fechaNacimiento;}

    public Double getPrecioVenta(){return precioVenta;}

    public void setPrecioVenta(Double precioVenta){this.precioVenta = precioVenta;}

    public Double getPrecioCompra(){return precioCompra;}

    public void setPrecioCompra(Double precioCompra){this.precioCompra = precioCompra;}

    public void setImg(String img){this.img=img; }

    public String getImg(){return img;}

    }

