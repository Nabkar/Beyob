package com.example.beyob.recetas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beyob.R;

public class RecetasCardViewHolder extends RecyclerView.ViewHolder {

    //TODO: Find and store views from itemView
    public ImageView image;
    public TextView title;
    public TextView dificultad;

    public RecetasCardViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        title = itemView.findViewById(R.id.title);
        dificultad = itemView.findViewById(R.id.dificultad);
    }
}