package com.tallerii.match.core;

import com.tallerii.match.core.query.QueryListener;

/**
 * Created by dlopez on 01/06/2016.
 */
public class NullQueryListener implements QueryListener {
    @Override
    public void onReturnedRequest(String request) {

    }

    @Override
    public void onFailRequest(String message, String request) {

    }

    @Override
    public void afterRequest(String request) {

    }
}
