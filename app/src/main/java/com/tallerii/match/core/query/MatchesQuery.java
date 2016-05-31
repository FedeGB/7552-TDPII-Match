package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.HttpGetMatchesRequester;

/**
 * Created by Demian on 31/05/2016.
 */
public class MatchesQuery extends HttpQuery {
    public static final String QUERY_TAG = "MATCHES";
    HttpGetMatchesRequester httpGetMatchesRequester = new HttpGetMatchesRequester();
    QueryListener queryListener;

    public MatchesQuery(QueryListener queryListener){
        this.queryListener = queryListener;
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

    @Override
    public void execute() {
        httpGetMatchesRequester.requestUserMatches(this);
    }
}
