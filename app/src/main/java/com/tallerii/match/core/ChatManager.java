package com.tallerii.match.core;

import com.tallerii.match.core.query.QueryListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Demian on 25/05/2016.
 */
public class ChatManager {
    private Map<String, Chat> chatMap = new HashMap<>();

    public void addChat(Chat chat){
        chatMap.put(chat.userId, chat);
    }

    public void mergeChat(ArrayList<ChatMessage> chatMessages, String userId) {
        chatMap.get(userId).mergeMessages(chatMessages);
    }

    public Chat getChat(String userId){
        return chatMap.get(userId);
    }

    public Iterator<Map.Entry<String, Chat>> getChatIterator(){
        return  chatMap.entrySet().iterator();
    }

    public boolean hasChat(String chat) {
        return chatMap.containsKey(chat);
    }

    public void clearChatList() {
        chatMap.clear();
    }
}
