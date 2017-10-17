package com.ljs.utils;

import android.widget.Toast;

import com.ljs.application.XApplication;

/**
 * Created by 暮雨而歌 on 2017/10/17.
 */

public class ToastManager {
    private static long         lastTime;
    private ToastManager(){

    }

    public static ToastManager getTostManager(){
        return ToastManagerImp.getToastManager();
    }

    public void showToast(int resId){
        showToast(XApplication.getContext().getString(resId));
    }

    public void showToast(String toastInfo){
        if(isNeedToShowToast()){
            Toast.makeText(XApplication.getContext(),toastInfo,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNeedToShowToast(){
        long difTime = System.currentTimeMillis() - lastTime;
        if(difTime / 1000 >= 2){
            lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    private static class ToastManagerImp{
        private static ToastManager mToastManager;
        public static ToastManager getToastManager(){
            if(mToastManager == null){
                mToastManager = new ToastManager();
            }
            return mToastManager;
        }
    }
}
