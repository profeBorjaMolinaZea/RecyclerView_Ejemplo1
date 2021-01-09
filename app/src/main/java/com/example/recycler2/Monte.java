package com.example.recycler2;

import android.net.Uri;

import java.net.URL;

public class Monte {
    String nombre, continente;
    int altura, foto;
    Uri masInfo;

    public Monte(String nombre, String continente, int altura, int foto, Uri masInfo) {
        this.nombre = nombre;
        this.continente = continente;
        this.altura = altura;
        this.foto = foto;
        this.masInfo = masInfo;
    }
}
