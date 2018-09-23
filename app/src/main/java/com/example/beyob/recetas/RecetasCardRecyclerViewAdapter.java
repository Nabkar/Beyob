package com.example.beyob.recetas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beyob.MainActivity;
import com.example.beyob.R;
import com.example.beyob.network.GlideApp;
import com.example.beyob.network.TipoEntry;
import com.example.beyob.receta.RecetaViewActivity;
import com.example.beyob.receta.RecetaViewFragment;

import java.util.List;

public class RecetasCardRecyclerViewAdapter extends RecyclerView.Adapter<RecetasCardViewHolder>  {

    private List<TipoEntry> recetasList;
    private Context context;

    RecetasCardRecyclerViewAdapter(Context context, List<TipoEntry> tiposList) {
        this.recetasList = tiposList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecetasCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.byb_recetas_card, parent, false);
        return new RecetasCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetasCardViewHolder holder, int position) {
        if (recetasList != null && position < recetasList.size()) {
            final TipoEntry receta = recetasList.get(position);
            holder.title.setText(receta.title);
            holder.dificultad.setText(receta.subtitle);

            GlideApp.with(context)
                    .load(context.getResources().getIdentifier(receta.image,"drawable", context.getPackageName()))
                    .error(R.drawable.imagenotfound)
                    .centerCrop()
                    .into(holder.image);

            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //recetaListener(receta);
                    Intent i = new Intent(context, RecetaViewActivity.class);
                    i.putExtra("titulo", receta.title);
                    i.putExtra("imagen", receta.image);
                    context.startActivity(i);
                }
            });
        } else {
            holder.title.setText(context.getResources().getText(R.string.byb_lista_vacia));
            holder.dificultad.setText("");

            GlideApp.with(context)
                    .load(R.drawable.imagenotfound)
                    .error(R.drawable.imagenotfound)
                    .centerCrop()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return recetasList.size();
    }

//    private void recetaListener(TipoEntry receta){
//        if(context instanceof MainActivity){
//            //System.out.println("Click!!!!!!");
//            // Le pasamos al nuevo fragment los datos necesarios
//            Bundle datos = new Bundle();
//            datos.putString("tipo", receta.title);
//            RecetaViewFragment fragment = new RecetaViewFragment();
//            fragment.setArguments(datos);
//
//            // Lanzamos el fragment nuevo desde la actividad principal
//            ((MainActivity)context).navigateTo(fragment,true);
//        }
//    }
}
