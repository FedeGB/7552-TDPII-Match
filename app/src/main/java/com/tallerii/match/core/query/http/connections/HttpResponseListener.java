package com.tallerii.match.core.query.http.connections;

import org.json.JSONObject;

/**
 * Created by Demian on 10/04/2016.
 */
public interface HttpResponseListener {
    void handleHttpResponse(JSONObject responseBody);
    void handleHttpError(int errorNumber, String errorMessage);
}
