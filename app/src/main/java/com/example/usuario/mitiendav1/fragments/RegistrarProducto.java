package com.example.usuario.mitiendav1.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuario.mitiendav1.R;
import com.example.usuario.mitiendav1.backend.controller.ProductoRequest;
import com.example.usuario.mitiendav1.backend.model.Categoria;
import com.example.usuario.mitiendav1.backend.model.Producto;
import com.example.usuario.mitiendav1.backend.util.DireccionHost;
import com.example.usuario.mitiendav1.backend.util.RequestHandler;
import com.example.usuario.mitiendav1.backend.util.Rutas;
import com.example.usuario.mitiendav1.backend.util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegistrarProducto extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener,
        AdapterView.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //VARIABLES
    public static int aa,m,dd;
    private EditText f;
    private Spinner spinner_categorias;
    EditText etnombre,etprecioventa,etpreciocompra,etstock,etfechavenc;
    private Button btnRegistrarproduct,btnfecha;
    private ProgressDialog progressDialog;
    ProgressDialog pDialog;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private ArrayList<Categoria> categoriaList;
    private RegistrarCategory.OnFragmentInteractionListener mListener;

    public RegistrarProducto() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistrarProducto newInstance(String param1, String param2) {
        RegistrarProducto fragment = new RegistrarProducto();
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
        etnombre.setText("");
        etprecioventa.setText("");
        etpreciocompra.setText("");
        etprecioventa.setText("");
        etstock.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_registrar_producto, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere por pavor...");
        spinner_categorias = (Spinner)vista.findViewById(R.id.spinner_categorias);
        etnombre = (EditText)vista.findViewById(R.id.etnombre);
        etprecioventa = (EditText)vista.findViewById(R.id.etprecioventa);
        etpreciocompra = (EditText)vista.findViewById(R.id.etpreciocompra);
        etstock = (EditText)vista.findViewById(R.id.etstock);
        categoriaList = new ArrayList<Categoria>();
        cargarCategorias();
        Button btnRegistrarproduct = (Button)vista.findViewById(R.id.btnRegistrarproduct);

        btnRegistrarproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(evaluar()){
                Producto produc = new Producto();
                produc.setNombre(etnombre.getText().toString());
                produc.setIdCategoria(obtenercodigo());
                produc.setPrecioVenta(Double.parseDouble(etprecioventa.getText().toString()));
                produc.setPrecioCompra(Double.parseDouble(etpreciocompra.getText().toString()));
                produc.setStock(Integer.parseInt(etstock.getText().toString()));
                    System.out.printf("PRDOCUTO :"+produc);
                registrarProducto(produc);
                }
            }
        });


        return vista;


    }
    public int obtenercodigo(){
        int num,cont;char a;String sid="";
        String sacarid=spinner_categorias.getSelectedItem().toString();
        cont=0;
        for(int i=0;i<sacarid.length();i++){
            a=sacarid.charAt(i);
            if(a=='-'){
                cont++;
            }
            if(cont==0){
                sid=sid+a;
            }
        }
        num=Integer.parseInt(sid);
        return num;
    }

    private void registrarProducto(final Producto producto){
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Rutas.URL_REGISTRAR_PRODUCTO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.has("error") && !obj.getBoolean("error")){
                                if(obj.has("res") && (obj.getInt("res")==1 || obj.getInt("res")==0) )

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
                                        "Error de Registro",
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
                params.put("iduser","1");
                params.put("iidcat",String.valueOf(producto.getIdCategoria()));
                params.put("nombre",producto.getNombre());
                params.put("pv",String.valueOf(producto.getPrecioVenta()));
                params.put("pc",String.valueOf(producto.getPrecioCompra()));
                params.put("stock",String.valueOf(producto.getStock()));
                params.put("img","prueba");
                return params;
            }

        };

        RequestHandler.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    public boolean evaluar(){
        if(etnombre.getText().toString().length()==0 || etprecioventa.getText().toString().length()==0
                || etpreciocompra.getText().toString().length()==0 || etstock.getText().toString().length()==0){
            if(etnombre.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR UN NOMBRE",Toast.LENGTH_SHORT).show();
            }
            else if(etprecioventa.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR UN PRECIO DE VENTA",Toast.LENGTH_SHORT).show();
            }
            else if(etpreciocompra.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR UN PRECIO DE COMPRA",Toast.LENGTH_SHORT).show();
            }
            else if(etstock.getText().toString().length()==0){
                Toast.makeText(getContext(),"DEBE INGRESAR STOCK",Toast.LENGTH_SHORT).show();
            }
            return false;
        }
        return true;
    }

    private void cargarCategorias() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();
        DireccionHost dh=new DireccionHost();
        String url=dh.getIp()+"listarcategoria.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,null,this,this);
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
        Categoria tipos=null;
        JSONArray json=response.optJSONArray("categoria");
        try {

            for (int i=0;i<json.length();i++){
                System.out.println("ENTRO AL JSON : ");
                tipos=new Categoria();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                tipos.setIdcategoria(jsonObject.optInt("idcategoria"));
                tipos.setNombre(jsonObject.optString("nombre"));
                System.out.println("ENTRO AL JSON NOMBRE : "+jsonObject.optString("nombre"));
                categoriaList.add(tipos);
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
        for (int i = 0; i < categoriaList.size(); i++) {
            lables.add(categoriaList.get(i).getIdcategoria()+"-"+categoriaList.get(i).getNombre());
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, lables);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_categorias.setAdapter(spinnerAdapter);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
