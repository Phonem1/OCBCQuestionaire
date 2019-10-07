package com.aidentsl.anewtech.ocbcquestionaire.constants;


import com.aidentsl.anewtech.ocbcquestionaire.utils.App;
import com.qihancloud.opensdk.setting.Setting;

public class MyConstants {

    public static final String DEVICE_ID  = Setting.getString(
            App.getContext().getContentResolver(), Setting.Key.DEVICE_ID);
    public static final int    NEGATIVE     = -1;
    public static final int    ZEROTH     = 0;
    public static final int    FIRST      = 1;
    public static final int    SECOND     = 2;
    public static final int    THIRD      = 3;
    public static final int    FOURTH     = 4;
    public static final int    FIFTH      = 5;
    public static final int    ELEVENTH   = 11;
    public static final int    TWELFTH    = 12;
    public static final int    THIRTEENTH = 13;

    public static final long ZEROTH_TIME = 30000;
    //public static final long ZEROTH_TIME =300000;//测试
    public static final long FIRST_TIME  = 20000;
    public static final long SECOND_TIME = 100;//其实不需要
    public static final long THIRD_TIME  = 1000;


    public static final long WELCOME_TIME  = 20000;//欢迎无响应时间
    public static final long INTERVAL_TIME = 40000;//欢迎无响应时间

    public static final long REQUEST_TIME = 60000;//请求失败重新请求的时间间隔

    public static final String SYSTEM_BUTTON_OPEN  = "com.qihan.FloatService.open";//返回键开启
    public static final String SYSTEM_BUTTON_CLOSE = "com.qihan.FloatService.close";//返回键关闭


    public static final long FIRST_WELCOME  = 2000;
    public static final long THIRD_WELCOME  = 15000;
    public static final long FOURTH_WELCOME = 10000;
    public static final long FIFTH_WELCOME  = 2000;

    public static final long ELEVENTH_WELCOME   = 1000;
    public static final long TWELFTH_WELCOME    = 1500;
    public static final long THIRTEENTH_WELCOME = 5000;


    public static final long WELCOME_LOOP  = 60000;//欢迎循环播报
}
