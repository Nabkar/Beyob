package com.example.beyob;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.beyob.tipos.TiposGridFragment;

public class MainActivity extends AppCompatActivity implements NavigationHost {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.byb_main_activity);

		// Cargamos en el FragmentManager la primera vista
		if (savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.container, new TiposGridFragment())
					.commit();
		}
	}

	/**
	 * Navigate to the given fragment.
	 *
	 * @param fragment       Fragment to navigate to.
	 * @param addToBackstack Whether or not the current fragment should be added to the backstack.
	 */
	@Override
	public void navigateTo(Fragment fragment, boolean addToBackstack) {
		FragmentTransaction transaction =
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container, fragment);

		if (addToBackstack) {
			transaction.addToBackStack(null);
		}

		transaction.commit();
	}
}
