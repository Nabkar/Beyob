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
	public final String image;

	public TipoEntry(
			String title, String dynamicUrl, String url, String subtitle, String image) {
		this.title = title;
		this.dynamicUrl = Uri.parse(dynamicUrl);
		this.url = url;
		this.subtitle = subtitle;
		this.image = image;
	}

	/**
	 * Cargamos el JSON de tipos
	 */
	public static List<TipoEntry> initTipoEntryList(Resources resources, String tipoDatos, String tipoCoctel) {
		InputStream inputStream = null;
		switch(tipoDatos) {
			case "tipos":
				inputStream = resources.openRawResource(R.raw.tipos);
				break;
			case "recetas":
				if (tipoCoctel.equalsIgnoreCase("after dinners")){
					inputStream = resources.openRawResource(R.raw.recetas_after_dinners);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("cobblers")){
					inputStream = resources.openRawResource(R.raw.recetas_cobblers);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("coladas")){
					inputStream = resources.openRawResource(R.raw.recetas_coladas);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("collins")){
					inputStream = resources.openRawResource(R.raw.recetas_collins);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("coolers")){
					inputStream = resources.openRawResource(R.raw.recetas_cooler);
					break;
				}
				/*if (tipoCoctel.equalsIgnoreCase("crustas")){
					inputStream = resources.openRawResource(R.raw.recetas_crustas);
					break;
				}*/
				if (tipoCoctel.equalsIgnoreCase("cups")){
					inputStream = resources.openRawResource(R.raw.recetas_cups);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("daisies")){
					inputStream = resources.openRawResource(R.raw.recetas_daisy);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("eggnogs")){
					inputStream = resources.openRawResource(R.raw.recetas_eggnogs);
					break;
				}
				/*if (tipoCoctel.equalsIgnoreCase("fixies")){
					inputStream = resources.openRawResource(R.raw.recetas_fixies);
					break;
				}*/

				if (tipoCoctel.equalsIgnoreCase("fizzes")){
					inputStream = resources.openRawResource(R.raw.recetas_fizz);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("flips")){
					inputStream = resources.openRawResource(R.raw.recetas_flip);
					break;
				}
				/*if (tipoCoctel.equalsIgnoreCase("frappes")){
					inputStream = resources.openRawResource(R.raw.recetas_frappes);
					break;
				}*/
				if (tipoCoctel.equalsIgnoreCase("grogs")){
					inputStream = resources.openRawResource(R.raw.recetas_grog);
					break;
				}
				if (tipoCoctel.equalsIgnoreCase("highballs")){
					inputStream = resources.openRawResource(R.raw.recetas_highball);
					break;
				}
				/*if (tipoCoctel.equalsIgnoreCase("old fashioned")){
					inputStream = resources.openRawResource(R.raw.recetas_old_fashioned);
					break;
				}*/
				/*if (tipoCoctel.equalsIgnoreCase("ponche")){
					inputStream = resources.openRawResource(R.raw.recetas_ponche);
					break;
				}*/
				/*if (tipoCoctel.equalsIgnoreCase("pousse-café")){
					inputStream = resources.openRawResource(R.raw.recetas_pousse);
					break;
				}*/
				/*if (tipoCoctel.equalsIgnoreCase("puff")){
					inputStream = resources.openRawResource(R.raw.recetas_puff);
					break;
				}*/
			default:
		}
		if (inputStream != null) {
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
		} else {
			Gson gson = new Gson();
			Type ListType = new TypeToken<ArrayList<TipoEntry>>() {
			}.getType();
			return gson.fromJson("", ListType);
		}
	}
}