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
import com.example.usuario.mitiendav1.backend.model.DetalleVenta;
import com.example.usuario.mitiendav1.backend.Request.DetalleVentaRequest;
import com.example.usuario.mitiendav1.backend.model.Venta;
import com.example.usuario.mitiendav1.backend.model.Producto;
import com.example.usuario.mitiendav1.backend.util.DireccionHost;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistrarDetalleVenta extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener,
        AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinner_venta,spinner_producto;
    EditText etpreciodetalleventa,etcantdetventa;
    private Button btnRegistrarDetalleVenta;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private ArrayList<Venta> ventaList;
    private RegistrarCompra.OnFragmentInteractionListener mListener;
    private ArrayList<Producto> productoList;
    private RegistrarProducto.OnFragmentInteractionListener mListener2;

    public RegistrarDetalleVenta() {
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
    public static RegistrarDetalleVenta newInstance(String param1, String param2) {
        RegistrarDetalleVenta fragment = new RegistrarDetalleVenta();
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
        etpreciodetalleventa.setText("");
        etcantdetventa.setText("");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate( R.layout.fragment_registrar_detalleventa, container, false);
        spinner_venta = (Spinner)vista.findViewById(R.id.spinner_venta);
        spinner_producto = (Spinner)vista.findViewById(R.id.spinner_producto);
        etpreciodetalleventa = (EditText)vista.findViewById(R.id.etpreciodetalleventa);
        etcantdetventa = (EditText)vista.findViewById(R.id.etcantdetventa);
        ventaList = new ArrayList<Venta>();
        productoList = new ArrayList<Producto>();
        cargaVenta();
        cargarProducto();
        Button btnRegistrarDetalleVenta = (Button)vista.findViewById(R.id.btnRegistrarDetalleVenta);

        btnRegistrarDetalleVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetalleVenta detventa = new DetalleVenta();

                detventa.setIdVenta(Integer.parseInt(spinner_venta.getSelectedItem().toString()));
                detventa.setIdProducto(Integer.parseInt(spinner_producto.getSelectedItem().toString()));
                detventa.setPrcio(Double.parseDouble(etpreciodetalleventa.getText().toString()));
                detventa.setCantidad(Integer.parseInt(etcantdetventa.getText().toString()));

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

                DetalleVentaRequest detalleVentaAdapter = new DetalleVentaRequest(detventa, respoListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(detalleVentaAdapter);
            }
        });


        return vista;


    }
    private void cargaVenta() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        DireccionHost dh=new DireccionHost();

        String url=dh.getIp()+"listarCompra.php";
        System.out.println(url);
        System.out.println(url);

        jsonObjectRequest=new JsonObjectRequest( Request.Method.POST,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }
    private void cargarProducto() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        DireccionHost dh=new DireccionHost();

        String url=dh.getIp()+"listarProducto.php";
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
        }if (mListener2 != null) {
            mListener2.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mListener2 = null;
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
        Venta vent=null;
        JSONArray json=response.optJSONArray("compra");
        try {

            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                vent=new Venta();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                vent.setIdVenta(jsonObject.optInt("idcompra"));
                vent.setDireccion(jsonObject.optString("direccion"));
                System.out.println("ENTRO AL JSON NOMBRE : "+jsonObject.optString("direccion"));
                ventaList.add(vent);
            }
            populateSpinner();
            progress.hide();


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

        Producto com=null;
        JSONArray json2=response.optJSONArray("producto");
        try {

            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                com=new Producto();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                com.setIdProducto(jsonObject.optInt("idproducto"));
                com.setNombre(jsonObject.optString("nombre"));
                System.out.println("ENTRO AL JSON NOMBRE : "+jsonObject.optString("nombre"));
                productoList.add(com);
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
        for (int i = 0; i < ventaList.size(); i++) {
            lables.add(ventaList.get(i).getDireccion());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_venta.setAdapter(spinnerAdapter);

        List<String> lables2 = new ArrayList<String>();
        for (int i = 0; i < productoList.size(); i++) {
            lables2.add(productoList.get(i).getNombre());
        }
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lables2);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_producto.setAdapter(spinnerAdapter);
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



