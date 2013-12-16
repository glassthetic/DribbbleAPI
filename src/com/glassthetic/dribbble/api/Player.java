package com.glassthetic.dribbble.api;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class Player implements Parcelable {

	static final String NAME = "players";
	
	static final Type listType = new TypeToken<List<Player>>() {}.getType();
	static final Type type = new TypeToken<Player>() {}.getType();
	
	private static final String PLAYERS_BASE_URL = "players/";
	private static final String PLAYER_URL = PLAYERS_BASE_URL + "%d/";
	private static final String PLAYER_DRAFTEES_URL = PLAYER_URL + "draftees/";
	private static final String PLAYER_FOLLOWERS_URL = PLAYER_URL + "followers/";
	private static final String PLAYER_FOLLOWING_URL = PLAYER_URL + "following/";
	private static final String PLAYER_SHOTS_URL = PLAYER_URL + "shots/";
	private static final String PLAYER_LIKES_URL = PLAYER_SHOTS_URL + "likes/";
	private static final String PLAYER_FOLLOWING_SHOTS_URL = PLAYER_SHOTS_URL + "following/";

	
	public int id;
    
	public String name;
	
    public String username;
    
    public String url;
    
    @SerializedName("avatar_url")
    public String avatarUrl;
    
    public String location;
    
    @SerializedName("twitter_screen_name")
    public String twitterScreenName;
    
    @SerializedName("drafted_by_player_id")
    public int draftedByPlayerId;
    
    @SerializedName("shots_count")
    public int shotsCount;
    
    @SerializedName("draftees_count")
    public int drafteesCount;
    
    @SerializedName("followers_count")
    public int followersCount;
    
    @SerializedName("following_count")
    public int followingCount;
    
    @SerializedName("comments_count")
    public int commentsCount;
    
    @SerializedName("comments_received_count")
    public int commentsReceivedCount;
    
    @SerializedName("likes_count")
    public int likesCount;
    
    @SerializedName("likes_received_count")
    public int likesReceivedCount;
    
    @SerializedName("rebounds_count")
    public int reboundsCount;
    
    @SerializedName("rebounds_received_count")
    public int reboundsReceivedCount;
    
    @SerializedName("created_at")
    public String createdAt;
    
    
    private static void getPlayers(String url, final PaginatedListener<Player> listener, final ErrorListener errorListener) {
    	new Request<Player>(url, NAME, listType, listener, errorListener);
    }
    
    
    public static void get(int id, final Listener<Player> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_URL, id);
    	new Request<Player>(url, type, listener, errorListener);
    }
    
    
    public void getDraftees(final PaginatedListener<Player> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_DRAFTEES_URL, this.id);
    	getPlayers(url, listener, errorListener);
    }
    
    public void getFollowers(final PaginatedListener<Player> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_FOLLOWERS_URL, this.id);
    	getPlayers(url, listener, errorListener);
    }
    
    public void getFollowing(final PaginatedListener<Player> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_FOLLOWING_URL, this.id);
    	getPlayers(url, listener, errorListener);
    }
    
    public void getLikes(final PaginatedListener<Shot> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_LIKES_URL, this.id);
    	Shot.getShots(url, listener, errorListener);
    }
    
    public void getShots(final PaginatedListener<Shot> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_SHOTS_URL, this.id);
    	Shot.getShots(url, listener, errorListener);
    }
    
    public void getFollowingShots(final PaginatedListener<Shot> listener, final ErrorListener errorListener) {
    	String url = String.format(Locale.US, PLAYER_FOLLOWING_SHOTS_URL, this.id);
    	Shot.getShots(url, listener, errorListener);
    }

    
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {

		@Override
		public Player createFromParcel(Parcel source) {
			return new Player(source);
		}

		@Override
		public Player[] newArray(int size) {
			return new Player[size];
		}
	};
    
	private Player(Parcel source) {
		this.id = source.readInt();
		this.name = source.readString();
		this.username = source.readString();
		this.url = source.readString();
		this.avatarUrl = source.readString();
		this.location = source.readString();
		this.twitterScreenName = source.readString();
		this.draftedByPlayerId = source.readInt();
		this.shotsCount = source.readInt();
		this.drafteesCount = source.readInt();
		this.followersCount = source.readInt();
		this.followingCount = source.readInt();
		this.commentsCount = source.readInt();
		this.commentsReceivedCount = source.readInt();
		this.likesCount = source.readInt();
		this.likesReceivedCount = source.readInt();
		this.reboundsCount = source.readInt();
		this.reboundsReceivedCount = source.readInt();
	    this.createdAt = source.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(username);
		dest.writeString(url);
		dest.writeString(avatarUrl);
		dest.writeString(location);
		dest.writeString(twitterScreenName);
		dest.writeInt(draftedByPlayerId);
		dest.writeInt(shotsCount);
		dest.writeInt(drafteesCount);
		dest.writeInt(followersCount);
		dest.writeInt(followingCount);
		dest.writeInt(commentsCount);
		dest.writeInt(commentsReceivedCount);
		dest.writeInt(likesCount);
		dest.writeInt(likesReceivedCount);
		dest.writeInt(reboundsCount);
		dest.writeInt(reboundsReceivedCount);
	    dest.writeString(createdAt);
	}
}
