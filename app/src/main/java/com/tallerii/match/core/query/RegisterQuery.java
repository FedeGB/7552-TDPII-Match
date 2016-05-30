package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.http.HttpRegisterRequest;

/**
 * Created by Demian on 26/05/2016.
 */
public class RegisterQuery extends HttpQuery {
    private String id;
    private String pass;
    private String name;
    private QueryListener queryListener;

    HttpRegisterRequest httpRegisterRequest = new HttpRegisterRequest();

    public RegisterQuery(QueryListener queryListener, String id, String pass, String name) {
        this.queryListener = queryListener;
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    @Override
    public void execute() {
        httpRegisterRequest.sendRegisterRequest(id, name, pass, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {

    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {

    }
}
