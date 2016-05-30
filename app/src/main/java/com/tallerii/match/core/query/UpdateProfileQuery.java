package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.http.HttpEditProfileRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class UpdateProfileQuery extends HttpQuery {
    HttpEditProfileRequester httpEditProfileRequester = new HttpEditProfileRequester();
    QueryListener queryListener;

    public UpdateProfileQuery(QueryListener queryListener, ServerData serverData) {
       this.queryListener = queryListener;
    }

    @Override
    public void execute() {
        httpEditProfileRequester.updateProfile(this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {

    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {

    }
}
