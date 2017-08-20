package com.bkdev.translation.networks;

import com.bkdev.translation.api.Parameter;
import com.bkdev.translation.model.newsfeed.NewsFeedResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Api {
    @FormUrlEncoded
    @POST("/api/get-news-feed")
    Call<NewsFeedResponse> getNewsFeed(@Field("offset") int offset,
                                       @Field("limit") int limit);

}
