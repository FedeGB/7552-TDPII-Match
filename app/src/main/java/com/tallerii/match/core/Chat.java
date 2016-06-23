package com.tallerii.match.core;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Created by Demian on 24/04/2016.
 */
public class Chat extends Observable {

    ArrayList<ChatMessage> messageList = new ArrayList<>();

    String userId;

    public Chat(String userId) {
        this.userId = userId;
    }

    public void addMessageToChat(ChatMessage message){
        this.messageList.add(message);
    }

    public ArrayList<ChatMessage> getMessageList() {
        return messageList;
    }

    public String getUserId() {
        return userId;
    }

    public void mergeMessages(ArrayList<ChatMessage> chatMessages) {

        Iterator<ChatMessage> newMessagesIterator = chatMessages.iterator();
        while (newMessagesIterator.hasNext()) {
            ChatMessage newMessage = newMessagesIterator.next();
            boolean hasMessage = false;
            for(int i = 0; i < messageList.size(); i++) {
                ChatMessage actualMessage = messageList.get(i);
                hasMessage = hasMessage || (actualMessage.id.compareTo(newMessage.id) == 0);
            }
            if(!hasMessage) {
                this.notifyObservers(newMessage);
                this.setChanged();
                messageList.add(newMessage);
            }
        }

    }
}
