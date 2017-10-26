package com.ljs.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.ljs.application.XApplication;

/**
 * Created by 暮雨而歌 on 2017/10/17.
 */

public class SystemUtils {
    public static void lauchActivity(Activity activity,Class cls){
        Intent intent = new Intent();
        intent.setClass(activity,cls);
        activity.startActivity(intent);
    }

    public static void openOrCloseDrawer(DrawerLayout drawerLayout, NavigationView navigationView) {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            drawerLayout.openDrawer(navigationView);
        }
    }

    private static int statusBarHeight = -1;
    public static int getStatusBarHeight(){
        if(statusBarHeight == -1){
            statusBarHeight = getInternalDimensionSize(XApplication.getApplication().getResources(), "status_bar_height");
        }
        return statusBarHeight;
    }

    public static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
