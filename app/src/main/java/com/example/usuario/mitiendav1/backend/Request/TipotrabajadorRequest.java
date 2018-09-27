package com.example.usuario.mitiendav1.backend.Request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.TipoTrabajador;

import java.util.HashMap;
import java.util.Map;

public class TipotrabajadorRequest extends StringRequest {

    private static final String TIPOTRABAJADOR_REQUEST_URL="http://192.168.0.11/APPGAS/RegistroTipotrabajador.php";
    private Map<String,String> params;
    public TipotrabajadorRequest(TipoTrabajador tipoTrabajador, Response.Listener<String> listener){
        super(Request.Method.POST, TIPOTRABAJADOR_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("descripcion",tipoTrabajador.getDescripcion());


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
