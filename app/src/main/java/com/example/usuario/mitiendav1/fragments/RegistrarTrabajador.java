package com.example.usuario.mitiendav1.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuario.mitiendav1.R;

import com.example.usuario.mitiendav1.backend.Request.TrabajadorRequest;
import com.example.usuario.mitiendav1.backend.model.TipoTrabajador;
import com.example.usuario.mitiendav1.backend.model.Trabajador;
import com.example.usuario.mitiendav1.backend.util.DireccionHost;
import com.example.usuario.mitiendav1.backend.util.Rutas;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistrarTrabajador extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener,
        AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner_tipotrabajador;
    EditText etnombretrab,etapellidotrab,etfechanactrab,ettelefonotrab,etcorreotrab,etfecharegtrab;
    private Button btnRegistrarTrabajador,btnfechanactrab,btnfecharegtrab;
    RadioButton radioButtonH,radioButtonM;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private ArrayList<TipoTrabajador> tipotrabList;
    private RegistrarTipoTrabajador.OnFragmentInteractionListener mListener;

    public RegistrarTrabajador() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static RegistrarTrabajador newInstance(String param1, String param2) {
        RegistrarTrabajador fragment = new RegistrarTrabajador();
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
    public void limpiarCampos(){
        etnombretrab.setText("");
        etapellidotrab.setText("");
        etfechanactrab.setText("");
        ettelefonotrab.setText("");
        etcorreotrab.setText("");
        etfecharegtrab.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate( R.layout.fragment_registrar_trabajador, container, false);

        spinner_tipotrabajador = (Spinner)vista.findViewById(R.id.spinner_tipotrabajador);
        etnombretrab = (EditText)vista.findViewById(R.id.etnombretrab);
        etapellidotrab = (EditText)vista.findViewById(R.id.etapellidotrab);
        etfechanactrab = (EditText)vista.findViewById(R.id.etfechanactrab);
        ettelefonotrab = (EditText)vista.findViewById(R.id.ettelefonotrab);
        etcorreotrab = (EditText)vista.findViewById(R.id.etcorreotrab);

        tipotrabList = new ArrayList<TipoTrabajador>();
        cargarTipotrabajador();
        Button btnRegistrarTrabajador = (Button)vista.findViewById(R.id.btnRegistrarTrabajador);
        btnfechanactrab=(Button)vista.findViewById(R.id.btnfechanactrab);
        btnfechanactrab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment miFragment=new DireccionFragment();
                Toast.makeText(getContext(), "SELECCIONO FECHA", Toast.LENGTH_SHORT).show();
            }
        });




        btnRegistrarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trabajador trabajador = new Trabajador();
                trabajador.setNombres(etnombretrab.getText().toString());
                trabajador.setIdTipoTrabajador(Integer.parseInt(spinner_tipotrabajador.getSelectedItem().toString()));
                trabajador.setApellidos(etapellidotrab.getText().toString());
                trabajador.setCorreo(etcorreotrab.getText().toString());
                trabajador.setTelefono(ettelefonotrab.getText().toString());
                trabajador.setFechaNacimiento(etfechanactrab.getText().toString());
                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(getContext(), "Registro Correcto", Toast.LENGTH_SHORT).show();
                                limpiarCampos();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("error registro")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                TrabajadorRequest trabajadorRequest = new TrabajadorRequest(trabajador, respoListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(trabajadorRequest);
            }
        });
        return vista;
    }
    private void cargarTipotrabajador() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();
        String url= Rutas.URL_LISTAR_TIPOTRABAJADOR;
        jsonObjectRequest=new JsonObjectRequest( Request.Method.POST,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        TipoTrabajador tipotrab=null;
        JSONArray json=response.optJSONArray("tipotrabajador");
        try {
            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                tipotrab=new TipoTrabajador();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                tipotrab.setTipoTrabajador(jsonObject.optInt("idtipotrabajador"));
                tipotrab.setDescripcion(jsonObject.optString("descripcion"));
                System.out.println("ENTRO AL JSON NOMBRE : "+jsonObject.optString("descripcion"));
                tipotrabList.add(tipotrab);
            }
            populateSpinner();
            progress.hide();


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }

    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();
        for (int i = 0; i < tipotrabList.size(); i++) {
            lables.add(tipotrabList.get(i).getDescripcion());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tipotrabajador.setAdapter(spinnerAdapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

