package com.lapzap.cgi;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by krychylskyy on 15/11/2017.
 */

public class MyApplication extends Application {

    private static Bus eventBus;

    @Override
    public void onCreate() {
        eventBus = new Bus();
        super.onCreate();
    }

    public static Bus getEventBus() {
        return eventBus;
    }

}
