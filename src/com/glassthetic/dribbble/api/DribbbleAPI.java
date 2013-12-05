package com.glassthetic.dribbble.api;

import android.content.Context;

import com.android.volley.toolbox.Volley;

public class DribbbleAPI {
	
	private static Context context;

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		DribbbleAPI.context = context;
		Request.queue = Volley.newRequestQueue(context.getApplicationContext());
	}
}
