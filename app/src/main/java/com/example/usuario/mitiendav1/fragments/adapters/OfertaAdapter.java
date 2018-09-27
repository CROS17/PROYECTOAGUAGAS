package com.example.usuario.mitiendav1.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.mitiendav1.fragments.ListadoUsuario;
import com.example.usuario.mitiendav1.fragments.RegistroUsuario;

import static com.example.usuario.mitiendav1.fragments.TabOferta.int_items;

public class OfertaAdapter extends FragmentPagerAdapter {


    public OfertaAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RegistroUsuario();
            case 1:
                return new ListadoUsuario();

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
                return "Registrar Oferta";
            case 1:
                return "Editar Oferta";


        }

        return null;
    }
}


