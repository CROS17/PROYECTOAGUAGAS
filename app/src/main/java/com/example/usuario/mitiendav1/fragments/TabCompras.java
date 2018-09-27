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
import com.example.usuario.mitiendav1.fragments.adapters.CompraAdapter;


public class TabCompras extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public  static int int_items=2;

    public TabCompras() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_tab_compras, container, false);
        tabLayout=(TabLayout)v.findViewById(R.id.tablcompras);
        viewPager=(ViewPager)v.findViewById(R.id.viewpagercompras);
        viewPager.setAdapter(new CompraAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return v;
    }


}
