package com.tallerii.match.core.query;

/**
 * Created by Demian on 26/05/2016.
 */
public class ChatQuery extends HttpQuery {
    private QueryListener listener;

    public ChatQuery(QueryListener listener) {
        this.listener = listener;
    }

    @Override
    public void execute() {

    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        setAsFinished();
    }
}
