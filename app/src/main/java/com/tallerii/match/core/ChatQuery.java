package com.tallerii.match.core;

import com.tallerii.match.core.HttpQuery;
import com.tallerii.match.core.RequesterListener;

/**
 * Created by Demian on 26/05/2016.
 */
public class ChatQuery extends HttpQuery {

    public ChatQuery(RequesterListener listener, DataFacade dataFacade) {
        super(listener, dataFacade);
    }

    @Override
    public void execute() {
        dataFacade.chatManager.getChatList(this);
    }

}
