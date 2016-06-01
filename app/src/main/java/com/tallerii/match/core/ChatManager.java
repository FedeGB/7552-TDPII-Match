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

    public void addChat(ArrayList<Chat> chatList) {
        Iterator<Chat> chatIterator = chatList.iterator();

        while (chatIterator.hasNext()){
            addChat(chatIterator.next());
        }
    }

    public Chat getChat(String userId){
        return chatMap.get(userId);
    }

    public Iterator<Map.Entry<String, Chat>> getChatIterator(){
        return  chatMap.entrySet().iterator();
    }

}
