package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Cliente;


import java.util.HashMap;
import java.util.Map;

public class ClienteRequest extends StringRequest{

    private static final String CLIENTE_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroCliente.php";
    private Map<String,String> params;
    public ClienteRequest(Cliente cliente, Response.Listener<String> listener){
        super( Request.Method.POST, CLIENTE_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idtipocliente",String.valueOf(cliente.getIdtipocliente()));
        params.put("nombres",cliente.getNombres());
        params.put("razonsocial",cliente.getRazonsocial());
        params.put("ruc",cliente.getRuc());
        params.put("dni",cliente.getDni());
        params.put("telefono",String.valueOf(cliente.getTelefono()));
        params.put("fechanacimiento",String.valueOf(cliente.getFechaNacimiento()));
        params.put("correo",cliente.getCorreo());
        params.put("direccion",cliente.getCorreo());
        params.put("usuario",cliente.getUsuario());
        params.put("clave",String.valueOf(cliente.getClave()));
        params.put("estado",String.valueOf(cliente.getEstado()));


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
