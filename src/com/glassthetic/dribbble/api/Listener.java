package com.glassthetic.dribbble.api;

public interface Listener<T> {
	public void onResponse(T response);
}