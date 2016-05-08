package com.tallerii.match.core.http;

import com.tallerii.match.core.http.HttpConnection;

import java.io.InputStream;

/**
 * Created by Demian on 10/04/2016.
 */
public interface HttpResponseListener {
    void handleHttpResponse(InputStream response, HttpConnection connection);
    void httpRequestError(HttpConnection connection);
}
