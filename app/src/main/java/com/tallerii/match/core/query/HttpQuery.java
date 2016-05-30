package com.tallerii.match.core.query;

import com.tallerii.match.core.query.http.RequesterListener;

public abstract class HttpQuery implements RequesterListener {
    public abstract void execute();
}
