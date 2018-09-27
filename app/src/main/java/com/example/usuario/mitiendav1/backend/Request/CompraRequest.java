package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Compra;

import java.util.HashMap;
import java.util.Map;

public class CompraRequest extends StringRequest{

    private static final String COMPRA_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroCompra.php";
    private Map<String,String> params;
    public CompraRequest(Compra compra, Response.Listener<String> listener){
        super( Request.Method.POST, COMPRA_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idtrabajador",String.valueOf(compra.getIdTrabajador()));
        params.put("direccion",compra.getDireccion());
        params.put("cantidadproductos",String.valueOf(compra.getCantidadProductos()));
        params.put("montototal",String.valueOf(compra.getMontoTotal()));
        params.put("precio",String.valueOf(compra.getPrecio()));
        params.put("igv",String.valueOf(compra.getIgv()));

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
