package com.tallerii.match.core.query;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.http.HttpEditProfileRequester;

/**
 * Created by Demian on 26/05/2016.
 */
public class UpdateProfileQuery extends HttpQuery {
    HttpEditProfileRequester httpEditProfileRequester = new HttpEditProfileRequester();

    public UpdateProfileQuery(RequesterListener listener, DataFacade dataFacade) {
        super(listener, dataFacade);
    }

    @Override
    public void execute() {
        httpEditProfileRequester.updateProfile(this);
    }
}
