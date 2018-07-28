package com.example.beyob.tipos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beyob.R;
import com.example.beyob.network.ImageRequester;
import com.example.beyob.network.TipoEntry;

import java.util.List;

public class TipoCardRecyclerViewAdapter extends RecyclerView.Adapter<TipoCardViewHolder> {

	private List<TipoEntry> tiposList;
	private ImageRequester imageRequester;

	TipoCardRecyclerViewAdapter(List<TipoEntry> productList) {
		this.tiposList = productList;
		imageRequester = ImageRequester.getInstance();
	}

	@NonNull
	@Override
	public TipoCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.byb_tipo_card, parent, false);
		return new TipoCardViewHolder(layoutView);
	}

	@Override
	public void onBindViewHolder(@NonNull TipoCardViewHolder holder, int position) {
		if (tiposList != null && position < tiposList.size()) {
			TipoEntry tipo = tiposList.get(position);
			holder.tipoTitle.setText(tipo.title);
			holder.tipoPrice.setText(tipo.price);
			imageRequester.setImageFromUrl(holder.tipoImage, tipo.url);
		}
	}

	@Override
	public int getItemCount() {
		return tiposList.size();
	}
}
