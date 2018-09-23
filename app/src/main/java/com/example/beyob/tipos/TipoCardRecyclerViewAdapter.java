package com.example.beyob.tipos;

import android.content.Context;
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
import com.example.beyob.recetas.RecetasGridFragment;

import java.util.List;

public class TipoCardRecyclerViewAdapter extends RecyclerView.Adapter<TipoCardViewHolder>  {

	private List<TipoEntry> tiposList;
	//private ImageRequester imageRequester;
	private Context context;

	TipoCardRecyclerViewAdapter(Context context, List<TipoEntry> tiposList) {
		this.tiposList = tiposList;
		//imageRequester = ImageRequester.getInstance();
		this.context = context;
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
			final TipoEntry tipo = tiposList.get(position);
			holder.tipoTitle.setText(tipo.title);
			holder.tipoSubtitle.setText(tipo.subtitle);

			GlideApp.with(context)
				.load(context.getResources().getIdentifier(tipo.image,"drawable", context.getPackageName()))
					.error(R.drawable.imagenotfound)
					.centerCrop()
				.into(holder.tipoImage);
//			if(tipo.image.isEmpty())
//				imageRequester.setImageFromUrl(holder.tipoImage, tipo.url);
//			else {
//				int id = context.getResources().getIdentifier(tipo.image, "drawable", context.getPackageName());
//				imageRequester.setImageFromResources(holder.tipoImage, id);
//			}
			holder.tipoImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(context instanceof MainActivity){
						// Le pasamos al nuevo fragment los datos necesarios
						Bundle datos = new Bundle();
						datos.putString("tipo",tipo.title);
						RecetasGridFragment fragment = new RecetasGridFragment();
						fragment.setArguments(datos);

						// Lanzamos el fragment nuevo desde la actividad principal
						((MainActivity)context).navigateTo(fragment,true);
					}
				}
			});

		}
	}

	@Override
	public int getItemCount() {
		return tiposList.size();
	}
}
