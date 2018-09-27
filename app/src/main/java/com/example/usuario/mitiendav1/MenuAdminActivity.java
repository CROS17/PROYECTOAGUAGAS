package com.example.usuario.mitiendav1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.usuario.mitiendav1.fragments.EditadoUsuario;
import com.example.usuario.mitiendav1.fragments.EditarCategory;
import com.example.usuario.mitiendav1.fragments.EditarProducto;
import com.example.usuario.mitiendav1.fragments.ListadoCategory;
import com.example.usuario.mitiendav1.fragments.ListadoProducto;
import com.example.usuario.mitiendav1.fragments.ListadoUsuario;
import com.example.usuario.mitiendav1.fragments.RegistrarCategory;
import com.example.usuario.mitiendav1.fragments.RegistrarProducto;
import com.example.usuario.mitiendav1.fragments.RegistrarTrabajador;
import com.example.usuario.mitiendav1.fragments.RegistroUsuario;
import com.example.usuario.mitiendav1.fragments.TabCategoria;
import com.example.usuario.mitiendav1.fragments.TabCompras;
import com.example.usuario.mitiendav1.fragments.TabOferta;
import com.example.usuario.mitiendav1.fragments.TabPedidoCliente;
import com.example.usuario.mitiendav1.fragments.TabProducto;
import com.example.usuario.mitiendav1.fragments.TabUsuario;

public class MenuAdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        RegistroUsuario.OnFragmentInteractionListener,
        ListadoUsuario.OnFragmentInteractionListener,
        EditadoUsuario.OnFragmentInteractionListener,
        RegistrarCategory.OnFragmentInteractionListener,
        ListadoCategory.OnFragmentInteractionListener,
        EditarCategory.OnFragmentInteractionListener,
        RegistrarProducto.OnFragmentInteractionListener,
        ListadoProducto.OnFragmentInteractionListener,
        EditarProducto.OnFragmentInteractionListener,
        RegistrarTrabajador.OnFragmentInteractionListener {

private Button btnmapas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int controlsalida=0;
        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;
        if (id == R.id.nav_vistausuarios) {
            miFragment=new TabUsuario();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_categoria) {
            miFragment=new TabCategoria();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_producto) {
            miFragment=new TabProducto();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_oferta) {
            miFragment=new TabOferta();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_mispedidos) {
            miFragment=new TabPedidoCliente();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_miscompras) {
            miFragment=new TabCompras();
            fragmentSeleccionado=true;
        } else if(id == R.id.nav_login){
            Intent intentLog = new Intent(MenuAdminActivity.this, LoginActivity.class);
            MenuAdminActivity.this.startActivity(intentLog);
        }
        if (fragmentSeleccionado==true && controlsalida==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_menu_admin,miFragment).commit();
        }
        if (fragmentSeleccionado==true && controlsalida==1){
            // getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void callFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception ex) {
            Toast.makeText(this, "", Toast.LENGTH_LONG);
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
