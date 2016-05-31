package com.tallerii.match.core;

import com.tallerii.match.core.query.QueryListener;

import java.util.ArrayList;

/**
 * Created by Demian on 25/05/2016.
 */
public class ChatManager {
    private ArrayList<Chat> chatArrayList = new ArrayList<>();

    public void addChat(Chat chat){
        chatArrayList.add(chat);
    }

}
