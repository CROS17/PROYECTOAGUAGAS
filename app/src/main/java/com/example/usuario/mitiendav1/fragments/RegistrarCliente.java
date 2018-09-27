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
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuario.mitiendav1.R;

import com.example.usuario.mitiendav1.backend.Request.ClienteRequest;
import com.example.usuario.mitiendav1.backend.model.Cliente;
import com.example.usuario.mitiendav1.backend.model.TipoCliente;
import com.example.usuario.mitiendav1.backend.util.DireccionHost;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistrarCliente extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener,
        AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner_tipocliente;
    EditText etnombrecliente,etrazonsocialcliente,etruccliente,etdnicliente,ettelefonocliente,etfechanaccliente,etcorreocliente,etdireccioncliente,etreferenciacliente,etusuarioclient,etclaveclient,etestadoclient;
    private Button btnfechanaccliente,btnRegistrarClient;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private ArrayList<TipoCliente> tipoclientList;
    private RegistrarTipocliente.OnFragmentInteractionListener mListener;

    public RegistrarCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarCliente newInstance(String param1, String param2) {
        RegistrarCliente fragment = new RegistrarCliente();
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
        etnombrecliente.setText("");
        etrazonsocialcliente.setText("");
        etruccliente.setText("");
        etdnicliente.setText("");
        ettelefonocliente.setText("");
        etfechanaccliente.setText("");
        etcorreocliente.setText("");
        etdireccioncliente.setText("");
        etreferenciacliente.setText("");
        etusuarioclient.setText("");
        etclaveclient.setText("");
        etestadoclient.setText("");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate( R.layout.fragment_registrar_cliente, container, false);
        spinner_tipocliente = (Spinner)vista.findViewById(R.id.spinner_tipocliente);
        etnombrecliente = (EditText)vista.findViewById(R.id.etnombrecliente);
        etrazonsocialcliente = (EditText)vista.findViewById(R.id.etrazonsocialcliente);
        etruccliente = (EditText)vista.findViewById(R.id.etruccliente);
        etdnicliente = (EditText)vista.findViewById(R.id.etdnicliente);
        ettelefonocliente = (EditText)vista.findViewById(R.id.ettelefonocliente);
        etfechanaccliente = (EditText)vista.findViewById(R.id.etfechanaccliente);
        etcorreocliente = (EditText)vista.findViewById(R.id.etcorreocliente);
        etdireccioncliente = (EditText)vista.findViewById(R.id.etdireccioncliente);
        etreferenciacliente = (EditText)vista.findViewById(R.id.etreferenciacliente);
        etusuarioclient = (EditText)vista.findViewById(R.id.etusuarioclient);
        etclaveclient = (EditText)vista.findViewById(R.id.etclaveclient);
        etestadoclient = (EditText)vista.findViewById(R.id.etestadoclient);
        tipoclientList = new ArrayList<TipoCliente>();
        cargarTipocliente();
        Button btnRegistrarClient = (Button)vista.findViewById(R.id.btnRegistrarClient);
        btnfechanaccliente=(Button)vista.findViewById(R.id.btnfechanaccliente);
        btnfechanaccliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment miFragment=new DireccionFragment();
                Toast.makeText(getContext(), "SELECCIONO FECHA", Toast.LENGTH_SHORT).show();

            }
        });
        btnRegistrarClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = new Cliente();

                cliente.setIdtipocliente(Integer.parseInt(spinner_tipocliente.getSelectedItem().toString()));
                cliente.setNombres(etnombrecliente.getText().toString());
                cliente.setRazonsocial(etrazonsocialcliente.getText().toString());
                cliente.setRuc(etruccliente.getText().toString());
                cliente.setDni(etdnicliente.getText().toString());
                cliente.setTelefono(ettelefonocliente.getText().toString());
                cliente.setFechaNacimiento(etfechanaccliente.getText().toString());
                cliente.setCorreo(etcorreocliente.getText().toString());
                cliente.setDireccion(etdireccioncliente.getText().toString());
                cliente.setReferencia(etreferenciacliente.getText().toString());
                cliente.setUsuario(etusuarioclient.getText().toString());
                cliente.setClave(etclaveclient.getText().toString());
                cliente.setEstado(Integer.parseInt(etestadoclient.getText().toString()));

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

                ClienteRequest clienteRequest = new ClienteRequest(cliente, respoListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(clienteRequest);
            }
        });


        return vista;


    }
    private void cargarTipocliente() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        DireccionHost dh=new DireccionHost();

        String url=dh.getIp()+"listarTipocliente.php";
        System.out.println(url);
        System.out.println(url);

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
        TipoCliente tipoclient=null;
        JSONArray json=response.optJSONArray("categoria");
        try {

            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                tipoclient=new TipoCliente();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                tipoclient.setIdTipoCliente(jsonObject.optInt("idtipocliente"));
                tipoclient.setDescripcion(jsonObject.optString("descripcion"));
                System.out.println("ENTRO AL JSON NOMBRE : "+jsonObject.optString("descripcion"));
                tipoclientList.add(tipoclient);
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
        for (int i = 0; i < tipoclientList.size(); i++) {
            lables.add(tipoclientList.get(i).getDescripcion());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tipocliente.setAdapter(spinnerAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

