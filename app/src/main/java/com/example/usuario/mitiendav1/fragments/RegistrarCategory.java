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
import com.example.usuario.mitiendav1.backend.model.Categoria;
import com.example.usuario.mitiendav1.backend.util.RequestHandler;
import com.example.usuario.mitiendav1.backend.util.Rutas;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarCategory.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarCategory extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText etnombre,etdescripcion;
    private Button btnregistrarcat;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private OnFragmentInteractionListener mListener;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public RegistrarCategory() {
        // Required empty public constructor



    }


    // TODO: Rename and change types and number of parameters
    public static RegistrarCategory newInstance(String param1, String param2) {
        RegistrarCategory fragment = new RegistrarCategory();
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
        View vista = inflater.inflate(R.layout.fragment_registrar_category, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere por pavor...");
        etnombre = (EditText)vista.findViewById(R.id.etnombre);
        etdescripcion = (EditText)vista.findViewById(R.id.etdescripcion);
        Button btnregistrarcat = (Button)vista.findViewById(R.id.btnregistrarcat);

        btnregistrarcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(evaluar()){
                Categoria cate = new Categoria();
                cate.setNombre(etnombre.getText().toString());
                cate.setDescripcion(etdescripcion.getText().toString());
                    registrocategoria("1",cate);
            }
        }});
        return vista;


    }


   public  boolean evaluar(){
        if(etnombre.getText().toString().length()==0 ||etdescripcion.getText().toString().length()==0 ){
            if(etnombre.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR NOMBRE DE CATEGORIA",Toast.LENGTH_SHORT).show();
            }
            if(etdescripcion.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR DESCRIPCION DE  CATEGORIA",Toast.LENGTH_SHORT).show();
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
        etnombre.setText("");
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void registrocategoria(final String iduser,final Categoria categoria){
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Rutas.URL_REGISTRAR_CATEGORIA,
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
                params.put("iduser",iduser);
                params.put("nombre",categoria.getNombre());
                params.put("descripcion",categoria.getDescripcion());
                return params;
            }

        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
