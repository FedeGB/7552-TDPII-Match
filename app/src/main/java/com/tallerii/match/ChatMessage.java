package com.tallerii.match;

/**
 * Created by Demian on 25/05/2016.
 */
public class ChatMessage {

    private boolean sendByMe = true;
    private String content = "";
    private String Date = "";
    private boolean unread = true;

    public ChatMessage(boolean sendByMe, String content, String date) {
        this.sendByMe = sendByMe;
        this.content = content;
        Date = date;
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
