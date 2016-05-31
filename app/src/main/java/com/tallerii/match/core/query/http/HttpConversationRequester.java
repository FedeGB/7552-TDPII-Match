package com.tallerii.match.core.query.http;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ChatMessage;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpGetConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dlopez on 31/05/2016.
 */
public class HttpConversationRequester implements HttpResponseListener {

    RequesterListener requesterListener;
    String myId;

    public void getConversationsWith(String userId, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpGetConnection httpGetConnection = new HttpGetConnection(this);
        SystemData systemData = SystemData.getInstance();
        myId = systemData.getUserId();
        String token = systemData.getToken();

        httpGetConnection.addHeader("user1", myId);
        httpGetConnection.addHeader("user2", userId);
        httpGetConnection.addHeader("token", token);

        httpGetConnection.setUri("conversations");

        httpGetConnection.execute();
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        try {
            JSONArray messageList = responseBody.getJSONArray("messages");
            Chat newChat = new Chat(null);

            for(int i = 0; i < messageList.length(); i++){
                JSONObject message = messageList.getJSONObject(i);
                String date = message.getString("date");
                String content = message.getString("data");
                String sender = message.getString("sender");

                boolean sendByMe = (sender.compareTo(myId) == 0);

                ChatMessage chatMessage = new ChatMessage(sendByMe, content, date);
                newChat.addMessageToChat(chatMessage);
            }

            requesterListener.onSuccesRequest(newChat);
        } catch (JSONException e){
            handleHttpError(-2, "Error parsing conversation in \"handleHttpResponse\" in HttpConversationRequester.java");
        }
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
