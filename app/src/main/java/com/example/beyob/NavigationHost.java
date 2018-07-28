package com.example.beyob;

import android.support.v4.app.Fragment;

public interface NavigationHost {
	void navigateTo(Fragment fragment, boolean addToBackstack);
}
