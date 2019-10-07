package com.aidentsl.anewtech.ocbcquestionaire.utils;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Timer;


public class App extends Application {


    private static Context mContext;
    private static App   mInstance;
    private static Timer mTimer;



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //上下文
        mContext = getApplicationContext();
        mTimer = new Timer(true);
        //LogCollector.init(this);



    }

    public static App getInstance() {

        return mInstance;
    }



    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
    /* 得到上下文 */
    public static Context getContext() {
        return mContext;
    }




    /** 得到主线程Timer */
    public static Timer getmTimer() {
        return mTimer;
    }

}
