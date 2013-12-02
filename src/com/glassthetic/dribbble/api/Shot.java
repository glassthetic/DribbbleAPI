package com.glassthetic.dribbble.api;

import com.google.gson.annotations.SerializedName;

public class Shot {
	
	public int id;
    
	public String title;
    
	public String url;
    
    @SerializedName("short_url")
    public String shortUrl;
    
    @SerializedName("image_url")
    public String imageUrl;
    
    @SerializedName("image_teaser_url")
    public String imageTeaserUrl;
    
    public short width;
    
    public short height;
    
    @SerializedName("views_count")
    public short viewsCount;
    
    @SerializedName("likes_count")
    public short likesCount;
    
    @SerializedName("comments_count")
    public short commentsCount;
    
    @SerializedName("rebounds_count")
    public short reboundsCount;
    
    @SerializedName("rebound_source_id")
    public int reboundSourceId;
    
    @SerializedName("created_at")
    public String createdAt;
    
    public Player player;
}
