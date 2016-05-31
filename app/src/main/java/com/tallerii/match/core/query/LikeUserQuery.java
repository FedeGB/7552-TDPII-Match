package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.HttpLikeRequester;

public class LikeUserQuery extends HttpQuery {
    private HttpLikeRequester httpLikeRequester = new HttpLikeRequester();
    public static final String QUERY_TAG = "LIKE";

    private QueryListener listener;
    private String userId;
    private boolean like;

    public LikeUserQuery(QueryListener listener, String userId, boolean like) {
        this.listener = listener;
        this.userId = userId;
        this.like = like;
    }

    @Override
    public void execute() {
        httpLikeRequester.sendLikeToUser(userId, like, this);
    }


    @Override
    public void onSuccesRequest(Object returnedObject) {
        listener.onReturnedRequest(QUERY_TAG);
        listener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        listener.onFailRequest(errorMessage, QUERY_TAG);
        listener.afterRequest(QUERY_TAG);
        setAsFinished();
    }
}
