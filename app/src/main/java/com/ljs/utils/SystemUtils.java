package com.ljs.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

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
}
