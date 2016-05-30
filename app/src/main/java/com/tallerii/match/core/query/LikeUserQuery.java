package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.http.HttpLikeRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class LikeUserQuery extends HttpQuery {
    private QueryListener listener;
    HttpLikeRequester httpLikeRequester = new HttpLikeRequester();
    String userId;
    boolean like;

    public LikeUserQuery(QueryListener listener, ServerData serverData, String userId, boolean like) {
        this.listener = listener;
        this.userId = userId;
        this.like = like;
    }

    @Override
    public void execute() {
        httpLikeRequester.sendLikeToUser(userId, like, this);
    }


    @Override
    public void onSuccesRequest(Object returnedObject) {

    }

    @Override
    public void onFailRequest(int errorCode, String errorMessage) {

    }
}
