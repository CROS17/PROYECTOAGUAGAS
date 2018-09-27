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
import com.example.usuario.mitiendav1.backend.controller.RegistrarRequest;
import com.example.usuario.mitiendav1.backend.model.Cliente;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText etnombre, etusername, etclave, etedad;
    Button btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etnombre = (EditText) findViewById(R.id.etnombre);
        etusername = (EditText) findViewById(R.id.etusername);
        etclave = (EditText) findViewById(R.id.etclave);
        etedad = (EditText) findViewById(R.id.etedad);

        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name=etnombre.getText().toString();
                final String username=etusername.getText().toString();
                final String clave=etclave.getText().toString();
                final int edad= Integer.parseInt(etedad.getText().toString());

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

                RegistrarRequest registrarRequest = new RegistrarRequest(name,username,edad,clave, respoListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registrarRequest);

            }
        });



    }
}
