package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.TipoCliente;

import java.util.HashMap;
import java.util.Map;

public class TipoclienteRequest extends StringRequest{
    private static final String TIPOCLIENTE_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroTipocliente.php";
    private Map<String,String> params;
    public TipoclienteRequest(TipoCliente tipoCliente, Response.Listener<String> listener){
        super( Request.Method.POST, TIPOCLIENTE_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("descripcion",tipoCliente.getDescripcion());


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

