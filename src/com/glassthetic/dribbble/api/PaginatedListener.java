package com.glassthetic.dribbble.api;

import java.util.List;

public interface PaginatedListener<T> {
	public void onPaginatedResponse(List<T> list, Paginator<T> paginator);
}
