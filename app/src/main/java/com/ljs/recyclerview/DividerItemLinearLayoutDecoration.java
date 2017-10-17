package com.ljs.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 暮雨而歌 on 2017/10/16.
 */

public class DividerItemLinearLayoutDecoration extends RecyclerView.ItemDecoration{
    private final static int[] ATTRS = new int[]{android.R.attr.listDivider};

    private final static int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private final static int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDrawable;
    private int      mOrientation;
    public DividerItemLinearLayoutDecoration(Context context, int orientation){
        final TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDrawable = ta.getDrawable(0);
        ta.recycle();
        setOritation(orientation);

    }

    private void setOritation(int orientation){
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == VERTICAL_LIST){
            drawVertical(c,parent);
        }else if(mOrientation == HORIZONTAL_LIST){
            drawHorizontal(c,parent);
        }
    }

    private void drawVertical(Canvas canvas,RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() + parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + lp.topMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();

            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(canvas);
        }
    }

    private void drawHorizontal(Canvas canvas,RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getBottom() + parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + lp.rightMargin;
            int right = left + mDrawable.getIntrinsicWidth();

            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if(mOrientation == HORIZONTAL_LIST){
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),0);
        }else{
            outRect.set(0,0,0,mDrawable.getIntrinsicHeight());
        }
    }
}
