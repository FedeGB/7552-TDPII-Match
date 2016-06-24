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
        HttpPutConnection httpConnection = new HttpPutConnection(this, "HttpUpdateSettingsRequest");

        this.requesterListener = requesterListener;

        httpConnection.addHeader("Token", SystemData.getInstance().getToken());
        httpConnection.setUri("users");
        JSONObject body = new JSONObject();
        try {
            body.put("username", SystemData.getInstance().getUserId());
            body.put("distance", distance);

            JSONObject range = new JSONObject();
            range.put("min", minAge);
            range.put("max", maxAge);

            body.put("ageRange", range);

            JSONObject location = new JSONObject();
            //location.put("latitude", SystemData.getInstance().getLatitude());
            //location.put("longitude", SystemData.getInstance().getLongitude());

            location.put("latitude", 0);
            location.put("longitude", 0);

            body.put("location", location);
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
