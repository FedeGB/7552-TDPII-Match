package com.tallerii.match.core;

import com.tallerii.match.core.http.HttpLoginRequester;
import com.tallerii.match.core.http.ResponseListener;

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

    private HttpLoginRequester loginRequester;

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    private SystemData() {
        loginRequester = new HttpLoginRequester(null);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        this.isLoged = true;
    }

    public boolean isLoged() {
        return isLoged;
    }

    public void logIn(String name, String password){
        loginRequester.sendLoginRequest(name, password);
    }

    public String getUserId() {
        return userId;
    }
}
