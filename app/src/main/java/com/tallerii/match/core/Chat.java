package com.tallerii.match.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Demian on 24/04/2016.
 */
public class Chat {

    ArrayList<ChatMessage> messageList = new ArrayList<>();
    UserProfile talkinUserProfile;

    public Chat(UserProfile talkinUserProfile) {
        this.talkinUserProfile = talkinUserProfile;
    }

    public void addMessageToChat(ChatMessage message){
        this.messageList.add(message);
    }

    public ArrayList<ChatMessage> getMessageList() {
        return messageList;
    }

    public UserProfile getTalkingUserProfile() {
        return talkinUserProfile;
    }
}
