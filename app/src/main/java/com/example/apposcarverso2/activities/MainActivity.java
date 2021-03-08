package com.example.apposcarverso2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apposcarverso2.R;
import com.example.apposcarverso2.conexao.InterfaceAPI;
import com.example.apposcarverso2.conexao.RetrofitClientInstance;
import com.example.apposcarverso2.model.MyApp;
import com.example.apposcarverso2.model.Usuario;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private EditText userTxt, passTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userTxt = findViewById(R.id.editTextUsuario);
        passTxt = findViewById(R.id.editTextPassword);

    }

    public void login(View view) {
        String user = userTxt.getText().toString();
        String password = passTxt.getText().toString();

        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Insira um usuario e senha!", Toast.LENGTH_SHORT).show();
            return;
        }

        String authToken = createAuthToken(user, password);
        checkLoginDetails(authToken);
    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        Call<Usuario> call = api.checkLogin(authToken);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();

                if (response.isSuccessful()) {
                    if (!usuario.getNome().equals("ERROR")) {
                        //Toast.makeText(MainActivity.this, "Sucesso", Toast.LENGTH_LONG).show();

                        usuario.setPodeVotar(usuario.getFilme() == null);

                        MyApp myApp = (MyApp) getApplicationContext();
                        myApp.setUsuario(usuario);

                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("TAG", t.toString());
                t.printStackTrace();
            }
        });

    }

    private String createAuthToken(String user, String password) {
        byte[] data = new byte[0];
        data = (user + ":" + password).getBytes(StandardCharsets.UTF_8);

        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }


}