package com.tallerii.match.core.query;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.http.RequesterListener;

public abstract class HttpQuery implements RequesterListener {
    public abstract void execute();

    private ServerData serverData;

    public void setServerData(ServerData serverData) {
        this.serverData = serverData;
    }

    protected void setAsFinished(){
        this.serverData.queryComplete();
    }
}
