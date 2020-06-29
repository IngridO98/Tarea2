package com.example.tarea2_jsonwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar;
    EditText txtUsuario, txtContrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar=(Button) findViewById(R.id.btnIngresar);
        txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        txtContrasenia=(EditText) findViewById(R.id.txtContrasenia);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loguearse();
            }
        });
    }

    private void Loguearse(){


        String url="http://uealecpeterson.net/ws/login.php?usr="+ txtUsuario.getText()+ "&pass=" + txtContrasenia.getText();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Login Correcto!")){
                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    Bundle pasar= new Bundle();
                    //pasar.putString("Mensaje", "Login Exitoso!");
                    pasar.putString("Usuario", txtUsuario.getText().toString());
                    intent.putExtras(pasar);
                    //Iniciamos la nueva actividad
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error:" + error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               /*Map<String,String> parametros=new HashMap<>();
                parametros.put("usr", txtUsuario.getText().toString().trim());
                parametros.put("pass", txtContrasenia.getText().toString().trim());*/
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }

}