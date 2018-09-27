package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.DetalleCompra;

import java.util.HashMap;
import java.util.Map;

public class DetalleCompraRequest extends StringRequest{

    private static final String DETALLECOMPRA_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroDetalleCompra.php";
    private Map<String,String> params;
    public DetalleCompraRequest(DetalleCompra detalleCompra, Response.Listener<String> listener){
        super( Request.Method.POST, DETALLECOMPRA_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idcompra",String.valueOf(detalleCompra.getIdCompra()));
        params.put("idproducto",String.valueOf(detalleCompra.getIdProducto()));
        params.put("precio",String.valueOf(detalleCompra.getPrcio()));
        params.put("cantidad",String.valueOf(detalleCompra.getCantidad()));

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
