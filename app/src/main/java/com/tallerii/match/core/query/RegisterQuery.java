package com.tallerii.match.core.query;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.http.HttpRegisterRequest;

/**
 * Created by Demian on 26/05/2016.
 */
public class RegisterQuery extends HttpQuery {
    private String id;
    private String pass;
    private String name;

    HttpRegisterRequest httpRegisterRequest = new HttpRegisterRequest();

    public RegisterQuery(RequesterListener listener, DataFacade dataFacade, String id, String pass, String name) {
        super(listener, dataFacade);
        this.id = id;
        this.pass = pass;
        this.name = name;
    }

    @Override
    public void execute() {
        httpRegisterRequest.sendRegisterRequest(id, name, pass, this);
    }
}
