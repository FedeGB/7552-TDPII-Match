package com.tallerii.match.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Demian on 24/04/2016.
 */
public class Chat implements Serializable {

    ArrayList<ChatMessage> messageList = new ArrayList<>();
    String talkingUserId = "";

    public Chat() {

    }

    public void addMessageToChat(ChatMessage message){
        this.messageList.add(message);
    }

    public ArrayList<ChatMessage> getMessageList() {
        return messageList;
    }
}
