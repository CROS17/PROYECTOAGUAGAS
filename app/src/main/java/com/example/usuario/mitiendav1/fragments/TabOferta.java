package com.example.usuario.mitiendav1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.mitiendav1.R;
import com.example.usuario.mitiendav1.fragments.adapters.OfertaAdapter;


public class TabOferta extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public  static int int_items=3;
    public TabOferta() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_oferta,null);
        tabLayout=(TabLayout)v.findViewById(R.id.tabloferta);
        viewPager=(ViewPager)v.findViewById(R.id.viewpageroferta);
        viewPager.setAdapter(new OfertaAdapter( getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return v;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
