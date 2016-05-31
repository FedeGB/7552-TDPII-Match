package com.tallerii.match.core;

import com.tallerii.match.core.query.http.HttpUserProfileRequester;
import com.tallerii.match.core.query.QueryListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Demian on 25/05/2016.
 */
public class UserProfileManager {
    Map<String, UserProfile> userProfileCache = new HashMap<>();

    public UserProfile getUserProfile(String profileId){
        return userProfileCache.get(profileId);
    }

    public void addToProfileList(String id, UserProfile userProfile){
        userProfileCache.put(id, userProfile);
    }

    public void addAllToProfileList(ArrayList<UserProfile> userProfiles){
        Iterator<UserProfile> userProfileIterator = userProfiles.iterator();

        while (userProfileIterator.hasNext()){
            UserProfile userProfile = userProfileIterator.next();
            addToProfileList(userProfile.getId(), userProfile);
        }
    }

    public boolean hasUserProfile(String id){
        return userProfileCache.containsKey(id);
    }
}
