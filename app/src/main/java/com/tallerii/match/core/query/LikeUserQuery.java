package com.tallerii.match.core.query;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.http.HttpLikeRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class LikeUserQuery extends HttpQuery {
    HttpLikeRequester httpLikeRequester = new HttpLikeRequester();
    String userId;
    boolean like;

    public LikeUserQuery(RequesterListener listener, DataFacade dataFacade, String userId, boolean like) {
        super(listener, dataFacade);
        this.userId = userId;
        this.like = like;
    }

    @Override
    public void execute() {
        httpLikeRequester.sendLikeToUser(userId, like, this);
    }


}
