package com.example.apposcarverso2.model;

import android.app.Application;

public class MyApp extends Application {
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
