package com.bkdev.translation.model.newsfeed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;


/**
 * Created by linh918 on 8/7/17.
 */
@Data
public class NewsFeedResponse {

    @SerializedName("code")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("results")
    private List<Instagram> newsfeedList;





}
