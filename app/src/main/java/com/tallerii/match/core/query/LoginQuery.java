package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.http.HttpLoginRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class LoginQuery extends HttpQuery {

    HttpLoginRequester loginRequester = new HttpLoginRequester();
    private QueryListener listener;
    private String pass;
    private String user;

    public LoginQuery(QueryListener listener, ServerData serverData, String pass, String user) {
        this.listener = listener;
        this.pass = pass;
        this.user = user;
    }

    @Override
    public void execute() {
        loginRequester.sendLoginRequest(user, pass, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {

    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {

    }
}
