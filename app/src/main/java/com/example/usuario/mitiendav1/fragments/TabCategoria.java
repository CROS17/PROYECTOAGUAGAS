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
import com.example.usuario.mitiendav1.fragments.adapters.CategoriaAdapter;


public class TabCategoria extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public  static int int_items=3;


    public TabCategoria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_categoria,null);
        tabLayout=(TabLayout)v.findViewById(R.id.tablcategoria);
        viewPager=(ViewPager)v.findViewById(R.id.viewpagercategoria);
        viewPager.setAdapter(new CategoriaAdapter( getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return v;
    }






}
