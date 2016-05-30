package com.tallerii.match.core.query.http;

/**
 * Created by dlopez on 30/05/2016.
 */
public interface RequesterListener {
    public void onSuccesRequest(Object returnedObject);
    public void onFailRequest(int errorCode, String errorMessage);
}
