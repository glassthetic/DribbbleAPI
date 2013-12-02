package com.glassthetic.dribbble.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Shot implements Parcelable {
	
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
