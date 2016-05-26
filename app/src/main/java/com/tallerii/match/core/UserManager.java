package com.tallerii.match.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Demian on 25/05/2016.
 */
public class UserManager {
    Map<String, UserProfile> userProfileCache = new HashMap<>();
    RequesterListener requesterListener;

    public void getUserProfile(String userId, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        if(userProfileCache.containsKey(userId)){
            requesterListener.proccesRequest(userProfileCache.get(userId), "PROFILE");
        }
    }
}
