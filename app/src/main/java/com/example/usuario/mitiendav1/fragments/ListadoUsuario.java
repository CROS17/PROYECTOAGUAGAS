package com.example.usuario.mitiendav1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.mitiendav1.R;


public class ListadoUsuario extends Fragment {



    public ListadoUsuario() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ListadoUsuario newInstance(String param1, String param2) {
        ListadoUsuario fragment = new ListadoUsuario();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_listado_usuario, container, false);
        return vista;
    }


    public interface OnFragmentInteractionListener {
    }
}
