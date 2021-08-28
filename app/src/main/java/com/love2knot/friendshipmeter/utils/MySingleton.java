package com.love2knot.friendshipmeter.utils;

import android.app.Application;


public class MySingleton extends Application {
    private static MySingleton instance;
    public static synchronized MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //FirebaseApp.initializeApp(this);
        instance = this;

    }
}
