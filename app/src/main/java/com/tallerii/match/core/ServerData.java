package com.tallerii.match.core;

import com.tallerii.match.core.query.ConversationQuery;
import com.tallerii.match.core.query.HttpQuery;
import com.tallerii.match.core.query.LikeUserQuery;
import com.tallerii.match.core.query.LoginQuery;
import com.tallerii.match.core.query.CandidatesQuery;
import com.tallerii.match.core.query.MatchesQuery;
import com.tallerii.match.core.query.RegisterQuery;
import com.tallerii.match.core.query.QueryListener;
import com.tallerii.match.core.query.SendMessageQuery;
import com.tallerii.match.core.query.UpdateProfileQuery;
import com.tallerii.match.core.query.UserProfileQuery;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Demian on 26/05/2016.
 */
public class ServerData {
    private static ServerData ourInstance = new ServerData();
    public static ServerData getInstance() {
        return ourInstance;
    }

    Queue<HttpQuery> httpQueries = new LinkedList<>();
    boolean isExecuting = false;

    public void getMessages(String userId, QueryListener queryListener){
        addQuery(new ConversationQuery(userId, queryListener));
    }

    public void fetchUserMatches(QueryListener queryListener){
        addQuery(new MatchesQuery(queryListener));
    }

    public void sendMessage(String dest, String data, QueryListener queryListener){
        addQuery(new SendMessageQuery(queryListener, dest, data));
    }

    public void fetchCandidates(QueryListener requesterListener){
        addQuery(new CandidatesQuery(requesterListener));
    }

    public void fetchUserProfile(String id, QueryListener requesterListener){
        addQuery(new UserProfileQuery(requesterListener, id));
    }

    public void likeUser(String id, QueryListener requesterListener, boolean like){
        addQuery(new LikeUserQuery(requesterListener, id, like));
    }

    public void loginUser(String name, String pass, QueryListener requesterListener){
        addQuery(new LoginQuery(requesterListener,pass,name));
    }

    public void registerUser(String user, String name, String pass, QueryListener requesterListener){
        addQuery(new RegisterQuery(requesterListener, user, pass, name));
    }

    public void updateUserProfile(QueryListener requesterListener){
        addQuery(new UpdateProfileQuery(requesterListener));
    }

    private void addQuery(HttpQuery httpQuery){
        httpQuery.setServerData(this);
        httpQueries.add(httpQuery);
        tryExecute();
    }

    private void tryExecute(){
        if(!isExecuting){
            queryComplete();
        }
    }

    public void queryComplete(){
        if(httpQueries.size() > 0){
            isExecuting = true;
            httpQueries.remove().execute();
        } else {
            isExecuting = false;
        }
    }
}
