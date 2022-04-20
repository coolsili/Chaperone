package com.lww.mwwm;

import android.app.Application;

import com.lww.lwwlibrary.retrofit.RetrofitHandler;


public class BaseApplication extends Application {
    public static  BaseApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        RetrofitHandler.getInstance().setBaseUrl("https://iomstest.logimis.com/");
    }

    public static BaseApplication getInstance(){
        return application;
    }
}
