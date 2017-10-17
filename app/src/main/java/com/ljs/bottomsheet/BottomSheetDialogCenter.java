package com.ljs.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.media.MediaBrowserCompat;
import android.view.ViewGroup;

/**
 * Created by 暮雨而歌 on 2017/10/13.
 */

public class BottomSheetDialogCenter extends BottomSheetDialog{
    public BottomSheetDialogCenter(@NonNull Context context) {
        super(context);
    }

    public BottomSheetDialogCenter(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    public BottomSheetDialogCenter(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
