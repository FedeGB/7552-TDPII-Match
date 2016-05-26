package com.tallerii.match.core;

import com.tallerii.match.core.query.ChatQuery;
import com.tallerii.match.core.query.HttpQuery;
import com.tallerii.match.core.query.LikeUserQuery;
import com.tallerii.match.core.query.LoginQuery;
import com.tallerii.match.core.query.MatchQuery;
import com.tallerii.match.core.query.RegisterQuery;
import com.tallerii.match.core.query.UserProfileQuery;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Demian on 26/05/2016.
 */
public class DataFacade {
    private static DataFacade ourInstance = new DataFacade();

    public static DataFacade getInstance() {
        return ourInstance;
    }


    public UserProfileManager userManager = new UserProfileManager();
    public ChatManager chatManager = new ChatManager(userManager);
    public MatchManager matchManager = new MatchManager();

    Queue<HttpQuery> httpQueries = new LinkedList<>();
    boolean isExecuting = false;

    private DataFacade() {
    }

    public void getChatList(RequesterListener requesterListener){
        addQuery(new ChatQuery(requesterListener, this));
    }

    public void getNextMatch(RequesterListener requesterListener){
        addQuery(new MatchQuery(requesterListener, this));
    }

    public void getUserProfile(String id, RequesterListener requesterListener){
        addQuery(new UserProfileQuery(requesterListener, id, this));
    }

    public void likeUser(String id, RequesterListener requesterListener, boolean like){
        addQuery(new LikeUserQuery(requesterListener, this, id, like));
    }

    public void loginUser(String name, String pass, RequesterListener requesterListener){
        addQuery(new LoginQuery(requesterListener, this,pass,name));
    }

    public void registerUser(String user, String name, String pass, RequesterListener requesterListener){
        addQuery(new RegisterQuery(requesterListener, this, user, pass, name));
    }


    private void addQuery(HttpQuery httpQuery){
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

    public Chat getChatByIdWithoutQuery(int id){
        return chatManager.getChatByIdWithoutQuery(id);
    }
}
