package com.example.beyob.tipos;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.example.beyob.NavigationIconClickListener;
import com.example.beyob.R;
import com.example.beyob.network.TipoEntry;

public class TiposGridFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.byb_tipos_grid_fragment, container, false);

		// Set up the toolbar
		setUpToolbar(view);

		// Set up de RecyclerView
		RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        TipoCardRecyclerViewAdapter adapter = new TipoCardRecyclerViewAdapter(getContext(),
                TipoEntry.initTipoEntryList(getResources(), "tipos", ""));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.byb_tipo_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.byb_tipo_grid_spacing_small);
        recyclerView.addItemDecoration(new TiposArreglarItems(largePadding, smallPadding));

		// Set cut corner background for API 23+
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			view.findViewById(R.id.tipos_grid).setBackground(getContext().getDrawable(R.drawable.byb_background_shape));
		}

		return view;
	}

	private void setUpToolbar(View view) {
		Toolbar toolbar = view.findViewById(R.id.app_bar);
		AppCompatActivity activity = (AppCompatActivity) getActivity();
		if (activity != null) {
			activity.setSupportActionBar(toolbar);
		}

		//toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(), view.findViewById(R.id.product_grid)));
		toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
				getContext(),
				view.findViewById(R.id.tipos_grid),
				new AccelerateDecelerateInterpolator()//,
				/*getContext().getResources().getDrawable(R.drawable.byb_branded_menu), // Menu open icon
				getContext().getResources().getDrawable(R.drawable.byb_close_menu)*/)); // Menu close icon
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
		menuInflater.inflate(R.menu.byb_menu_top, menu);
		super.onCreateOptionsMenu(menu, menuInflater);
	}
}
