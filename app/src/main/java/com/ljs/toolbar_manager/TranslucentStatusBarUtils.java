package com.ljs.toolbar_manager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ljs.sumery.R;
import com.ljs.utils.SystemUtils;

public class TranslucentStatusBarUtils {
	
	public static Activity getTopActivity(Activity activity){
		Activity parent = activity.getParent();
		Activity cur = activity;
		while(parent != null){
			cur = parent;
			parent = parent.getParent();
		}
		return cur;
	}
	
	public static void setStatusBarColor(Activity activity,int color){
		if(Build.VERSION.SDK_INT >= 19){
			ViewGroup contentView = (ViewGroup) activity.getWindow().getDecorView();
			View statusBarView = (View)contentView.getTag(R.id.id_toolbar);
			if(statusBarView == null){
				statusBarView = new View(activity);
				contentView.setTag(R.id.id_toolbar, statusBarView);
				ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						SystemUtils.getStatusBarHeight());
				contentView.addView(statusBarView, lp);
			}else{
				statusBarView.setVisibility(View.VISIBLE);
			}
			statusBarView.setBackgroundColor(color);
		}
	}

	public static void hideStatusBarView(Activity activity){
		if(Build.VERSION.SDK_INT >= 19){
			ViewGroup contentView = (ViewGroup) activity.getWindow().getDecorView();
            View statusBarView = (View)contentView.getTag(R.id.toolbar);
            if(statusBarView != null){
            	statusBarView.setVisibility(View.GONE);
            }
		}
	}
}
