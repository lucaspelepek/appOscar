package com.example.apposcarverso2.conexao;

import com.example.apposcarverso2.model.Diretor;
import com.example.apposcarverso2.model.Filme;
import com.example.apposcarverso2.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @POST("login.php")
    Call<Usuario> checkLogin(@Header("Authorization") String authToken);

    @GET("ufpr/filme")
    Call<List<Filme>> getFilme();

    @GET("ufpr/diretor")
    Call<List<Diretor>> getDiretor();

    @POST("registro.php")
    @FormUrlEncoded
    Call<Usuario> registraVoto(@Field("nome") String nome,
                               @Field("filme") String filme,
                               @Field("diretor") String diretor,
                               @Field("tokenDigitado") int token);

}
