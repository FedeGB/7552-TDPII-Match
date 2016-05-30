package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;

/**
 * Created by Demian on 26/05/2016.
 */
public class UserProfileQuery extends HttpQuery {
    String userId;
    QueryListener queryListener;

    public UserProfileQuery(QueryListener queryListener, String userId, ServerData serverData) {
        this.queryListener = queryListener;
        this.userId = userId;
    }

    @Override
    public void execute() {
        //dataFacade.userManager.getUserProfile(userId, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {

    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {

    }
}
