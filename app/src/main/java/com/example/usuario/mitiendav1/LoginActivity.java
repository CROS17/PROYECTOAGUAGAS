package com.example.usuario.mitiendav1;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.usuario.mitiendav1.backend.controller.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView tv_registrar;
    EditText et_usuario, et_clave;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_usuario=(EditText)findViewById(R.id.et_usuario);
        et_clave=(EditText)findViewById(R.id.et_clave);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        tv_registrar=(TextView) findViewById(R.id.tv_registrar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intentReg);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intentlog = new Intent(LoginActivity.this, MenuAdminActivity.class);
                LoginActivity.this.startActivity(intentlog);
                finish();*/
                    if(et_usuario.getText().toString().equals("1")){
                        Intent intentlog2 = new Intent(LoginActivity.this, MenuAdminActivity.class);
                        LoginActivity.this.startActivity(intentlog2);
                    }
                if(et_usuario.getText().toString().equals("2")){
                    Intent intentlog3 = new Intent(LoginActivity.this, MenuClienteActivity.class);
                    LoginActivity.this.startActivity(intentlog3);
                }

                /*final String username = et_usuario.getText().toString();
                final String clave = et_clave.getText().toString();

                Response.Listener<String>responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String name = jsonResponse.getString("name");
                                int edad = jsonResponse.getInt("edad");

                                Intent intentlog = new Intent(LoginActivity.this, MenuAdminActivity.class);
                                intentlog.putExtra("name",name);
                                intentlog.putExtra("username",username);
                                intentlog.putExtra("edad",edad);
                                intentlog.putExtra("clave",clave);

                                LoginActivity.this.startActivity(intentlog);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("error Acceso")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username,clave,responseListener);

                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);*/

            }
        });


    }
}
