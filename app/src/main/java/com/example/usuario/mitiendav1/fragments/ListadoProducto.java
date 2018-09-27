package com.example.usuario.mitiendav1.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.usuario.mitiendav1.R;
import com.example.usuario.mitiendav1.backend.model.Categoria;
import com.example.usuario.mitiendav1.backend.model.Producto;
import com.example.usuario.mitiendav1.backend.util.RequestHandler;
import com.example.usuario.mitiendav1.backend.util.Rutas;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;
import com.example.usuario.mitiendav1.fragments.adaptersListados.AdapterCategoria;
import com.example.usuario.mitiendav1.fragments.adaptersListados.AdapterProducto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListadoProducto extends Fragment
        implements  Response.Listener<JSONObject>,Response.ErrorListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //VARIABLES
    //VARIABLES

    ArrayList<Producto> listaProducto;
    public static RecyclerView recyclerProducto;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progress;
    private Button btnbuscar;
    public static   int control;
    public static AdapterProducto adapter;
    private EditText etbuscar;
    private ProgressDialog progressDialog;

    private OnFragmentInteractionListener mListener;

    public ListadoProducto() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_listado_producto, container, false);
        btnbuscar=(Button)vista.findViewById(R.id.btnbuscarproducto) ;
        etbuscar=(EditText) vista.findViewById(R.id.etbuscarcaproducto);
        listaProducto=new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere Porfavor...");
        recyclerProducto= (RecyclerView) vista.findViewById(R.id.recyclerListarProducto);
        recyclerProducto.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerProducto.setHasFixedSize(true);
        llenarListaProducto();
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(evaluar()){
                    buscarcaproducto(etbuscar.getText().toString());
                }
            }
        });
        return vista;
    }
    public boolean evaluar(){
        if(etbuscar.getText().toString().length()==0){
            Toast.makeText(getContext(),"DEBE INGRESAR UN NOMBRE",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void llenarListaProducto() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url= Rutas.URL_LISTAR_PRODUCTO;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Producto producto=null;
        JSONArray json=response.optJSONArray("producto");
        try {
            for (int i=0;i<json.length();i++){
                producto=new Producto();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                producto.setIdProducto(Integer.parseInt(jsonObject.optString("v1")));
                producto.setNombrecategoria(jsonObject.optString("v2"));
                producto.setNombre(jsonObject.optString("v3"));
                producto.setPrecioVenta(Double.parseDouble(jsonObject.optString("v4")));
                producto.setPrecioCompra(Double.parseDouble(jsonObject.optString("v5")));
                producto.setStock(Integer.parseInt(jsonObject.optString("v6")));
                listaProducto.add(producto);
            }
            progress.hide();
            AdapterProducto adapter=new AdapterProducto(listaProducto);
            recyclerProducto.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
    private void buscarcaproducto(final String  buscar){
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Rutas.URL_BUSCAR_PRODUCTO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            control=0;
                            ArrayList<Producto> alproducto=new ArrayList<>();

                            Producto producto=null;
                            JSONObject obj = new JSONObject(response);
                            JSONObject jsonObject= new JSONObject(response.toString());
                            System.out.println(jsonObject);
                            JSONArray json = jsonObject.getJSONArray("producto");

                            for (int i=0;i<json.length();i++){
                                control++;
                                producto=new Producto();
                                JSONObject jsonObjectt=null;
                                jsonObjectt=json.getJSONObject(i);
                                System.out.println("objeto _: "+json.getJSONObject(i));
                                producto.setIdProducto(Integer.parseInt(jsonObjectt.optString("v1")));
                                producto.setNombrecategoria(jsonObjectt.optString("v2"));
                                producto.setNombre(jsonObjectt.optString("v3"));
                                producto.setPrecioVenta(Double.parseDouble(jsonObjectt.optString("v4")));
                                producto.setPrecioCompra(Double.parseDouble(jsonObjectt.optString("v5")));
                                producto.setStock(Integer.parseInt(jsonObjectt.optString("v6")));
                                alproducto.add(producto);
                            }
                            adapter=new AdapterProducto(alproducto);
                            recyclerProducto.setAdapter(adapter);
                            if(control==0){
                                Toast.makeText(getContext(),
                                        "NO SE ENCUENTRAN REGISTRADO PRODUCTO CON DICHO NOMBRE", Toast.LENGTH_SHORT).show();
                                btnbuscar.setEnabled(false);
                            }
                            if(control>0){
                                btnbuscar.setEnabled(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nombre",buscar);
                return params;
            }

        };
        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
