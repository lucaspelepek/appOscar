package com.example.apposcarverso2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apposcarverso2.R;
import com.example.apposcarverso2.adapters.AdapterFilme;
import com.example.apposcarverso2.conexao.InterfaceAPI;
import com.example.apposcarverso2.dialogs.LoadingDialog;
import com.example.apposcarverso2.model.Filme;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmesActivity extends AppCompatActivity implements AdapterFilme.OnNoteListener {

    RecyclerView recyclerView;
    List<Filme> filmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);

        recyclerView = findViewById(R.id.recyclerView);
        filmeList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://wecodecorp.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceAPI interfaceAPI = retrofit.create(InterfaceAPI.class);

        Call<List<Filme>> call = interfaceAPI.getFilme();

        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {

                List<Filme> filmes = response.body();

                filmeList.addAll(filmes);

                PutDataIntoRecyclerView(filmeList);
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {

            }
        });

        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();

        Handler handler = new Handler();
        handler.postDelayed(() -> loadingDialog.dissmissDialog(), 1000);

    }

    private void PutDataIntoRecyclerView(List<Filme> filmeList) {
        AdapterFilme adapterFilme = new AdapterFilme(this, filmeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterFilme);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
        intent.putExtra("filme", filmeList.get(position));
        startActivity(intent);
    }

}