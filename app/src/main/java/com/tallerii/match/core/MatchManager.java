package com.tallerii.match.core;

import com.tallerii.match.core.http.HttpMatchListRequester;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Demian on 26/05/2016.
 */
public class MatchManager implements RequesterListener {
    Queue<String> userMatchList = new LinkedList<>();
    RequesterListener requesterListener;
    HttpMatchListRequester httpMatchListRequester = new HttpMatchListRequester();

    public void getNextMatch(RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        if(userMatchList.size() > 0){
            String nextMatch = userMatchList.remove();
            requesterListener.proccesRequest(nextMatch, "MATCH");
        } else {
            httpMatchListRequester.getMatchList(this);
        }
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        ArrayList<String> userMatches = (ArrayList<String>) returnedObject;
        userMatchList.addAll(userMatches);

        String nextMatch = "fiubaTeam@fiuba.com.ar";

        if(userMatchList.size() > 0){
            nextMatch = userMatchList.remove();
        }

        requesterListener.proccesRequest(nextMatch, "MATCH");
    }
}
