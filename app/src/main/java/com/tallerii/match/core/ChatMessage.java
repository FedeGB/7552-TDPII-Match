package com.tallerii.match.core;

/**
 * Created by Demian on 25/05/2016.
 */
public class ChatMessage {

    private boolean sendByMe = true;
    private String content = "";
    private String Date = "";
    private boolean unread = true;
    String id = "";

    public String getId() {
        return id;
    }

    public ChatMessage(boolean sendByMe, String content, String date, String id) {
        this.sendByMe = sendByMe;
        this.content = content;
        Date = date;
        this.id = id;
    }

    public boolean isSendByMe() {
        return sendByMe;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return Date;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setReaded(){
        unread = false;
    }
}
