package com.tallerii.match.core;

import com.tallerii.match.core.http.HttpLoginRequester;
import com.tallerii.match.core.http.ResponseListener;

/**
 * Created by Demian on 07/05/2016.
 */
public class SystemData  implements ResponseListener {
    private static SystemData ourInstance = new SystemData();

    public static SystemData getInstance() {
        return ourInstance;
    }

    private DataCallback currentCallback;
    private boolean isLoged = false;
    private String ip = "192.168.0.103";
    private String port = "1234";
    private String token = "";

    private HttpLoginRequester loginRequester;

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    private SystemData() {
        loginRequester = new HttpLoginRequester(this);
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

    @Override
    public void httpRequestFinish(String responseTag) {
        if(currentCallback != null) {
            currentCallback.callback(responseTag);
        }
    }

    public DataCallback getCurrentCallback() {
        return currentCallback;
    }

    public void setCurrentCallback(DataCallback currentCallback) {
        this.currentCallback = currentCallback;
    }
}
