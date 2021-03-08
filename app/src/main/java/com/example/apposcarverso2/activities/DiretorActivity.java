package com.example.apposcarverso2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apposcarverso2.R;
import com.example.apposcarverso2.conexao.InterfaceAPI;
import com.example.apposcarverso2.model.Diretor;
import com.example.apposcarverso2.model.MyApp;
import com.example.apposcarverso2.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiretorActivity extends AppCompatActivity {

    List<Diretor> diretorList;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diretor);

        MyApp myApp = (MyApp) getApplicationContext();
        usuario = myApp.getUsuario();

        diretorList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wecodecorp.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);

        Call<List<Diretor>> call = interfaceAPI.getDiretor();

        call.enqueue(new Callback<List<Diretor>>() {
            @Override
            public void onResponse(Call<List<Diretor>> call, Response<List<Diretor>> response) {

                if (response.code() != 200) {
                    Toast.makeText(DiretorActivity.this, "Erro com os diretores!", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Diretor> diretores = response.body();

                diretorList.addAll(diretores);

                PutDataIntoRadioGroup(diretorList);
            }

            @Override
            public void onFailure(Call<List<Diretor>> call, Throwable t) {

            }
        });

        Button button = findViewById(R.id.buttonVotarDiretor);
        if (!usuario.getPodeVotar()) {
            button.setVisibility(View.GONE);
        }

    }

    private void PutDataIntoRadioGroup(List<Diretor> diretorList) {
        radioGroup = findViewById(R.id.radioGroupDiretor);

        for (Diretor diretor : diretorList) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(diretor.getNome());
            radioGroup.addView(radioButton);
        }
    }

    public void votarDiretor(View view) {
        int idButton = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(idButton);

        usuario.setDiretor(radioButton.getText().toString());

        finish();
    }

}