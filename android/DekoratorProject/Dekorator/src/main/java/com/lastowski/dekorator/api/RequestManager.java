package com.lastowski.dekorator.api;

import android.content.Context;

/**
 * Created by adamastowski on 10.09.2013.
 */
public class RequestManager extends com.foxykeep.datadroid.requestmanager.RequestManager {

    private static RequestManager sInstance;

    public synchronized static RequestManager from(Context context) {
        if (sInstance == null) {
            sInstance = new RequestManager(context);
        }

        return sInstance;
    }

    private RequestManager(Context context) {
        super(context, CommsService.class);
    }
}

