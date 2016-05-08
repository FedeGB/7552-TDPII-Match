package com.tallerii.match.core;

/**
 * Created by Demian on 07/05/2016.
 */
public class SystemData {
    private static SystemData ourInstance = new SystemData();

    public static SystemData getInstance() {
        return ourInstance;
    }

    private String ip = "192.168.0.103";
    private String port = "1234";

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    private SystemData() {
    }
}
