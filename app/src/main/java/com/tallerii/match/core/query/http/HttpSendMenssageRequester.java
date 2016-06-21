package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpPostConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONObject;

/**
 * Created by Demian on 31/05/2016.
 */
public class HttpSendMenssageRequester implements HttpResponseListener {

    RequesterListener requesterListener;

    public void sendMessage(String userId, String message, RequesterListener requesterListener){
        HttpPostConnection httpPostConnection = new HttpPostConnection(this, "HttpSendMessageRequester");
        this.requesterListener = requesterListener;

        String myId = SystemData.getInstance().getUserId();
        String token = SystemData.getInstance().getToken();

        httpPostConnection.addHeader("Token", token);
        httpPostConnection.setUri("messages");
        httpPostConnection.addVariable("user1", myId);
        httpPostConnection.addVariable("user2", userId);
        httpPostConnection.addVariable("data", message);

        httpPostConnection.execute();
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
