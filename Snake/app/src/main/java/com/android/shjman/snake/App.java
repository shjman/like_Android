package com.android.shjman.snake;

import android.app.Application;
import android.content.Context;


public class App extends Application {
    private static final String VACUUM = "VACUUM";
    private static App self;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
        App.context = getApplicationContext();
    }

    public static App getInstance() {
        return self;
    }

    public static Context getAppContext() {
        return App.context;
    }
}