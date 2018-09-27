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
import com.example.usuario.mitiendav1.backend.Request.VentaRequest;
import com.example.usuario.mitiendav1.backend.model.Cliente;
import com.example.usuario.mitiendav1.backend.model.Venta;
import com.example.usuario.mitiendav1.backend.util.DireccionHost;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistrarVenta extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener,
        AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner_cliente;
    EditText etdireccionvent,etcantproductoventa,etmontototalventa,etprecioventa,etigvventa;
    private Button btnRegistrarVenta;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private ArrayList<Cliente> clientelist;
    private RegistrarCliente.OnFragmentInteractionListener mListener;

    public RegistrarVenta() {
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
    public static RegistrarVenta newInstance(String param1, String param2) {
        RegistrarVenta fragment = new RegistrarVenta();
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
        etdireccionvent.setText("");
        etcantproductoventa.setText("");
        etmontototalventa.setText("");
        etprecioventa.setText("");
        etigvventa.setText("");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate( R.layout.fragment_registrar_venta, container, false);
        spinner_cliente = (Spinner)vista.findViewById(R.id.spinner_cliente);
        etdireccionvent = (EditText)vista.findViewById(R.id.etdireccionvent);
        etcantproductoventa = (EditText)vista.findViewById(R.id.etcantproductoventa);
        etmontototalventa = (EditText)vista.findViewById(R.id.etmontototalventa);
        etprecioventa = (EditText)vista.findViewById(R.id.etprecioventa);
        etigvventa = (EditText)vista.findViewById(R.id.etigvventa);
        clientelist = new ArrayList<Cliente>();
        cargarClientes();
        Button btnRegistrarVenta = (Button)vista.findViewById(R.id.btnRegistrarVenta);

        btnRegistrarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Venta venta = new Venta();

                venta.setIdCliente(Integer.parseInt(spinner_cliente.getSelectedItem().toString()));
                venta.setDireccion(etdireccionvent.getText().toString());
                venta.setCantidadProductos(Integer.parseInt(etcantproductoventa.getText().toString()));
                venta.setMontoTotal(Double.parseDouble(etmontototalventa.getText().toString()));
                venta.setPrecio(Double.parseDouble(etprecioventa.getText().toString()));
                venta.setIgv(Double.parseDouble(etigvventa.getText().toString()));

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

                VentaRequest ventaRequest = new VentaRequest(venta, respoListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(ventaRequest);
            }
        });


        return vista;


    }
    private void cargarClientes() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        DireccionHost dh=new DireccionHost();

        String url=dh.getIp()+"listarCliente.php";
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
        Cliente client=null;
        JSONArray json=response.optJSONArray("categoria");
        try {

            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                client=new Cliente();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                client.setIdcliente(jsonObject.optInt("idcliente"));
                client.setNombres(jsonObject.optString("nombre"));
                System.out.println("ENTRO AL JSON NOMBRE : "+jsonObject.optString("nombre"));
                clientelist.add(client);
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
        for (int i = 0; i < clientelist.size(); i++) {
            lables.add(clientelist.get(i).getNombres());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cliente.setAdapter(spinnerAdapter);
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

