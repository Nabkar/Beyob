package com.example.beyob.receta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.beyob.R;
import com.example.beyob.network.GlideApp;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExplicacionFragment extends Fragment {
    private String titulo;
    private String imagen;
    private ArrayList<HashMap<String, String>> receta;
    public ExplicacionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.titulo = getArguments() != null ? getArguments().getString("titulo") : "";
        this.imagen = getArguments() != null ? getArguments().getString("imagen") : "";
        this.receta = (ArrayList<HashMap<String, String>>) getArguments().getSerializable("receta");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.byb_explicacion_fragment, container, false);
        LinearLayout contenedor = view.findViewById(R.id.layoutLineas);

        TextView titulo = (TextView) view.findViewById(R.id.title);
        titulo.setText(this.titulo);

        ImageView image = view.findViewById(R.id.image);
        GlideApp.with(getContext())
                .load(getContext().getResources().getIdentifier(this.imagen,"drawable", getContext().getPackageName()))
                .error(R.drawable.imagenotfound)
                .centerCrop()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.zoom_imagen, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(getContext().getResources().getIdentifier(imagen,"drawable", getContext().getPackageName()));
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        int count = 0;
        for(HashMap<String, String> linea : receta) {
            if (linea.containsKey("paso")) {
                count++;
                TextView tv = new TextView(getContext());
                tv.setText(String.valueOf(count) + "- " + linea.get("paso"));
                tv.setTextSize(getResources().getDimension(R.dimen.byb_receta_view_text_size_12));
                ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(contenedor.getLayoutParams());
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                tv.setLayoutParams(lp);
                tv.setTextColor(getResources().getColor(R.color.textColorPrimary));
                contenedor.addView(tv);
            }
        }
        return view;
    }
}