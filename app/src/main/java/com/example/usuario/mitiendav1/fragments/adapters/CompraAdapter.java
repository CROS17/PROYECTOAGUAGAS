package com.example.usuario.mitiendav1.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.mitiendav1.fragments.EditarCategory;
import com.example.usuario.mitiendav1.fragments.ListadoCategory;
import com.example.usuario.mitiendav1.fragments.ListadoCompra;
import com.example.usuario.mitiendav1.fragments.RegistrarCategory;
import com.example.usuario.mitiendav1.fragments.RegistrarCompra;

import static com.example.usuario.mitiendav1.fragments.TabCompras.int_items;

public class CompraAdapter extends FragmentPagerAdapter {


    public CompraAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RegistrarCompra();
            case 1:
                return new ListadoCompra();

        }
        return null;
    }

    @Override
    public int getCount() {
        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Registrar Compras";
            case 1:
                return "Listar  Compras";


        }

        return null;
    }
}
