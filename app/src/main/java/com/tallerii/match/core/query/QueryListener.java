package com.tallerii.match.core.query;

/**
 * Created by Demian on 25/05/2016.
 */
public interface QueryListener {
    public void onReturnedRequest(Object returnedObject, String request);
    public void onFailRequest(String message, int code);
    public void afterRequest();
}
