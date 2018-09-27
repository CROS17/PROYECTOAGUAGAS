package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Trabajador;

import java.util.HashMap;
import java.util.Map;

public class TrabajadorRequest extends StringRequest {

    private static final String TRABAJADOR_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroTrabajador.php";
    private Map<String,String> params;
    public TrabajadorRequest(Trabajador trabajador, Response.Listener<String> listener){
        super(Request.Method.POST, TRABAJADOR_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("idtipotrabajador",String.valueOf(trabajador.getIdTipoTrabajador()));
        params.put("nombres",trabajador.getNombres());
        params.put("apellidos",trabajador.getApellidos());
        params.put("fechanacimiento",String.valueOf(trabajador.getFechaNacimiento()));
        params.put("correo",trabajador.getCorreo());
        params.put("telefono",String.valueOf(trabajador.getTelefono()));
        params.put("fecharegistro",String.valueOf(trabajador.getFechaRegistro()));
        params.put("sexo",String.valueOf(trabajador.getSexo()));


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

