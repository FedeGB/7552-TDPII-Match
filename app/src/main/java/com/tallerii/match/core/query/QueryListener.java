package com.tallerii.match.core.query;

public interface QueryListener {
    void onReturnedRequest(String request);
    void onFailRequest(String message, String request);
    void afterRequest(String request);
}
