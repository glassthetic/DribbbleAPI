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
import com.google.gson.reflect.TypeToken;

class Request<T> {
	
	public static RequestQueue queue;
	private static final String BASE_URL = "http://api.dribbble.com/";
	
	public static <E> void getList(String url, final String name, final Listener<List<E>> listener, final ErrorListener errorListener) {    			
    	new Request<List<E>>(url, new Listener<JSONObject>() {

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
				
				Type listType = new TypeToken<List<E>>() {}.getType();
				List<E> list = gson.fromJson(jsonString, listType);
				listener.onResponse(list);
			}
    		
		}, errorListener);
    }
	
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
