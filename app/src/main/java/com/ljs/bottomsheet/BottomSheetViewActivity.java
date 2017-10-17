package com.ljs.bottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ljs.sumery.R;

/**
 * Created by 暮雨而歌 on 2017/10/12.
 */

/*
** behavior的几种状态，总是HIDDING/DRAGGING->SETTILING->COLLAPSED/EXPANDED(结束状态);
** 图片缩放原理：根据onSlide判断是否有下滑偏移量，如果有，则说明图片已经进行过缩放了，
*  图片的缩放比例根据下滑偏移量等比例缩放，如果在拖动结束的时候，下滑偏移量小于1，则关闭bottomSheet
*  在拖动过程中松开手指会继续调用OnSlide,所以要在结束的时候再去设置isDragging标志位，否则会出现图片被压缩的情况
 */
public class BottomSheetViewActivity extends AppCompatActivity {
    BottomSheetBehavior behavior;
    ImageView mImageViewSheet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottonsheet);

        mImageViewSheet = (ImageView) findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(mImageViewSheet);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(BottomSheetBehavior.STATE_DRAGGING == newState){
                }
                if(BottomSheetBehavior.STATE_COLLAPSED== newState){
                }
                if(BottomSheetBehavior.STATE_EXPANDED == newState){
                }
                if(BottomSheetBehavior.STATE_HIDDEN == newState){
                }
                if(BottomSheetBehavior.STATE_SETTLING == newState){
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                mImageViewSheet.setAlpha(slideOffset);
                mImageViewSheet.setScaleX(slideOffset);
                mImageViewSheet.setScaleY(slideOffset);
            }
        });
    }

    public void doclick(View v)
    {
        switch (v.getId()) {
            case R.id.button0:
                if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.button1:
                BottomSheetDialogCenter mBottomSheetDialog = new BottomSheetDialogCenter(this);
                View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
                mBottomSheetDialog.setContentView(view);
                mBottomSheetDialog.show();
                break;
            case R.id.button2:
                new FullSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
                break;
        }
    }

}
