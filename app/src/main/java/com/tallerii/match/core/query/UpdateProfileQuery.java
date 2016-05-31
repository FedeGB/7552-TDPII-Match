package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.HttpEditProfileRequester;

public class UpdateProfileQuery extends HttpQuery {
    HttpEditProfileRequester httpEditProfileRequester = new HttpEditProfileRequester();
    public static final String QUERY_TAG = "UPDATEPROF";
    QueryListener queryListener;

    public UpdateProfileQuery(QueryListener queryListener) {
       this.queryListener = queryListener;
    }

    @Override
    public void execute() {
        httpEditProfileRequester.updateProfile(this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
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