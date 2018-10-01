package com.example.usuario.mitiendav1;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.usuario.mitiendav1.fragments.DireccionFragment;

import com.example.usuario.mitiendav1.backend.model.Producto;

import com.example.usuario.mitiendav1.fragments.EditadoUsuario;
import com.example.usuario.mitiendav1.fragments.ListadoUsuario;
import com.example.usuario.mitiendav1.fragments.RegistrarProducto;
import com.example.usuario.mitiendav1.fragments.RegistroUsuario;
import com.example.usuario.mitiendav1.fragments.TabCategoria;
import com.example.usuario.mitiendav1.fragments.TabOferta;
import com.example.usuario.mitiendav1.fragments.TabPedidoCliente;
import com.example.usuario.mitiendav1.fragments.TabProducto;
import com.example.usuario.mitiendav1.fragments.TabTienda;
import com.example.usuario.mitiendav1.fragments.TabUsuario;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;

public class MenuClienteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        RegistroUsuario.OnFragmentInteractionListener,
        ListadoUsuario.OnFragmentInteractionListener,
        EditadoUsuario.OnFragmentInteractionListener,
        DireccionFragment.OnFragmentInteractionListener,
        RegistrarProducto.OnFragmentInteractionListener {

    RecyclerView recycler;
    RecyclerView.Adapter adap;
    ArrayList<Producto> listaCard;

    TextView tvnombreclient,tvcorreoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);
        recycler=(RecyclerView)findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcliente);
        setSupportActionBar(toolbar);


        tvnombreclient=(TextView)findViewById( R.id.tvnombreclient );
        tvnombreclient=(TextView)findViewById( R.id.tvcorreoc );

        Intent intent = getIntent();
        String nombres=intent.getStringExtra( "nombre" );
        String correo=intent.getStringExtra( "correo" );

        tvnombreclient.setText( nombres );
        tvcorreoc.setText( correo );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabcliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawercliente_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navcliente_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawercliente_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settingscliente) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int controlsalida=0;
        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;
        if (id == R.id.nav_tienda) {
            miFragment=new TabTienda();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_pedidocliente) {
            miFragment=new TabPedidoCliente();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_share2) {
            if(isServicesOK()){
            miFragment=new DireccionFragment();
            fragmentSeleccionado=true;
            }
        } else if(id == R.id.nav_login2){

        }
        if (fragmentSeleccionado==true && controlsalida==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_menu_cliente,miFragment).commit();
        }
        if (fragmentSeleccionado==true && controlsalida==1){
            // getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawercliente_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean isServicesOK(){
         String TAG = "MenuClienteActivity";
         int ERROR_DIALOG_REQUEST = 9001;
         Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "Verificque conexion no puedes hacer solicitudes de mapas", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    void callFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_menu_cliente, fragment).commit();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawercliente_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception ex) {
            Toast.makeText(this, "", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void listarProductos(){

    }



}
