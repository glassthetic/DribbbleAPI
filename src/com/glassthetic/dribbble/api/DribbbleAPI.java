package com.glassthetic.dribbble.api;

import android.content.Context;

import com.android.volley.toolbox.Volley;

public class DribbbleAPI {
	
	public static void init(Context context) {
		Request.queue = Volley.newRequestQueue(context.getApplicationContext());
	}
}
