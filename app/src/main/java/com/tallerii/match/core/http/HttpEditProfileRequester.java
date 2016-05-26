package com.tallerii.match.core.http;

import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Demian on 26/05/2016.
 */
public class HttpEditProfileRequester extends HttpRequester {

    RequesterListener requesterListener;
    @Override
    public void afterError() {
        requesterListener.proccesRequest(false, "UPDATEPROFILE");
    }

    public void updateProfile(RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpPutConnection httpConnection = new HttpPutConnection(this);
        if(hasValidConnection()) {
            httpConnection.addHeader("token", SystemData.getInstance().getToken());
            httpConnection.setUri("users");

            JSONObject body = this.buildObject();
            httpConnection.addBody(body);

            httpConnection.execute();
        }
    }

    private JSONObject buildObject(){
        UserProfile userProfile = SystemData.getInstance().getUserProfile();
        JSONObject body = new JSONObject();

        try {

            body.put("id", userProfile.getId());
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

            body.put("interest", jsonArray);

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
    protected void responseArrival(JSONObject jsonObject) {
        requesterListener.proccesRequest(true, "UPDATEPROFILE");
    }
}
