package com.glassthetic.dribbble.api;

public abstract class IndexedListener<T> implements Listener<T> {
	
	private int mIndex;
	
	public IndexedListener(int index) {
		mIndex = index;
	}
	
	public int getIndex() {
		return mIndex;
	}
}
