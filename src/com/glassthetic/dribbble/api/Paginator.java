package com.glassthetic.dribbble.api;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Paginator<T> implements Parcelable {

	private static final String CURRENT_PAGE_NUMBER_KEY = "page";
	private static final String NUMBER_OF_PAGES_KEY = "pages";
	private static final String NUMBER_OF_ITEMS_PER_PAGE_KEY = "per_page";
	private static final String TOTAL_NUMBER_OF_ITEMS_KEY = "total";
	
	private int mCurrentPageNumber;
	private int mNumberOfItemsPerPage;
	private int mNumberOfPages;
	private int mTotalNumberOfItems;
	
	private String mUrl;
	private String mName;
	private Type mType;
	private PaginatedListener<T> mListener;
	private ErrorListener mErrorListener;
	
	public Paginator(JSONObject response, String url, String name, Type type, PaginatedListener<T> listener, ErrorListener errorListener) {
		int currentPageNumber = 0;
		int numberOfPages = 0;
		int numberOfItemsPerPage = 0;
		int totalNumberOfItems = 0;
		
		try {		
			currentPageNumber = response.getInt(CURRENT_PAGE_NUMBER_KEY);
			numberOfPages = response.getInt(NUMBER_OF_PAGES_KEY);
			numberOfItemsPerPage = response.getInt(NUMBER_OF_ITEMS_PER_PAGE_KEY);
			totalNumberOfItems = response.getInt(TOTAL_NUMBER_OF_ITEMS_KEY);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mCurrentPageNumber = currentPageNumber;
		mNumberOfPages = numberOfPages;
		mNumberOfItemsPerPage = numberOfItemsPerPage;
		mTotalNumberOfItems = totalNumberOfItems;
		
		mUrl = url;
		mName = name;
		mType = type;
		mListener = listener;
		mErrorListener = errorListener;
	}
	
	private void changePage(int offset, PaginatedListener<T> listener, ErrorListener errorListener) {
		JSONObject params = new JSONObject();
		
		try {
			params.put(CURRENT_PAGE_NUMBER_KEY, mCurrentPageNumber);
			params.put(NUMBER_OF_PAGES_KEY, mNumberOfPages);
			params.put(NUMBER_OF_ITEMS_PER_PAGE_KEY, mNumberOfItemsPerPage + offset);
			params.put(TOTAL_NUMBER_OF_ITEMS_KEY, mTotalNumberOfItems);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Request<T>(mUrl, params, mName, mType, mListener, mErrorListener);
	}
	
	public void nextPage(PaginatedListener<T> listener, ErrorListener errorListener) {
		changePage(1, listener, errorListener);
	}
	
	public void previousPage(PaginatedListener<T> listener, ErrorListener errorListener) {
		changePage(-1, listener, errorListener);
	}
	
	
	public static final Parcelable.Creator<Paginator> CREATOR = new Parcelable.Creator<Paginator>() {

		@Override
		public Paginator createFromParcel(Parcel source) {
			return new Paginator(source);
		}

		@Override
		public Paginator[] newArray(int size) {
			return new Paginator[size];
		}
	};
	
	private Paginator(Parcel source) {
		this.mCurrentPageNumber = source.readInt();
		this.mNumberOfItemsPerPage = source.readInt();
		this.mNumberOfPages = source.readInt();
		this.mTotalNumberOfItems = source.readInt();
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mCurrentPageNumber);
		dest.writeInt(mNumberOfItemsPerPage);
		dest.writeInt(mNumberOfPages);
		dest.writeInt(mTotalNumberOfItems);
	}

}
