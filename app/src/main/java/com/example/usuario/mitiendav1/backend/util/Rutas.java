package com.example.usuario.mitiendav1.backend.util;

public class Rutas {
    private static final DireccionHost local=new DireccionHost();
    private static final String ROOT_URL =local.getIp();
    public static final String URL_REGISTRAR_CATEGORIA = ROOT_URL+"registrarcategoria.php";
    public static final String URL_LISTAR_CATEGORIA = ROOT_URL+"listarcategoria.php";
    public static final String URL_BUSCAR_CATEGORIA = ROOT_URL+"buscarcategoria.php";
    public static final String URL_REGISTRAR_PRODUCTO = ROOT_URL+"RegistrarProducto.php";
    public static final String URL_LISTAR_PRODUCTO = ROOT_URL+"mostrarproductos.php";
    public static final String URL_BUSCAR_PRODUCTO = ROOT_URL+"buscarproducto.php";
    public static final String URL_LISTAR_TIPOTRABAJADOR= ROOT_URL+"listartipotrabajador.php";
    public static final String URL_REGISTRAR_CLIENTE = ROOT_URL+"RegistrarCliente.php";
    public static final String URL_REGISTRAR_TIPOCLIENTE = ROOT_URL+"RegistrarTipocliente.php";
    public static final String URL_REGISTRAR_TRABAJADOR = ROOT_URL+"RegistrarTrabajador.php";
    public static final String URL_REGISTRAR_TIPOTRABAJADOR = ROOT_URL+"RegistrarTipotrabajador.php";
    public static final String URL_REGISTRAR_COMPRA = ROOT_URL+"RegistrarCompra.php";
    public static final String URL_REGISTRAR_VENTA = ROOT_URL+"RegistrarVenta.php";
    public static final String URL_REGISTRAR_DETALLECOMPRA = ROOT_URL+"RegistrarDetallecompra.php";
    public static final String URL_REGISTRAR_DETALLEVENTA = ROOT_URL+"RegistrarDetalleventa.php";
    public static final String URL_REGISTRAR_ACTIVIDAD = ROOT_URL+"RegistrarActividad.php";
    public static final String URL_REGISTRAR_TIPOACTIVIDAD = ROOT_URL+"RegistrarTipoactividad.php";
    public static final String URL_REGISTRAR_USUARIO = ROOT_URL+"RegistrarUsuario.php";


}
