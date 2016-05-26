package com.tallerii.match.core;

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

    Queue<HttpQuery> httpQueries = new LinkedList<>();
    boolean isExecuting = false;

    private DataFacade() {
    }

    public void getChatList(RequesterListener requesterListener){
        addQuery(new ChatQuery(requesterListener, this));
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
