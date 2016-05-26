package com.tallerii.match.core.query;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.http.HttpLoginRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class LoginQuery extends HttpQuery {
    String pass;
    String user;
    HttpLoginRequester loginRequester = new HttpLoginRequester();

    public LoginQuery(RequesterListener listener, DataFacade dataFacade, String pass, String user) {
        super(listener, dataFacade);
        this.pass = pass;
        this.user = user;
    }

    @Override
    public void execute() {
        loginRequester.sendLoginRequest(user, pass, this);
    }
}
