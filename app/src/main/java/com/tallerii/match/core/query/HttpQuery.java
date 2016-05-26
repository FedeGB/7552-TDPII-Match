package com.tallerii.match.core.query;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;

/**
 * Created by Demian on 26/05/2016.
 */
public abstract class HttpQuery implements RequesterListener {
    private RequesterListener listener;
    protected DataFacade dataFacade;

    public HttpQuery(RequesterListener listener, DataFacade dataFacade){
        this.listener = listener;
        this.dataFacade = dataFacade;
    }

    public abstract void execute();

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        listener.proccesRequest(returnedObject, request);
        dataFacade.queryComplete();
    }
}
