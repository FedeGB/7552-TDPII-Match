package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.HttpSendMenssageRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class SendMessageQuery extends HttpQuery {
    public static final String QUERY_TAG = "SENDMESSAGE";
    private HttpSendMenssageRequester httpSendMenssageRequester = new HttpSendMenssageRequester();
    private QueryListener listener;
    private String data;
    private String userDestId;

    public SendMessageQuery(QueryListener listener, String userDestId, String data) {
        this.listener = listener;
        this.data = data;
        this.userDestId = userDestId;
    }

    @Override
    public void execute() {
        httpSendMenssageRequester.sendMessage(userDestId, data, this);
    }

    @Override
    public void onSuccesRequest(Object returnedObject) {
        listener.onReturnedRequest(QUERY_TAG);
        listener.afterRequest(QUERY_TAG);
        setAsFinished();
    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {
        listener.onFailRequest(errorMessage, QUERY_TAG);
        listener.afterRequest(QUERY_TAG);
        setAsFinished();
    }
}
