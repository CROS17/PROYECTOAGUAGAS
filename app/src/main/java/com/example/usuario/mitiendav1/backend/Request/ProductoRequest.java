package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Producto;


import java.util.HashMap;
import java.util.Map;

public class ProductoRequest extends StringRequest {

    private static final String PRODUCTO_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroProducto.php";
    private Map<String,String> params;
    public ProductoRequest(Producto producto, Response.Listener<String> listener){
        super(Request.Method.POST, PRODUCTO_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idcategoria",String.valueOf(producto.getIdCategoria()));
        params.put("nombre",producto.getNombre());
        params.put("precioventa",String.valueOf(producto.getPrecioVenta()));
        params.put("preciocompra",String.valueOf(producto.getPrecioCompra()));
        params.put("stock",String.valueOf(producto.getStock()));
        params.put("fechavencimiento",producto.getFechaNacimiento());
        params.put("img",String.valueOf(producto.getImg()));


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
