package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;

/**
 * Created by Demian on 26/05/2016.
 */
public class MatchQuery extends HttpQuery {
    private QueryListener queryListener;

    public MatchQuery(QueryListener listener, ServerData serverData) {
        this.queryListener = listener;
    }

    @Override
    public void execute() {
        //dataFacade.matchManager.getNextMatch(this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {

    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {

    }
}
