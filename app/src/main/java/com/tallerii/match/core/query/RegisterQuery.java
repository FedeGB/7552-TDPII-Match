package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.HttpRegisterRequest;

public class RegisterQuery extends HttpQuery {
    HttpRegisterRequest httpRegisterRequest = new HttpRegisterRequest();
    public static final String QUERY_TAG = "REGISTER";

    private String id;
    private String pass;
    private String name;
    private int age;
    private String sex;
    private QueryListener queryListener;


    public RegisterQuery(QueryListener queryListener, int age, String sex, String id, String pass, String name) {
        this.queryListener = queryListener;
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public void execute() {
        httpRegisterRequest.sendRegisterRequest(id, name, age, sex, pass, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
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
