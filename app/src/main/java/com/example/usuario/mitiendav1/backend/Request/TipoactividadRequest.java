package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.TipoActividad;

import java.util.HashMap;
import java.util.Map;

public class TipoactividadRequest extends  StringRequest{

    private static final String TIPOACTIVIDAD_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroTipoactividad.php";
    private Map<String,String> params;
    public TipoactividadRequest(TipoActividad tipoActividad, Response.Listener<String> listener){
        super( Request.Method.POST, TIPOACTIVIDAD_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("descripcion",tipoActividad.getDescripcion());


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}