package com.example.usuario.mitiendav1.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.mitiendav1.fragments.EditarProducto;
import com.example.usuario.mitiendav1.fragments.ListadoProducto;
import com.example.usuario.mitiendav1.fragments.ListadoUsuario;
import com.example.usuario.mitiendav1.fragments.RegistrarProducto;
import com.example.usuario.mitiendav1.fragments.RegistroUsuario;
import static com.example.usuario.mitiendav1.fragments.TabProducto.int_items;

public class ProductoAdapter extends FragmentPagerAdapter {


    public ProductoAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RegistrarProducto();
            case 1:
                return new ListadoProducto();
            case 2:
                return new EditarProducto();

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
                return "Registrar Producto";
            case 1:
                return "Listar Producto";
            case 2:
                return "Editar Producto";


        }

        return null;
    }
}


