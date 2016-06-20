package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.HttpUpdateSettingsRequest;

/**
 * Created by demian on 16/06/16.
 */
public class ChangeSearchSettingsQuery extends HttpQuery {
    public static final String QUERY_TAG = "UPDATESEARCHSETTING";

    HttpUpdateSettingsRequest httpUpdateSettingsRequest = new HttpUpdateSettingsRequest();
    int distance;
    int minAge;
    int maxAge;
    QueryListener queryListener;

    public ChangeSearchSettingsQuery(int distance, int minRange, int maxRange, QueryListener queryListener) {
        this.distance = distance;
        this.maxAge = maxRange;
        this.minAge = minRange;
        this.queryListener = queryListener;
    }

    @Override
    public void execute() {
        httpUpdateSettingsRequest.changeSearchSettings(minAge, maxAge, distance, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        queryListener.onReturnedRequest(QUERY_TAG);
        queryListener.afterRequest(QUERY_TAG);
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        queryListener.onFailRequest(errorMessage, QUERY_TAG);
    }
}
