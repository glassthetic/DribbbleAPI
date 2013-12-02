package com.glassthetic.dribbble.api;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class DribbbleAPI {
	
	private static final String BASE_URL = "http://api.dribbble.com/";
	private static final String SHOTS_BASE_URL = BASE_URL + "shots/";
	private static final String DEBUT_SHOTS_URL = SHOTS_BASE_URL + "debuts";
	private static final String EVERYONE_SHOTS_URL = SHOTS_BASE_URL +  "everyone";
	private static final String POPULAR_SHOTS_URL = SHOTS_BASE_URL +  "popular";
	
	private static final byte FIRST_PAGE = 1;
	
	private RequestQueue requestQueue;
	
	
	public DribbbleAPI(Context context) {
		requestQueue = Volley.newRequestQueue(context);
	}

	
	public void getDebutShots(final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		getDebutShots(FIRST_PAGE, listener, errorListener);
	}
	
	public void getDebutShots(int page, final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		getShots(DEBUT_SHOTS_URL, page, listener, errorListener);
	}
	
	
	public void getEveryoneShots(final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		getEveryoneShots(FIRST_PAGE, listener, errorListener);
	}
	
	public void getEveryoneShots(int page, final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		getShots(EVERYONE_SHOTS_URL, page, listener, errorListener);
	}
	
	
	public void getPopularShots(final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		getPopularShots(FIRST_PAGE, listener, errorListener);
	}
	
	public void getPopularShots(int page, final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		getShots(POPULAR_SHOTS_URL, page, listener, errorListener);
	}
	
	
	private void getShots(String url, int page, final Listener<List<Shot>> listener, final ErrorListener errorListener) {
		JSONObject jsonRequest = new JSONObject();
		
		try {
			jsonRequest.put("page", page);
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}
		
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, jsonRequest, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Gson gson = new Gson();
				String shotsJson = null;
				
				try {
					shotsJson = response.getString("shots");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Type listType = new TypeToken<List<Shot>>() {}.getType();
				List<Shot> shots = gson.fromJson(shotsJson, listType);
				listener.onResponse(shots);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				
				errorListener.onErrorResponse(error);
			}
		});
		
		requestQueue.add(request);
	}
}
