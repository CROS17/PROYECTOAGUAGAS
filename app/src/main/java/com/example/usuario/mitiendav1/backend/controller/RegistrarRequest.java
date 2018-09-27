package com.example.usuario.mitiendav1.backend.controller;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrarRequest extends StringRequest {

    private static final String REGISTRAR_REQUEST_URL="http://192.168.0.11/APPGAS/register.php";
    private Map<String,String> params;
    public RegistrarRequest(String name, String username, int edad, String clave, Response.Listener<String> listener){
        super(Method.POST, REGISTRAR_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("edad",edad+"");
        params.put("clave",clave);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
