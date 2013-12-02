package com.glassthetic.dribbble.api;

public class Response<T> {

	public interface Listener<T> {
		public void onResponse(T response);
	}
	
	public interface ErrorListener {
		public void onErrorResponse(Exception exception);
	}
}
