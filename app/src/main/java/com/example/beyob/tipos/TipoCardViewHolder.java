package com.example.beyob.tipos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.beyob.R;

public class TipoCardViewHolder extends RecyclerView.ViewHolder {

	//TODO: Find and store views from itemView
	public NetworkImageView tipoImage;
	public TextView tipoTitle;
	public TextView tipoSubtitle;

	public TipoCardViewHolder(@NonNull View itemView) {
		super(itemView);
		tipoImage = itemView.findViewById(R.id.tipo_image);
		tipoTitle = itemView.findViewById(R.id.tipo_title);
		tipoSubtitle = itemView.findViewById(R.id.tipo_subtitle);
	}
}