package com.bkdev.translation.model.newsfeed;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by linh918 on 8/12/17.
 */

@Data
public class Newsfeed {
    @SerializedName("isCheckAdvertisement")
    int isCheckAdvertisement;
}


