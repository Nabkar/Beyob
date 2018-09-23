package com.example.beyob.receta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.beyob.BottomNavigationBehavior;
import com.example.beyob.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class RecetaViewActivity extends AppCompatActivity {

	private ActionBar toolbar;
	private String titulo;
	private String imagen;

	private ArrayList<HashMap<String, String>> receta;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.titulo = getIntent().getStringExtra("titulo");
		this.imagen = getIntent().getStringExtra("imagen");

		setContentView(R.layout.byb_receta_view_fragment);

		toolbar = getSupportActionBar();

		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
		layoutParams.setBehavior(new BottomNavigationBehavior());

		receta = leerRecetas();
		//toolbar.setTitle(titulo);
		Fragment fragment = new IngredientesFragment();
		Bundle datos = new Bundle();
		datos.putString("titulo",titulo);
		datos.putString("imagen",imagen);
		datos.putSerializable("receta", receta);
		fragment.setArguments(datos);

		loadFragment(fragment);
	}

	@Override
	public void onResume(){
		super.onResume();
		receta = leerRecetas();
	}

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment fragment;
			Bundle datos;
			switch (item.getItemId()) {
				case R.id.navigation_ingredientes:
					receta = leerRecetas();
					datos = new Bundle();
					datos.putString("titulo",titulo);
					datos.putString("imagen",imagen);
					datos.putSerializable("receta", receta);
					fragment = new IngredientesFragment();
					fragment.setArguments(datos);
					loadFragment(fragment);
					return true;
				case R.id.navigation_utiles:
					receta = leerRecetas();
					datos = new Bundle();
					datos.putString("titulo",titulo);
					datos.putString("imagen",imagen);
					datos.putSerializable("receta", receta);
					fragment = new UtilesFragment();
					fragment.setArguments(datos);
					loadFragment(fragment);
					return true;
				case R.id.navigation_explicacion:
					//toolbar.setTitle("Receta");
					receta = leerRecetas();
					datos = new Bundle();
					datos.putString("titulo",titulo);
					datos.putString("imagen",imagen);
					datos.putSerializable("receta", receta);
					fragment = new ExplicacionFragment();
					fragment.setArguments(datos);
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
		//transaction.addToBackStack(null);
		transaction.commit();
	}

	private ArrayList<HashMap<String,String>> leerRecetas() {
		String json = null;
		ArrayList<HashMap<String,String>> lista = new ArrayList<>();
		boolean lee = true;
		try {
			try {
				InputStream is = getResources().openRawResource(R.raw.recetas);
				BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
				while(lee) {
					json = buffer.readLine();
					json = json.replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
					if(json.contains("title")){
						String clave = json.split(": " )[0].replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
						String valor = json.split(": " )[1].replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
						if (valor.toLowerCase().equals(this.titulo.toLowerCase())){
							while(lee) {
								json = buffer.readLine();
								json = json.replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
								if(!json.contains("title") && !json.contains("}")) {
									HashMap<String, String> par = new HashMap<>();
									String c = json.split(": ")[0];
									String v = "";
									if(json.split(": ").length==2) {
										v = json.split(": ")[1];
									}
									par.put(c,v);
									lista.add(par);
								} else {
									lee = false;
								}
							}
						}
					}
				}
			} catch (IOException ex) {
				Log.v("RecetaViewActivity", "Error: " + ex.getMessage());
				ex.printStackTrace();
				return new ArrayList<HashMap<String, String>>();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public ArrayList<HashMap<String,String>> loadJSONFromAsset() {
		String json = null;
		ArrayList<HashMap<String,String>> lista = new ArrayList<>();
		boolean lee = true;
		try {
			InputStream is = getResources().openRawResource(R.raw.recetas);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			while(lee) {
				json = buffer.readLine();
				json = json.replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
				if(json.contains("title")){
					String clave = json.split(": " )[0].replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
					String valor = json.split(": " )[1].replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
					if (valor.equals(this.titulo)){
						while(lee) {
							json = buffer.readLine();
							json = json.replaceAll("\t","").replaceAll(",","").replaceAll("\"","");
							if(!json.contains("title") && !json.contains("}")) {
								HashMap<String, String> par = new HashMap<>();
								String c = json.split(": ")[0];
								String v = "";
								if(json.split(": ").length==2) {
									v = json.split(": ")[1];
								}
								par.put(c,v);
								lista.add(par);
							} else {
								lee = false;
							}
						}
					}
				}
			}
//			if(json.equalsIgnoreCase("titulo"))
//			json = new String(buffer, "UTF-8");
//			Log.v("MainActivity", "Load json ok");
		} catch (IOException ex) {
			Log.v("RecetaViewActivity", "Error: " + ex.getMessage());
			ex.printStackTrace();
			return new ArrayList<HashMap<String, String>>();
		}
		return lista;
	}
}