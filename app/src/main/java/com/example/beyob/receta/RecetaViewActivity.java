package com.example.beyob.receta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.beyob.R;

public class RecetaViewActivity extends AppCompatActivity {

	private ActionBar toolbar;
	private String titulo;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.titulo = getArguments() != null ? getArguments().getString("tipo") : "";

		setContentView(R.layout.byb_receta_view_fragment);

		toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		//toolbar.setTitle(titulo);
		loadFragment(new IngredientesFragment());
	}

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment fragment;
			switch (item.getItemId()) {
				case R.id.navigation_ingredientes:
					//toolbar.setTitle("Ingredientes");
					fragment = new IngredientesFragment();
					loadFragment(fragment);
					return true;
				case R.id.navigation_explicacion:
					//toolbar.setTitle("Receta");
					fragment = new ExplicacionFragment();
					loadFragment(fragment);
					return true;
			}
			return false;
		}
	};

	private void loadFragment(Fragment fragment) {
		// load fragment
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_container, fragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}