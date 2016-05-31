package com.tallerii.match.core;

import com.tallerii.match.core.query.http.HttpUserProfileRequester;
import com.tallerii.match.core.query.QueryListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Demian on 25/05/2016.
 */
public class UserProfileManager {
    Map<String, UserProfile> userProfileCache = new HashMap<>();
    QueryListener requesterListener;

    HttpUserProfileRequester userProfileRequester;

}
