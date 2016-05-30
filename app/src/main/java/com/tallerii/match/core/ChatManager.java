package com.tallerii.match.core;

import com.tallerii.match.core.query.QueryListener;

import java.util.ArrayList;

/**
 * Created by Demian on 25/05/2016.
 */
public class ChatManager implements QueryListener {
    private ArrayList<Chat> chatArrayList = new ArrayList<>();
    private UserProfileManager userProfileManager;
    private QueryListener requesterListener;

    public ChatManager(UserProfileManager userProfileManager) {
        this.userProfileManager = userProfileManager;
    }

    public void getChatList(QueryListener requesterListener) {
        this.requesterListener = requesterListener;
        userProfileManager.getUserProfile("fiubaTeam@fiuba.com.ar", this);
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        UserProfile userProfile = (UserProfile) returnedObject;
        Chat newChat = new Chat(userProfile);
        newChat.addMessageToChat(new ChatMessage(false, "Hello from the Fiuba team!", "Today"));
        chatArrayList.clear();
        chatArrayList.add(newChat);
        requesterListener.proccesRequest(chatArrayList, "CHATLIST");
    }

    public Chat getChatByIdWithoutQuery(int id){
        return chatArrayList.get(id);
    }
}
