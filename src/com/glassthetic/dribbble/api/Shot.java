package com.glassthetic.dribbble.api;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class Shot implements Parcelable {

	static final String NAME = "shots";
	
	static final Type listType = new TypeToken<List<Shot>>() {}.getType();
	static final Type type = new TypeToken<Shot>() {}.getType();
	
	private static final String SHOTS_BASE_URL = "shots/";
	private static final String SHOTS_DEBUTS_URL = SHOTS_BASE_URL + "debuts/";
	private static final String SHOTS_EVERYONE_URL = SHOTS_BASE_URL + "everyone/";
	private static final String SHOTS_POPULAR_URL = SHOTS_BASE_URL + "popular/";
	private static final String SHOT_URL = SHOTS_BASE_URL + "%d/";
	private static final String SHOT_COMMENTS_URL = SHOT_URL + "comments/";
	private static final String SHOT_REBOUNDS_URL = SHOT_URL + "rebounds/";
	
	
	public int id;
    
	public String title;
    
	public String url;
    
    @SerializedName("short_url")
    public String shortUrl;
    
    @SerializedName("image_url")
    public String imageUrl;
    
    @SerializedName("image_teaser_url")
    public String imageTeaserUrl;
    
    public int width;
    
    public int height;
    
    @SerializedName("views_count")
    public int viewsCount;
    
    @SerializedName("likes_count")
    public int likesCount;
    
    @SerializedName("comments_count")
    public int commentsCount;
    
    @SerializedName("rebounds_count")
    public int reboundsCount;
    
    @SerializedName("rebound_source_id")
    public int reboundSourceId;
    
    @SerializedName("created_at")
    public String createdAt;
    
    public Player player;
    
    
    static void getShots(String url, final PaginatedListener<List<Shot>> listener, final ErrorListener errorListener) {
    	new Request<Shot>(url, NAME, listType, listener, errorListener);
    }
    
    
    public static void get(int id, final Listener<Shot> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, SHOT_URL, id);
    	new Request<Shot>(url, type, listener, errorListener);
    }
    
    public static void getDebuts(final PaginatedListener<List<Shot>> listener, final ErrorListener errorListener) {
    	getShots(SHOTS_DEBUTS_URL, listener, errorListener);
    }
    
    public static void getEveryone(final PaginatedListener<List<Shot>> listener, final ErrorListener errorListener) {
    	getShots(SHOTS_EVERYONE_URL, listener, errorListener);
    }
    
    public static void getPopular(final PaginatedListener<List<Shot>> listener, final ErrorListener errorListener) {
    	getShots(SHOTS_POPULAR_URL, listener, errorListener);
    }
    
    
    public void getComments(final PaginatedListener<List<Comment>> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, SHOT_COMMENTS_URL, this.id);
    	new Request<Comment>(url, Comment.NAME, Comment.listType, listener, errorListener);
    }
    
    public void getRebounds(final PaginatedListener<List<Shot>> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, SHOT_REBOUNDS_URL, this.id);
    	getShots(url, listener, errorListener);
    }
    
    
    public void getImage(final Listener<Bitmap> listener, final ErrorListener errorListener) {
    	ImageRequest request = new ImageRequest(this.imageUrl, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap) {
				listener.onResponse(bitmap);
			}
		}, 0, 0, null, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				errorListener.onErrorResponse(error);
			}
		});
		
		Request.queue.add(request);
    }

    
    public static final Parcelable.Creator<Shot> CREATOR = new Parcelable.Creator<Shot>() {

		@Override
		public Shot createFromParcel(Parcel source) {
			return new Shot(source);
		}

		@Override
		public Shot[] newArray(int size) {
			return new Shot[size];
		}
	};
	
	private Shot(Parcel source) {
		this.id = source.readInt();
		this.title = source.readString();
		this.url = source.readString();
		this.shortUrl = source.readString();
		this.imageUrl = source.readString();
		this.imageTeaserUrl = source.readString();
		this.width = source.readInt();
		this.height= source.readInt();
		this.viewsCount = source.readInt();
		this.likesCount = source.readInt();
		this.commentsCount = source.readInt();
		this.reboundsCount = source.readInt();
		this.reboundSourceId = source.readInt();
		this.createdAt = source.readString();
		this.player = source.readParcelable(Player.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(url);
		dest.writeString(shortUrl);
		dest.writeString(imageUrl);
		dest.writeString(imageTeaserUrl);
		dest.writeInt(width);
		dest.writeInt(height);
		dest.writeInt(viewsCount);
		dest.writeInt(likesCount);
		dest.writeInt(commentsCount);
		dest.writeInt(reboundsCount);
		dest.writeInt(reboundSourceId);
		dest.writeString(createdAt);
		dest.writeParcelable(player, 0);
	}
}
