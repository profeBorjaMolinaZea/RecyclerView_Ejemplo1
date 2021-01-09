package com.example.recycler2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    RecyclerView rv;
    RecyclerView.Adapter rva;
    RecyclerView.LayoutManager lm;
    List<Monte> montes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Leemos los datos
        readData();

        // Enlazamos la interfaz gráfica del RecyclerView con el código
        rv = findViewById(R.id.rv);
        // Establecemos que los elementos del RecyclerView se apilen verticalmente
        lm = new LinearLayoutManager(this);
        // Creamos un adaptador para el RecyclerView
        rva = new Adaptador(this, montes);
        // Enlazamos el adaptador con el objeto RecyclerView
        rv.setAdapter(rva);
        rv.setLayoutManager(lm);
    }

    // Añadimos una nueva montaña al RecyclerView
    public void addElemento(View v){
        // Creamos el objeto de la nueva montaña
        Monte m = new Monte("Mont blanc", "Europa", 4855,
                R.drawable.mont, Uri.parse("https://en.wikipedia.org/wiki/Mont_Blanc"));
        // Lo añadimos a la lista de montes
        montes.add(m);
        // Notificamos al adaptador que hemos insertado una nueva montaña
        rva.notifyItemInserted(montes.size());
    }

    // Leemos los datos de las montañas del fichero arrays.xml
    public void readData(){
        Resources res = getResources();
        CharSequence[] nombres = res.getStringArray(R.array.nombres);
        int[] alturas = res.getIntArray(R.array.alturas);
        CharSequence[] continentes = res.getStringArray(R.array.continentes);
        CharSequence[] URLS = res.getStringArray(R.array.URLS);
        TypedArray fotos = res.obtainTypedArray(R.array.fotos);

        int nMontes = nombres.length;
        for(int i=0; i<nMontes; i++){
            String nombre = nombres[i].toString();
            int altura = alturas[i];
            String continente = continentes[i].toString();
            Uri url = Uri.parse(URLS[i].toString());
            int foto = fotos.getResourceId(i, R.drawable.mul);
            Monte m = new Monte(nombre, continente, altura, foto, url);
            montes.add(m);
        }
    }

}