package com.glassthetic.dribbble.api;

public interface PaginatedListener<T> {
	public void onPaginatedResponse(T list, Paginator paginator);
}
