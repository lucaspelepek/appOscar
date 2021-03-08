package com.example.apposcarverso2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Filme implements Parcelable {

    public static final Creator<Filme> CREATOR = new Creator<Filme>() {
        @Override
        public Filme createFromParcel(Parcel in) {
            return new Filme(in);
        }

        @Override
        public Filme[] newArray(int size) {
            return new Filme[size];
        }
    };
    private final int id;
    private final String nome;
    private final String genero;
    @SerializedName("foto")
    private final String poster;

    protected Filme(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        genero = in.readString();
        poster = in.readString();
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(genero);
        dest.writeString(poster);
    }
}
