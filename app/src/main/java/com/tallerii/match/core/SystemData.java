package com.tallerii.match.core;

import com.tallerii.match.MatchFragmentMatchResults;

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


    private double latitude;
    private double longitude;


    private UserProfileManager userManager = new UserProfileManager();
    private ChatManager chatManager = new ChatManager();
    private CandidatesManager candidatesManager = new CandidatesManager();

    private UserProfile showingProfile;

    public UserProfile getShowingProfile() {
        return showingProfile;
    }

    public void setShowingProfile(UserProfile showingProfile) {
        this.showingProfile = showingProfile;
    }

    public UserProfileManager getUserManager() {
        return userManager;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }

    public CandidatesManager getCandidatesManager() {
        return candidatesManager;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getToken() {
        return token;
    }


    public void setLogin(String id, String token){
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public MatchFragmentMatchResults matchFragmentMatchResults;
}
