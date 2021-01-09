package com.example.recycler2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.vh> {
    /*
        Atributos del adaptador.
        Como mínimo necesitamos una variable que tenga el contexto de la app y otra que tenga la
        lista de objetos a mostrar.
     */
    Context c;
    List<Monte> montes;

    // Constructor. Lo único que hacemos es inicializar el contexto y la lista de objetos
    public Adaptador(Context c, List<Monte> montes) {
        this.c = c;
        this.montes = montes;
    }

    /*
        Establecemos qué fichero xml tiene la intefaz gráfica de cada uno de los elementos
        de nuestro RecyclerView
    */
    @NonNull
    @Override
    public vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.rv_row, parent, false);
        return new vh(v);
    }

    /*
        Para cada uno de los objetos establece sus datos, en nuestro caso establece el nombre,
        contintente, altura e imagen. También establecemos los onclick necesarios. En nuestro caso
        el onclick para borrar elemento y el onclick para visitar la página de la Wikipedia de la
        montaña
     */
    @Override
    public void onBindViewHolder(@NonNull vh holder, final int position) {
        holder.nombre.setText(montes.get(position).nombre);
        holder.continente.setText("Continente: "+montes.get(position).continente);
        holder.altura.setText("Altura: "+Integer.toString(montes.get(position).altura));
        holder.foto.setImageResource(montes.get(position).foto);

        final Monte m = montes.get(position);

        // onclick para ir a la página de la Wikipedia
        holder.masInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, m.masInfo);
                c.startActivity(launchBrowser);
            }
        });

        // onclick para borrar el monte
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, montes.size());
            }
        });
    }

    // Devolver el número de elementos que componen nuestra RecyclerView
    @Override
    public int getItemCount() {
        return montes.size();
    }

    /*
        Clase encargada de enlazar los elementos de la interfaz gráfica con las variables del
        adaptador
    */
    public class vh extends RecyclerView.ViewHolder {
        TextView nombre, altura, continente, masInfo;
        ImageView foto;
        ImageButton borrar;

        public vh(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.row_name);
            continente = (TextView) itemView.findViewById(R.id.row_continent);
            altura = (TextView) itemView.findViewById(R.id.row_h);
            masInfo = (TextView) itemView.findViewById(R.id.row_info);
            foto = (ImageView) itemView.findViewById(R.id.row_img);
            borrar = (ImageButton) itemView.findViewById(R.id.row_delete);
        }
    }
}
