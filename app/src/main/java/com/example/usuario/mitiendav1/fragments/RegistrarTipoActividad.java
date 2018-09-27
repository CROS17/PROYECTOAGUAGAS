package com.example.usuario.mitiendav1.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.example.usuario.mitiendav1.backend.model.TipoActividad;
import com.example.usuario.mitiendav1.backend.util.RequestHandler;
import com.example.usuario.mitiendav1.backend.util.Rutas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarTipoActividad extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText etdescripcion;
    private Button btnRegistrarTipoActi;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private RegistrarTipoActividad.OnFragmentInteractionListener mListener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public RegistrarTipoActividad() {
        // Required empty public constructor

    }


    // TODO: Rename and change types and number of parameters
    public static RegistrarTipoActividad newInstance(String param1, String param2) {
        RegistrarTipoActividad fragment = new RegistrarTipoActividad();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate( R.layout.fragment_registrar_tipocliente, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere por favor...");
        etdescripcion = (EditText)vista.findViewById(R.id.etdescripcion);
        Button btnRegistrarTipoActi = (Button)vista.findViewById(R.id.btnRegistrarTipoActi);

        btnRegistrarTipoActi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(evaluar()){
                    TipoActividad tipoActividad = new TipoActividad();
                    tipoActividad.setDescripcion(etdescripcion.getText().toString());
                    registrotipoact("1",tipoActividad);
                }
            }});
        return vista;


    }


    public  boolean evaluar(){
        if(etdescripcion.getText().toString().length()==0 ){
            if(etdescripcion.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR DESCRIPCION DEL TIPO DE ACTIVIDAD",Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void limpiarCampos(){

        etdescripcion.setText("");

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistrarTipoActividad.OnFragmentInteractionListener) {
            mListener = (RegistrarTipoActividad.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void registrotipoact(final String idtipoactividad,final TipoActividad tipoActividad){
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Rutas.URL_REGISTRAR_TIPOACTIVIDAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.has("error") && !obj.getBoolean("error")){
                                if(obj.has("res") && ((obj.getInt("res")==1 || obj.getInt("res")==0)))
                                    if(obj.getInt("res")==1){
                                        limpiarCampos();
                                        Toast.makeText(getContext(),obj.getString("mensaje"), Toast.LENGTH_LONG).show();
                                    }
                                if(obj.getInt("res")==0){
                                    Toast.makeText(getContext(),obj.getString("mensaje"), Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(
                                        getContext(),
                                        "ERROR DE REGISTRO",
                                        Toast.LENGTH_LONG
                                ).show();
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
                params.put("idtipoactividad",idtipoactividad);
                params.put("descripcion",tipoActividad.getDescripcion());
                return params;
            }

        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
