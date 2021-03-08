package com.example.apposcarverso2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apposcarverso2.R;
import com.example.apposcarverso2.model.MyApp;
import com.example.apposcarverso2.model.Usuario;

public class MenuActivity extends AppCompatActivity {

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MyApp myApp = (MyApp) getApplicationContext();
        usuario = myApp.getUsuario();

        TextView textViewToken = findViewById(R.id.textViewToken);
        textViewToken.setText(String.valueOf(usuario.getToken()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.votarFilme:
                Intent intentFilm = new Intent(getApplicationContext(), FilmesActivity.class);
                startActivity(intentFilm);
                break;

            case R.id.votarDiretor:
                Intent intentDiretor = new Intent(getApplicationContext(), DiretorActivity.class);
                startActivity(intentDiretor);
                break;

            case R.id.confirmarVoto:
                Intent intentConfirmarVoto = new Intent(getApplicationContext(), ConfirmarVotoActivity.class);
                startActivity(intentConfirmarVoto);
                break;

            case R.id.sair:
                finish();
                System.exit(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}