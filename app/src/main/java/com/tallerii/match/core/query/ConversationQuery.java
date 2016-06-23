package com.tallerii.match.core.query;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ChatMessage;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.HttpConversationRequester;

import java.util.ArrayList;

/**
 * Created by dlopez on 31/05/2016.
 */
public class ConversationQuery extends HttpQuery {
    public static final String QUERY_TAG = "CONVERSATION";
    HttpConversationRequester httpConversationRequester = new HttpConversationRequester();
    QueryListener queryListener;
    String userId;

    public ConversationQuery(String userId, QueryListener queryListener){
        this.userId = userId;
        this.queryListener = queryListener;
    }

    @Override
    public void execute() {
        httpConversationRequester.getConversationsWith(userId, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        ArrayList<ChatMessage> chatMessages = (ArrayList<ChatMessage>) returnedObject;


        SystemData.getInstance().getChatManager().mergeChat(chatMessages, userId);

        queryListener.onReturnedRequest(QUERY_TAG);
        queryListener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        queryListener.onFailRequest(QUERY_TAG, errorMessage);
        queryListener.afterRequest(QUERY_TAG);
        setAsFinished();
    }
}
