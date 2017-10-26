package com.ljs.utils;

import android.content.res.ColorStateList;
import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by 暮雨而歌 on 2017/10/24.
 */

public class MaterialDesignUtils {
    //改变TextInputLayout的标签颜色
    public static void changeTextInputLayoutLabelColor(TextInputLayout layout, int color) {
        if (layout != null) {
            try {
                Field field = layout.getClass().getDeclaredField("mFocusedTextColor");
                field.setAccessible(true);
                field.set(layout, createColorStateList(color, 0, 0, 0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ColorStateList createColorStateList(int normal, int pressed, int select,
                                                      int unable) {
        if (pressed == 0) {
            pressed = normal;
        }
        if (select == 0) {
            select = normal;
        }
        if (unable == 0) {
            unable = normal;
        }
        int[] colors = new int[] {
                pressed, select, unable, normal
        };
        int[][] states = new int[4][];
        states[0] = new int[] {
                android.R.attr.state_pressed, android.R.attr.state_enabled
        };
        states[1] = new int[] {
                android.R.attr.state_enabled, android.R.attr.state_selected
        };
        states[2] = new int[] {
                -android.R.attr.state_enabled
        };
        states[3] = new int[] {
                android.R.attr.state_enabled
        };
        return new ColorStateList(states, colors);
    }

    //设置TextInputLayout的错误颜色显示
    public static void setErrorTextColor(TextInputLayout textInputLayout, int color) {
        try {
            Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);
            TextView mErrorView = (TextView) fErrorView.get(textInputLayout);
            Field fCurTextColor = TextView.class.getDeclaredField("mCurTextColor");
            fCurTextColor.setAccessible(true);
            fCurTextColor.set(mErrorView, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
