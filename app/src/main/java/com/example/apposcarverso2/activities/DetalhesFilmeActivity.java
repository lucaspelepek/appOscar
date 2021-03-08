package com.example.apposcarverso2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.apposcarverso2.R;
import com.example.apposcarverso2.model.Filme;
import com.example.apposcarverso2.model.MyApp;
import com.example.apposcarverso2.model.Usuario;

public class DetalhesFilmeActivity extends AppCompatActivity {

    TextView textViewNome;
    TextView textViewGenero;
    ImageView imageView;
    Filme filme;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        MyApp myApp = (MyApp) getApplicationContext();
        usuario = myApp.getUsuario();

        Intent intentMain = getIntent();
        filme = intentMain.getParcelableExtra("filme");

        textViewNome = findViewById(R.id.textViewNomeFilmeDetalhe);
        textViewGenero = findViewById(R.id.textViewGeneroFilmeDetalhe);
        imageView = findViewById(R.id.imageViewFilmeDetalhe);

        textViewNome.setText(filme.getNome());
        textViewGenero.setText(filme.getGenero());

        Glide.with(this).load(filme.getPoster()).into(imageView);

        Button button = findViewById(R.id.buttonVotarFilme);
        if (!usuario.getPodeVotar()) {
            button.setVisibility(View.GONE);
        }

    }

    public void votar(View view) {
        usuario.setFilme(filme.getNome());

        finish();
    }

}