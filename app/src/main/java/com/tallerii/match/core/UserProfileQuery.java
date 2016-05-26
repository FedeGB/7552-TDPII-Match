package com.tallerii.match.core;

import com.tallerii.match.core.http.HttpUserProfileRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class UserProfileQuery extends HttpQuery implements RequesterListener {
    String userId;

    public UserProfileQuery(RequesterListener listener, String userId, DataFacade dataFacade) {
        super(listener, dataFacade);
        this.userId = userId;
    }

    @Override
    public void execute() {
        dataFacade.userManager.getUserProfile(userId, this);
    }
}
