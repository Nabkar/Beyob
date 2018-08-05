package com.example.beyob.network;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.example.beyob.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TipoEntry {
	private static final String TAG = TipoEntry.class.getSimpleName();

	public final String title;
	public final Uri dynamicUrl;
	public final String url;
	public final String subtitle;

	public TipoEntry(
			String title, String dynamicUrl, String url, String subtitle) {
		this.title = title;
		this.dynamicUrl = Uri.parse(dynamicUrl);
		this.url = url;
		this.subtitle = subtitle;
	}

	/**
	 * Cargamos el JSON de tipos
	 */
	public static List<TipoEntry> initTipoEntryList(Resources resources) {
		InputStream inputStream = resources.openRawResource(R.raw.tipos);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			int pointer;
			while ((pointer = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, pointer);
			}
		} catch (IOException exception) {
			Log.e(TAG, "Error writing/reading from the JSON file.", exception);
		} finally {
			try {
				inputStream.close();
			} catch (IOException exception) {
				Log.e(TAG, "Error closing the input stream.", exception);
			}
		}
		String jsonString = writer.toString();
		Gson gson = new Gson();
		Type ListType = new TypeToken<ArrayList<TipoEntry>>() {
		}.getType();
		return gson.fromJson(jsonString, ListType);
	}
}