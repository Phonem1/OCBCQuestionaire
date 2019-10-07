package com.aidentsl.anewtech.ocbcquestionaire.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.aidentsl.anewtech.ocbcquestionaire.constants.MyConstants;

/**
 * HideSetting.java
 * "设置FloatBar显示或隐藏的辅助类"
 * <p>
 * Created by Cris_Peng on 2016/12/14
 * Copyright (c) 2016 QihanCloud, Inc. All Rights Reserved.
 */

public class HideSetting {
    public final static Uri SETTING_CONTENT_URI = Uri.parse("content://com.qihancloud.floatbar/t_setting");
    /**
     * @param className 需要填写播放视频或动画的Activity（最好用getClass().getName()）
     * @param isHide    是否隐藏
     * @return 返回是否设置成功，大于0代表成功
     */
    public static int hideFloatBar(Context context, String className, boolean isHide) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("className", className);
        contentValues.put("isHide", isHide ? "1" : "0");
        int result = -1;
        try {
            result = context.getContentResolver().update(SETTING_CONTENT_URI, contentValues, "key=?", new String[]{"hide_floatbar"});
            Log.i("launcher","新版floatbar"+result);
        } catch (Exception e) {
            Log.i("launcher","旧版floatbar");
            if(isHide){
                Intent intent = new Intent(MyConstants.SYSTEM_BUTTON_CLOSE);
                context.sendBroadcast(intent);
            }else{
                Intent intent = new Intent(MyConstants.SYSTEM_BUTTON_OPEN);
                context.sendBroadcast(intent);
            }
        } finally {
            return result;
        }
    }
}
