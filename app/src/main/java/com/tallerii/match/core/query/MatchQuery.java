package com.tallerii.match.core.query;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;

/**
 * Created by Demian on 26/05/2016.
 */
public class MatchQuery extends HttpQuery {

    public MatchQuery(RequesterListener listener, DataFacade dataFacade) {
        super(listener, dataFacade);
    }

    @Override
    public void execute() {
        dataFacade.matchManager.getNextMatch(this);
    }
}
