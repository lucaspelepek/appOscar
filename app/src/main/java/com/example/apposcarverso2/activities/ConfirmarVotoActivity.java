package com.example.apposcarverso2.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apposcarverso2.R;
import com.example.apposcarverso2.conexao.InterfaceAPI;
import com.example.apposcarverso2.conexao.RetrofitClientInstance;
import com.example.apposcarverso2.dialogs.AlertDialog;
import com.example.apposcarverso2.model.MyApp;
import com.example.apposcarverso2.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfirmarVotoActivity extends AppCompatActivity {

    Usuario usuario;
    EditText editTextNumberToken;
    AlertDialog alertDialog;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_voto);

        alertDialog = new AlertDialog();
        bundle = new Bundle();

        TextView textViewVotoFilme = findViewById(R.id.textViewVotoFilme);
        TextView textViewVotoDiretor = findViewById(R.id.textViewVotoDiretor);
        editTextNumberToken = findViewById(R.id.editTextNumberToken);
        Button buttonConfirmar = findViewById(R.id.buttonConfirmar);
        TextView textViewTokenConfirmarVoto = findViewById(R.id.textViewTokenConfirmarVoto);

        //pega usuario global
        MyApp myApp = (MyApp) getApplicationContext();
        usuario = myApp.getUsuario();

        //só substitui textView se usuario escolheu filme e/ou diretor
        if (usuario.getFilme() != null) {
            textViewVotoFilme.setText(usuario.getFilme());
        }

        if (usuario.getDiretor() != null) {
            textViewVotoDiretor.setText(usuario.getDiretor());
        }

        //se usuario já confirmou seus votos não poderá mais votar
        if (!usuario.getPodeVotar()) {
            textViewTokenConfirmarVoto.setVisibility(View.GONE);
            editTextNumberToken.setVisibility(View.GONE);
            buttonConfirmar.setVisibility(View.GONE);
        }

    }

    public void confirmarVoto(View view) {
        if (usuario.getFilme() == null || usuario.getDiretor() == null) {
            Toast.makeText(this, "Escolha um filme e diretor antes de confirmar seus votos", Toast.LENGTH_SHORT).show();
            return;
        }

        //Token foi digitado?
        if (TextUtils.isEmpty(editTextNumberToken.getText().toString())) {
            Toast.makeText(this, "Insira um token!", Toast.LENGTH_SHORT).show();
            return;
        }

        int inputToken = Integer.parseInt(editTextNumberToken.getText().toString());
        enviaParaServidor(inputToken);

    }

    private void enviaParaServidor(int inputToken) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);

        api.registraVoto(usuario.getNome(), usuario.getFilme(), usuario.getDiretor(), inputToken).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuarioResultado = response.body();

                if (response.isSuccessful()) {
                    if (usuarioResultado.getNome().matches("Votos_cadastrados")) {
                        executaAlertDialog("Votos cadastrados!");
                        usuario.setPodeVotar(false);

                    } else if (usuarioResultado.getNome().matches("Token invalido")) {
                        executaAlertDialog("Token invalido");

                    }
                } else {
                    executaAlertDialog("Erro no registro de votos");

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println("Unable to submit post to API.");
            }
        });


    }

    private void executaAlertDialog(String s) {
        bundle.putString("resultado", s);
        alertDialog.setArguments(bundle);
        alertDialog.show(getSupportFragmentManager(), "exemplo dialog");
    }
}