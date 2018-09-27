package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.DetalleVenta;


import java.util.HashMap;
import java.util.Map;

public class DetalleVentaRequest extends StringRequest{

    private static final String DETALLEVENTA_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroDetalleVenta.php";
    private Map<String,String> params;
    public DetalleVentaRequest(DetalleVenta detalleVenta, Response.Listener<String> listener){
        super( Request.Method.POST, DETALLEVENTA_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idventa",String.valueOf(detalleVenta.getIdVenta()));
        params.put("idproducto",String.valueOf(detalleVenta.getIdProducto()));
        params.put("precio",String.valueOf(detalleVenta.getPrcio()));
        params.put("cantidad",String.valueOf(detalleVenta.getCantidad()));

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
