package com.example.beyob.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

public class BeyobApplication extends Application {
	private static BeyobApplication instance;
	private static Context appContext;

	public static BeyobApplication getInstance() {
		return instance;
	}

	public static Context getAppContext() {
		return appContext;
	}

	public void setAppContext(Context mAppContext) {
		appContext = mAppContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		this.setAppContext(getApplicationContext());
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
	}
}