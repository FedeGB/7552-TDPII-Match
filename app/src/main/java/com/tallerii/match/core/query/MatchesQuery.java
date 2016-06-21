package com.tallerii.match.core.query;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ChatManager;
import com.tallerii.match.core.NullQueryListener;
import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.HttpGetMatchesRequester;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Demian on 31/05/2016.
 */
public class MatchesQuery extends HttpQuery {
    public static final String QUERY_TAG = "MATCHES";
    HttpGetMatchesRequester httpGetMatchesRequester = new HttpGetMatchesRequester();
    QueryListener queryListener;

    public MatchesQuery(QueryListener queryListener){
        this.queryListener = queryListener;
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        ArrayList<String> matches = (ArrayList<String>) returnedObject;
        Iterator<String> matchesIterator = matches.iterator();

        ChatManager cM = SystemData.getInstance().getChatManager();

        cM.clearChatList();

        while (matchesIterator.hasNext()){
            String match = matchesIterator.next();
            Chat newChat = new Chat(match);
            cM.addChat(newChat);

            ServerData.getInstance().getMessages(match, new NullQueryListener());
        }

        queryListener.onReturnedRequest(QUERY_TAG);
        queryListener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        queryListener.onFailRequest(errorMessage, QUERY_TAG);
        queryListener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void execute() {
        httpGetMatchesRequester.requestUserMatches(this);
    }
}
