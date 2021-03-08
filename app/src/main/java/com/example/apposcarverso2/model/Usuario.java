package com.example.apposcarverso2.model;

import androidx.annotation.NonNull;

public class Usuario {

    private String nome;
    private int token;
    private String filme;
    private String diretor;
    private boolean podeVotar;

    public boolean getPodeVotar() {
        return podeVotar;
    }

    public void setPodeVotar(boolean podeVotar) {
        this.podeVotar = podeVotar;
    }

    public String getNome() {
        return nome;
    }

    public int getToken() {
        return token;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @NonNull
    @Override
    public String toString() {
        return "POST{" +
                "nome='" + nome + '\'' +
                ", filme='" + token + '\'' +
                ", diretor='" + token + '\'' +
                ", token=" + token +
                "}";
    }
}
