package com.example.usuario.mitiendav1;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.usuario.mitiendav1.backend.Request.ClienteRequest;
import com.example.usuario.mitiendav1.backend.model.Cliente;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText etnombrecliente, ettelefonocliente, etcorreocliente, etusuarioclient, etclaveclient;
    Button btnRegistrarClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etnombrecliente = (EditText) findViewById(R.id.etnombrecliente);
        ettelefonocliente = (EditText) findViewById(R.id.ettelefonocliente);
        etcorreocliente = (EditText) findViewById(R.id.etcorreocliente);
        etusuarioclient = (EditText) findViewById(R.id.etusuarioclient);
        etclaveclient = (EditText) findViewById(R.id.etclaveclient);

        btnRegistrarClient = (Button) findViewById(R.id.btnRegistrarClient);
        btnRegistrarClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nombres=etnombrecliente.getText().toString();
                final String telefono=ettelefonocliente.getText().toString();
                final String correo=etcorreocliente.getText().toString();
                final String usuario=etusuarioclient.getText().toString();
                final String clave=etclaveclient.getText().toString();

                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success= jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("error registro")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                ClienteRequest clienteRequest = new ClienteRequest(nombres,telefono,correo,usuario,clave, respoListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(clienteRequest);

            }
        });



    }
}
