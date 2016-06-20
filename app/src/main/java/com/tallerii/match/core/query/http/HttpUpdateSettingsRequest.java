package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpPutConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by demian on 16/06/16.
 */
public class HttpUpdateSettingsRequest implements HttpResponseListener {

    RequesterListener requesterListener;

    public void changeSearchSettings(int minAge,int maxAge, int distance, RequesterListener requesterListener) {
        HttpPutConnection httpConnection = new HttpPutConnection(this);

        this.requesterListener = requesterListener;

        httpConnection.addHeader("Authorization", SystemData.getInstance().getToken());
        httpConnection.setUri("users");
        JSONObject body = new JSONObject();
        try {

            body.put("distance", distance);

            JSONObject range = new JSONObject();
            range.put("minAge", minAge);
            range.put("maxAge", maxAge);

            body.put("range", range);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        httpConnection.addBody(body);
        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        requesterListener.onSuccesRequest(null);
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
