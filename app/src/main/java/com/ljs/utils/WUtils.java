package com.ljs.utils;

import android.view.View;

/**
 * Created by 暮雨而歌 on 2017/10/24.
 */

public class WUtils {
    public static void setPaddingTop(View v, int padding){
        v.setPadding(v.getPaddingLeft(), padding, v.getPaddingRight(),
                v.getPaddingBottom());
    }
}
