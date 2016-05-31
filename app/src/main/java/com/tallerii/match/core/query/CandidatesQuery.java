package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;
import com.tallerii.match.core.query.http.HttpCandidatesListRequester;

import java.util.ArrayList;

public class CandidatesQuery extends HttpQuery {
    public static final String QUERY_TAG = "CANDIDATES";

    HttpCandidatesListRequester httpCandidatesListRequester = new HttpCandidatesListRequester();
    private QueryListener queryListener;

    public CandidatesQuery(QueryListener listener) {
        this.queryListener = listener;
    }

    @Override
    public void execute() {
        httpCandidatesListRequester.getMatchList(this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        ArrayList<UserProfile> candidatesList = (ArrayList<UserProfile>) returnedObject;
        SystemData systemData = SystemData.getInstance();
        systemData.getCandidatesManager().addToMatchQueue(candidatesList);
        systemData.getUserManager().addAllToProfileList(candidatesList);

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
}
