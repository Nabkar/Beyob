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

public class TiposEscalonadosCardRecyclerViewAdapter extends RecyclerView.Adapter<TiposEscalonadosCardViewHolder> {

    private List<TipoEntry> tiposList;
    private ImageRequester imageRequester;

    public TiposEscalonadosCardRecyclerViewAdapter(List<TipoEntry> tiposList) {
        this.tiposList = tiposList;
        imageRequester = ImageRequester.getInstance();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @NonNull
    @Override
    public TiposEscalonadosCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.byb_tipo_escalonado_card_first;
        /*if (viewType == 1) {
            layoutId = R.layout.byb_tipo_escalonado_card_second;
        } else if (viewType == 2) {
            layoutId = R.layout.byb_tipo_escalonado_card_third;
        }*/

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new TiposEscalonadosCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull TiposEscalonadosCardViewHolder holder, int position) {
        if (tiposList != null && position < tiposList.size()) {
            TipoEntry tipo = tiposList.get(position);
            holder.tipoTitle.setText(tipo.title);
            holder.tipoSubtitle.setText(tipo.subtitle);
            imageRequester.setImageFromUrl(holder.tipoImage, tipo.url);
        }
    }

    @Override
    public int getItemCount() {
        return tiposList.size();
    }
}
