package com.example.usuario.mitiendav1.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.usuario.mitiendav1.fragments.EditadoUsuario;
import com.example.usuario.mitiendav1.fragments.ListadoUsuario;
import com.example.usuario.mitiendav1.fragments.RegistrarTrabajador;
import com.example.usuario.mitiendav1.fragments.RegistroUsuario;

import static com.example.usuario.mitiendav1.fragments.TabUsuario.int_items;
public class UsuarioAdapter  extends FragmentPagerAdapter {


    public UsuarioAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RegistrarTrabajador();
            case 1:
                return new ListadoUsuario();
            case 2:
                return new EditadoUsuario();

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
                return "Registrar";
            case 1:
                return "Listar";
            case 2:
                return "Editar";
        }

        return null;
    }
}

