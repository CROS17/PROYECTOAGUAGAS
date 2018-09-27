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
import com.example.usuario.mitiendav1.backend.util.DireccionHost;
import com.example.usuario.mitiendav1.backend.util.RequestHandler;
import com.example.usuario.mitiendav1.backend.util.Rutas;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;
import com.example.usuario.mitiendav1.fragments.adaptersListados.AdapterCategoria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListadoCategory extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //VARIABLES

    ArrayList<Categoria> listaCategoria;
    public static RecyclerView recyclerCategoria;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progress;
    private Button btnbuscar;
    public static   int control;
    public static AdapterCategoria adapter;
    private EditText etbuscar;
    private ProgressDialog progressDialog;
    private OnFragmentInteractionListener mListener;

    public ListadoCategory() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_listado_category, container, false);
        btnbuscar=(Button)vista.findViewById(R.id.btnbuscarcategoria) ;
        etbuscar=(EditText) vista.findViewById(R.id.etbuscarcategoria);
        listaCategoria=new ArrayList<>();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere Porfavor...");
        recyclerCategoria= (RecyclerView) vista.findViewById(R.id.recyclerListarCategoria);
        recyclerCategoria.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerCategoria.setHasFixedSize(true);

        llenarListaCategoria();
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(evaluar()){
                    buscarcategoria(etbuscar.getText().toString());
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
    private void llenarListaCategoria() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url= Rutas.URL_LISTAR_CATEGORIA;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        Categoria categoria=null;
        JSONArray json=response.optJSONArray("categoria");
        try {

            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                categoria=new Categoria();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                categoria.setIdcategoria(Integer.parseInt(jsonObject.optString("idcategoria")));
                categoria.setNombre(jsonObject.optString("nombre"));
                categoria.setDescripcion(jsonObject.optString("descripcion"));
                listaCategoria.add(categoria);
            }
            progress.hide();
            AdapterCategoria adapter=new AdapterCategoria(listaCategoria);
            recyclerCategoria.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }

    private void buscarcategoria(final String  buscar){
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Rutas.URL_BUSCAR_CATEGORIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            control=0;
                            ArrayList<Categoria> alcategoria=new ArrayList<>();

                            Categoria horaxdia=null;
                            JSONObject obj = new JSONObject(response);
                            JSONObject jsonObject= new JSONObject(response.toString());
                            System.out.println(jsonObject);
                            JSONArray json = jsonObject.getJSONArray("categoria");

                            for (int i=0;i<json.length();i++){
                                control++;
                                horaxdia=new Categoria();
                                JSONObject jsonObjectt=null;
                                jsonObjectt=json.getJSONObject(i);
                                horaxdia.setIdcategoria(Integer.parseInt(jsonObjectt.optString("0")));
                                horaxdia.setNombre(jsonObjectt.optString("1"));
                                horaxdia.setDescripcion(jsonObjectt.optString("2"));
                                alcategoria.add(horaxdia);
                            }
                            adapter=new AdapterCategoria(alcategoria);
                            recyclerCategoria.setAdapter(adapter);
                            if(control==0){
                                Toast.makeText(getContext(),
                                        "NO SE ENCUENTRAN HORARIOS REGISTRADOS EN LA FECHA SELECCIONADA", Toast.LENGTH_SHORT).show();
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
