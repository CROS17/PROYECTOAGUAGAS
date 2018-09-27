package com.example.usuario.mitiendav1.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.mitiendav1.fragments.EditarCategory;
import com.example.usuario.mitiendav1.fragments.ListadoCategory;
import com.example.usuario.mitiendav1.fragments.ListadoUsuario;
import com.example.usuario.mitiendav1.fragments.RegistrarCategory;
import com.example.usuario.mitiendav1.fragments.RegistroUsuario;

import static com.example.usuario.mitiendav1.fragments.TabCategoria.int_items;

public class CategoriaAdapter   extends FragmentPagerAdapter {


    public CategoriaAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RegistrarCategory();
            case 1:
                return new ListadoCategory();
            case 2:
                return new EditarCategory();

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
                return "Registrar Categoria";
            case 1:
                return "Listar  Categoria";
            case 2:
                return "Editar  Categoria";


        }

        return null;
    }
}

