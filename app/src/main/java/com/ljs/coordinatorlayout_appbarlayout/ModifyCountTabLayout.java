package com.ljs.coordinatorlayout_appbarlayout;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import java.lang.reflect.Field;

/**
 * Created by 暮雨而歌 on 2017/10/13.
 */

public class ModifyCountTabLayout extends TabLayout{
    public int tabViewNumber = 4;
    private static final String SCROLLABLE_TAB_MIN_WIDTH = "mScrollableTabMinWidth";

    public ModifyCountTabLayout(Context context) {
        super(context);
        initTabMinWidth();
    }

    public ModifyCountTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabMinWidth();
    }

    public ModifyCountTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTabMinWidth();
    }

    private void initTabMinWidth() {
        int screenWidth=getResources().getDisplayMetrics().widthPixels;
        int tabMinWidth = screenWidth / tabViewNumber;

        Field field;
        try {
            field = TabLayout.class.getDeclaredField(SCROLLABLE_TAB_MIN_WIDTH);
            field.setAccessible(true);
            field.set(this, tabMinWidth);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
