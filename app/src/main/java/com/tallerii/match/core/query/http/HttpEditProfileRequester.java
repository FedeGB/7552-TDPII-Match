package com.tallerii.match.core.query.http;

import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;
import com.tallerii.match.core.query.http.connections.HttpPutConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Demian on 26/05/2016.
 */
public class HttpEditProfileRequester implements HttpResponseListener {

    RequesterListener requesterListener;

    public void updateProfile(RequesterListener requesterListener){
        this.requesterListener = requesterListener;

        HttpPutConnection httpConnection = new HttpPutConnection(this);

        httpConnection.addHeader("Authorization", SystemData.getInstance().getToken());
        httpConnection.setUri("users");

        JSONObject body = this.buildObject();
        httpConnection.addBody(body);
        httpConnection.execute();
    }

    private JSONObject buildObject(){
        String myId = SystemData.getInstance().getUserId();
        UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(myId);
        JSONObject body = new JSONObject();

        userProfile.setLatitude(SystemData.getInstance().getLatitude());
        userProfile.setLongitude(SystemData.getInstance().getLongitude());

        try {
            body.put("username", userProfile.getId());
            body.put("name", userProfile.getName());
            body.put("alias", userProfile.getAlias());
            body.put("photo_profile", userProfile.getPhoto());
            body.put("sex", userProfile.getSex());

            JSONArray jsonArray = new JSONArray();

            for (Map.Entry<String, InterestCategory> entry : userProfile.getInterestCategories().entrySet())
            {
                for(String value : entry.getValue().getDetails()) {
                    JSONObject interest = new JSONObject();
                    interest.put("category", entry.getKey());
                    interest.put("value", value);
                    jsonArray.put(interest);
                }
            }

            body.put("interests", jsonArray);

            JSONObject location = new JSONObject();

            location.put("latitude", userProfile.getLatitude());
            location.put("longitude", userProfile.getLatitude());

            body.put("location", location);

        } catch (JSONException e){
            e.printStackTrace();
        }

        return body;
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
