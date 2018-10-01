package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Cliente;


import java.util.HashMap;
import java.util.Map;

public class ClienteRequest extends StringRequest{

    private static final String CLIENTE_REQUEST_URL="http://192.168.0.11/aguagas/registrarcliente.php";
    private Map<String,String> params;
    public ClienteRequest(String nombres, String telefono, String correo, String usuario,String clave, Response.Listener<String> listener){
        super( Request.Method.POST, CLIENTE_REQUEST_URL,listener,null);
        params=new HashMap<>();

        params.put("nombres",nombres);
        params.put("telefono",telefono);
        params.put("correo",correo);
        params.put("usuario",usuario);
        params.put("clave",clave);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
