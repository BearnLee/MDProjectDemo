package com.ljs.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.ljs.application.XApplication;
import com.ljs.sumery.R;

public class ToastManager {

    private static ToastManager sInstance;

    public static ToastManager getInstance(){
        return sInstance;
    }

    /**
     * 兼容老代码
     * @param context
     * @return
     */
    public static ToastManager getInstance(Context context){
        return sInstance;
    }

    static{
        sInstance = new ToastManager();
    }

    private ToastCreator	mToastCreator;

    private long			mNetworkErrorTipLastTime;

    private Toast 			sToastLast;
    private int 			sResIdLast;
    private String			sStringLast;
    private long 			sShowTimeLast;

    private Context 		mContext;
    private final Handler 	mHandler;

    private ToastManager(){
        mContext = XApplication.getContext();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void setToastCreator(ToastCreator creator){
        mToastCreator = creator;
    }

    private Runnable mRunnable = new Runnable() {
        public void run() {
            sToastLast = null;
            if(mToastCreator != null){
                sToastLast = mToastCreator.createToast(mContext, mContext.getString(sResIdLast), Toast.LENGTH_SHORT);
            }
            if(sToastLast == null){
                sToastLast = Toast.makeText(mContext, sResIdLast, Toast.LENGTH_SHORT);
            }
            sToastLast.show();
            sShowTimeLast = System.currentTimeMillis();
        }
    };

    private Runnable mRunnableString = new Runnable() {
        @Override
        public void run() {
            sToastLast = null;
            if(mToastCreator != null){
                sToastLast = mToastCreator.createToast(mContext, sStringLast, Toast.LENGTH_SHORT);
            }
            if(sToastLast == null){
                sToastLast = Toast.makeText(mContext, sStringLast, Toast.LENGTH_SHORT);
            }
            sToastLast.show();
            sShowTimeLast = System.currentTimeMillis();
        }
    };

    private Runnable mRunnableNetworkErrorTipRunnable = new Runnable() {
        @Override
        public void run() {
            final long time = SystemClock.elapsedRealtime();
            if(time - mNetworkErrorTipLastTime > Toast.LENGTH_SHORT){
                Toast t = new Toast(mContext);
                t.setDuration(Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                ImageView iv = new ImageView(mContext);
                iv.setImageResource(R.mipmap.tip_error_network);
                t.setView(iv);
                t.show();
                mNetworkErrorTipLastTime = time;
            }
        }
    };

    public void show(int nResId){
        if(nResId == sResIdLast){
            if(System.currentTimeMillis() - sShowTimeLast < 2000){
                return;
            }
        }
//		if(sToastLast != null){
//			sToastLast.cancel();
//		}

        sResIdLast = nResId;
        mHandler.removeCallbacks(mRunnable);
        mHandler.post(mRunnable);
    }

    public void show(final String strText){
        if(TextUtils.isEmpty(strText)){
            return;
        }
        if(strText.equals(sStringLast)){
            if(System.currentTimeMillis() - sShowTimeLast < 2000){
                return;
            }
        }

//		if(sToastLast != null){
//			sToastLast.cancel();
//		}

        sStringLast = strText;
        mHandler.removeCallbacks(mRunnableString);
        mHandler.post(mRunnableString);
    }

    public void showNetworkErrorTip(){
        mHandler.removeCallbacks(mRunnableNetworkErrorTipRunnable);
        mHandler.post(mRunnableNetworkErrorTipRunnable);
    }

    public static interface ToastCreator{
        public Toast createToast(Context context, CharSequence text, int duration);
    }
}
