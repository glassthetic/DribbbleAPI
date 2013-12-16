package com.glassthetic.dribbble.api;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

class Request<T> {
	
	public static RequestQueue queue;
	private static final String BASE_URL = "http://api.dribbble.com/";
	
	public Request(final String url, final Type type, final Listener<T> listener, final ErrorListener errorListener) {
		new JsonRequest(url, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Gson gson = new Gson();
				String jsonString = response.toString();
				T resource = gson.fromJson(jsonString, type);
				listener.onResponse(resource);
			}
			
		}, errorListener);
	}
	
	public Request(final String url, final String name, final Type type, final PaginatedListener<T> listener, final ErrorListener errorListener) {
		this(url, new JSONObject(), name, type, listener, errorListener);
	}
	
	public Request(final String url, final JSONObject params, final String name, final Type type, final PaginatedListener<T> listener, final ErrorListener errorListener) {
		new JsonRequest(url, params, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Gson gson = new Gson();
				String listJsonString = null;
				
				try {
					listJsonString = response.getString(name);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<T> list = gson.fromJson(listJsonString, type);				
				Paginator<T> paginator = new Paginator<T>(response, url, name, type, listener, errorListener);
				
				listener.onPaginatedResponse(list, paginator);
			}
			
		}, errorListener);
	}
	
	
	private class JsonRequest {
		
		public JsonRequest(String url, final Listener<JSONObject> listener, final ErrorListener errorListener) {
			this(url, new JSONObject(), listener, errorListener);
		}
		
		public JsonRequest(String url, JSONObject params, final Listener<JSONObject> listener, final ErrorListener errorListener) {
			String absoluteUrl = BASE_URL + url; 
			
			JsonObjectRequest request = new JsonObjectRequest(Method.GET, absoluteUrl, params, new Response.Listener<JSONObject>() {
	
				@Override
				public void onResponse(JSONObject response) {
					listener.onResponse(response);
				}
			}, new Response.ErrorListener() {
	
				@Override
				public void onErrorResponse(VolleyError error) {
					
					errorListener.onErrorResponse(error);
				}
			});
			
			queue.add(request);
		}
	}
}