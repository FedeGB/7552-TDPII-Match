package com.tallerii.match.core.http;

import com.tallerii.match.core.ResponseListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by Demian on 07/05/2016.
 */
public class HttpUserProfileRequester extends HttpRequester {
    ResponseListener<UserProfile> responseListener;

    public HttpUserProfileRequester(ResponseListener<UserProfile> responseListener){
        this.responseListener = responseListener;
    }

    public void getSerializedUserProfile(int userId){
        HttpConnection httpConnection = new HttpConnection(this);
        if(hasValidConnection()){
            httpConnection.setMethod(HttpConnection.HttpMethod.Get);
            httpConnection.setUri("users");
            httpConnection.addUriVariable("username", Integer.toString(userId));
            httpConnection.execute();
        }
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        //TODO DESEREALIZAR ESTO

        //TODO Devolver el userProfile deserializado!
        responseListener.response(new UserProfile());
    }
}
