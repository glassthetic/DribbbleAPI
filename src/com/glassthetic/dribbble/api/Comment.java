package com.glassthetic.dribbble.api;

import java.lang.reflect.Type;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class Comment implements Parcelable {
	
	static final String NAME = "comments";
	
	static final Type listType = new TypeToken<List<Comment>>() {}.getType();
	
	
	public int id;
    
	public String body;
    
	@SerializedName("likes_count")
    public int likesCount;
    
    @SerializedName("created_at")
    public String createdAt;
    
    public Player player;
    
    
    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {

		@Override
		public Comment createFromParcel(Parcel source) {
			return new Comment(source);
		}

		@Override
		public Comment[] newArray(int size) {
			return new Comment[size];
		}
	};
	
	private Comment(Parcel source) {
		this.id = source.readInt();
		this.body= source.readString();
		this.likesCount = source.readInt();
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
		dest.writeString(body);
		dest.writeInt(likesCount);
		dest.writeString(createdAt);
		dest.writeParcelable(player, 0);
	}
}
