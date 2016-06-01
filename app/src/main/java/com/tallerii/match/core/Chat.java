package com.tallerii.match.core;

import android.os.UserManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Demian on 24/04/2016.
 */
public class Chat {

    ArrayList<ChatMessage> messageList = new ArrayList<>();
    UserProfile talkinUserProfile;
    String userId;

    public Chat(String userId) {
        this.userId = userId;

        UserProfileManager userProfileManager = SystemData.getInstance().getUserManager();

        if(userProfileManager.hasUserProfile(userId)){
            talkinUserProfile = userProfileManager.getUserProfile(userId);
        } else {
            talkinUserProfile = null;
            //TODO: Paso un null QUERY LISTENER, pero quiza el mismo deberia escuchar y actualizar el perfil cuando vuelva!
            ServerData.getInstance().fetchUserProfile(userId, new NullQueryListener());
        }
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

    public String getUserId() {
        return userId;
    }
}
