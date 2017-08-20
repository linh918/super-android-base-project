package com.bkdev.translation.model.newsfeed;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by linh918 on 8/12/17.
 */
@Data
public class Instagram extends Newsfeed {


    @SerializedName("data")
    InstagramData data;


    @Data
    class InstagramData{
        @SerializedName("id")
        int id;
        @SerializedName("user")
        User user;
        @SerializedName("images")
        Image image;
        @SerializedName("created_time")
        long createdTime;
        @SerializedName("caption")
        Caption caption;
        @SerializedName("user_has_liked")
        boolean userHasLiked;
        @SerializedName("type")
        String type;
        @SerializedName("location")
        Location location;

    }

    @Data
    class User{
        @SerializedName("id")
        int id;
        @SerializedName("profile_picture")
        String profilePicture;
        @SerializedName("username")
        String username;
    }

    @Data
    class Image{
        @SerializedName("standard_resolution")
        StandardResolution standardResolution;


    }

    @Data
    class StandardResolution{
        @SerializedName("width")
        int width;
        @SerializedName("height")
        int height;
        @SerializedName("url")
        String url;

    }

    @Data
    class Caption{
        @SerializedName("id")
         String id;
        @SerializedName("text")
         String text;
        @SerializedName("created_time")
         String createdTime;

    }


    @Data
    class  Location{
        @SerializedName("latitude")
        double latitude;
        @SerializedName("longitude")
        double longitude;
        @SerializedName("name")
        String name;
    }


}



