package com.example.tarea2_jsonwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea2_jsonwebservice.Interface.interfazRetrofit;
import com.example.tarea2_jsonwebservice.Model.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {
    private Button btnBancos;
    private ListView Listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnBancos=(Button) findViewById(R.id.btnBancos);
        Listado=(ListView) findViewById(R.id.lvListadoBancos);
        TextView Usuario = (TextView)findViewById(R.id.txtUsuario3);
        Bundle bundle = this.getIntent().getExtras();

        Usuario.setText(bundle.getString("Usuario"));

        btnBancos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtraerBancos();
            }
        });
    }

    private void ExtraerBancos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-uat.kushkipagos.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final interfazRetrofit interfaz=retrofit.create(interfazRetrofit.class);
        Call<List<Posts>> call=interfaz.getPost();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error Codigo: "+response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Posts> listaretrofit=response.body();
                String[] ListaBancos=new String[listaretrofit.size()-1];

                for (int i=0;i<listaretrofit.size();i++){
                    if(i>0) {
                        ListaBancos[i-1] = listaretrofit.get(i).getName();
                    }
                }
                Listado.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,ListaBancos));
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}