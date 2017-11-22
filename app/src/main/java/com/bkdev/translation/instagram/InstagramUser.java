package com.bkdev.translation.instagram;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by linh918 on 8/25/17.
 */
@Data
@EqualsAndHashCode
public class InstagramUser {
    String id;
    String username;
    String accessToken;
}
