package com.lu.beauty.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by XiaoyuLu on 16/11/25.
 */

public class MyApp extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext(){
        return sContext;
    }
}
