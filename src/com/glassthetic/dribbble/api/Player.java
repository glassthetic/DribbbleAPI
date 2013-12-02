package com.glassthetic.dribbble.api;

import com.google.gson.annotations.SerializedName;

public class Player {
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
    public short shotsCount;
    
    @SerializedName("draftees_count")
    public short drafteesCount;
    
    @SerializedName("followers_count")
    public short followersCount;
    
    @SerializedName("following_count")
    public short followingCount;
    
    @SerializedName("comments_count")
    public short commentsCount;
    
    @SerializedName("comments_received_count")
    public short commentsReceivedCount;
    
    @SerializedName("likes_count")
    public short likesCount;
    
    @SerializedName("likes_received_count")
    public short likesReceivedCount;
    
    @SerializedName("rebounds_count")
    public short reboundsCount;
    
    @SerializedName("rebounds_received_count")
    public short reboundsReceivedCount;
    
    @SerializedName("created_at")
    public String createdAt;
}
