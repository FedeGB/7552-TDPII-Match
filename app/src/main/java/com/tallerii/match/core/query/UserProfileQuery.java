package com.tallerii.match.core.query;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;
import com.tallerii.match.core.query.http.HttpUserProfileRequester;

public class UserProfileQuery extends HttpQuery {
    HttpUserProfileRequester httpUserProfileRequester = new HttpUserProfileRequester();
    public static final String QUERY_TAG = "PROFILE";

    String userId;
    QueryListener queryListener;

    public UserProfileQuery(QueryListener queryListener, String userId) {
        this.queryListener = queryListener;
        this.userId = userId;
    }

    @Override
    public void execute() {
        httpUserProfileRequester.getSerializedUserProfile(userId, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        UserProfile userProfile = (UserProfile) returnedObject;
        SystemData.getInstance().getUserManager().addToProfileList(userProfile.getId(), userProfile);

        queryListener.onReturnedRequest(QUERY_TAG);
        queryListener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        queryListener.onFailRequest(errorMessage, QUERY_TAG);
        queryListener.afterRequest(QUERY_TAG);
        setAsFinished();
    }
}
