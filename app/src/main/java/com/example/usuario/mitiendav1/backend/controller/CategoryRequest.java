package com.example.usuario.mitiendav1.backend.controller;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.backend.model.Categoria;

import java.util.HashMap;
import java.util.Map;

public class CategoryRequest extends StringRequest {

    private static final String CATEGORY_REQUEST_URL="http://192.168.0.11/APPGAS/RegistrarCategoria.php";
    private Map<String,String> params;
    public CategoryRequest(Categoria categoria, Response.Listener<String> listener){
        super(Request.Method.POST, CATEGORY_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("nombre",categoria.getNombre());
        params.put("descripcion",categoria.getDescripcion());

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
