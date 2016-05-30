package com.tallerii.match.core;

import com.tallerii.match.core.query.http.HttpMatchListRequester;
import com.tallerii.match.core.query.QueryListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Demian on 26/05/2016.
 */
public class MatchManager implements QueryListener {
    Queue<UserProfile> userMatchList = new LinkedList<>();
    QueryListener requesterListener;
    HttpMatchListRequester httpMatchListRequester = new HttpMatchListRequester();

    public void getNextMatch(QueryListener requesterListener){
        this.requesterListener = requesterListener;
        if(userMatchList.size() > 0){
            UserProfile nextMatch = userMatchList.remove();
            requesterListener.proccesRequest(nextMatch, "MATCH");
        } else {
            httpMatchListRequester.getMatchList(this);
        }
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        ArrayList<UserProfile> userMatches = (ArrayList<UserProfile>) returnedObject;
        userMatchList.addAll(userMatches);

        UserProfile nextMatch = null;

        if(userMatchList.size() > 0){
            nextMatch = userMatchList.remove();
        }

        requesterListener.proccesRequest(nextMatch, "MATCH");
    }
}
