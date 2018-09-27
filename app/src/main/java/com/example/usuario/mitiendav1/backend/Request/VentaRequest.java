package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Venta;

import java.util.HashMap;
import java.util.Map;

public class VentaRequest extends StringRequest{

    private static final String COMPRA_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroVenta.php";
    private Map<String,String> params;
    public VentaRequest(Venta venta, Response.Listener<String> listener){
        super( Request.Method.POST, COMPRA_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idcliente",String.valueOf(venta.getIdCliente()));
        params.put("direccion",venta.getDireccion());
        params.put("cantidadproductos",String.valueOf(venta.getCantidadProductos()));
        params.put("montototal",String.valueOf(venta.getMontoTotal()));
        params.put("precio",String.valueOf(venta.getPrecio()));
        params.put("igv",String.valueOf(venta.getIgv()));

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

