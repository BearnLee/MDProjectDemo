package com.ljs.application;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by 暮雨而歌 on 2017/10/13.
 */

public class XApplication extends Application{
    private static int mScreenWidth;
    private static int mScreenHeight;
    private static XApplication sInstance;

    public static XApplication getApplication(){
        return sInstance;
    }

    public XApplication() {
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

    public static int getScreenWidth(){
        return mScreenWidth;
    }

    public static int getScreenHeight(){
        return mScreenHeight;
    }
}
