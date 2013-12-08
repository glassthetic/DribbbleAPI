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

class Request {
	
	public static RequestQueue queue;
	private static final String BASE_URL = "http://api.dribbble.com/";
	
	public Request(String url, final Listener<JSONObject> listener, final ErrorListener errorListener) {
		String absoluteUrl = BASE_URL + url; 
		JSONObject jsonRequest = new JSONObject();
		
//		try {
//			jsonRequest.put("page", page);
//		} catch (JSONException jsonException) {
//			jsonException.printStackTrace();
//		}
		
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, absoluteUrl, jsonRequest, new Response.Listener<JSONObject>() {

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


class ListRequest<T> {

	public ListRequest(String url, final String name, final Type type, final Listener<List<T>> listener, final ErrorListener errorListener) {
		new Request(url, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Gson gson = new Gson();
				String jsonString = null;
				
				try {
					jsonString = response.getString(name);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<T> list = gson.fromJson(jsonString, type);
				listener.onResponse(list);
			}
			
		}, errorListener);
	}
	
}


class ResourceRequest<T> {

	public ResourceRequest(String url, final Type type, final Listener<T> listener, final ErrorListener errorListener) {
		new Request(url, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Gson gson = new Gson();
				String jsonString = response.toString();
				T resource = gson.fromJson(jsonString, type);
				listener.onResponse(resource);
			}
			
		}, errorListener);
	}
	
}