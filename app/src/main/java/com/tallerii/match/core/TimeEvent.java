package com.tallerii.match.core;

import android.os.Handler;

import com.tallerii.match.core.query.QueryListener;

/**
 * Created by demian on 22/06/16.
 */
public class TimeEvent implements Runnable {
    private final int DELAY_TIME = 1000;

    Handler handler = new Handler();
    QueryListener listener;

    public TimeEvent(QueryListener listener){
        this.listener = listener;
    }

    public Handler getHandler() {
        return handler;
    }

    @Override
    public void run() {
        ServerData.getInstance().fetchUserMatches(listener);
    }
}
