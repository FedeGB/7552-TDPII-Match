package com.tallerii.match.core;

import android.graphics.Bitmap;

import com.tallerii.match.core.http.HttpLoginRequester;

/**
 * Created by Demian on 07/05/2016.
 */
public class SystemData {
    private static SystemData ourInstance = new SystemData();

    public static SystemData getInstance() {
        return ourInstance;
    }

    private boolean isLoged = false;
    private String ip = "192.168.0.103";
    private String port = "1234";
    private String token = "NONTOKEN";
    private String userId = "NONID";
    private UserProfile userProfile;

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getToken() {
        return token;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setLogin(String id, String token){
        userProfile = new UserProfile(id);
        setToken(token);
        this.userId = id;
    }

    public void setToken(String token) {
        this.token = token;
        this.isLoged = true;
    }

    public boolean isLoged() {
        return isLoged;
    }

    public String getUserId() {
        return userId;
    }
}
