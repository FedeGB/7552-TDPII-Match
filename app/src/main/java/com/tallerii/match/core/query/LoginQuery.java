package com.tallerii.match.core.query;

import com.tallerii.match.core.NullQueryListener;
import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.HttpLoginRequester;

public class LoginQuery extends HttpQuery {
    public static final String QUERY_TAG = "LOGIN";
    HttpLoginRequester loginRequester = new HttpLoginRequester();
    private QueryListener listener;
    private String pass;
    private String user;

    public LoginQuery(QueryListener listener, String pass, String user) {
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
        String userToken = (String) returnedObject;
        SystemData.getInstance().setLogin(user, userToken);
        ServerData.getInstance().fetchUserProfile(user, new NullQueryListener());

        listener.onReturnedRequest(QUERY_TAG);
        listener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        listener.onFailRequest(QUERY_TAG, errorMessage);
        listener.afterRequest(QUERY_TAG);
        setAsFinished();
    }
}
