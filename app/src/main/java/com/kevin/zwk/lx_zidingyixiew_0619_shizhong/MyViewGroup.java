package com.kevin.zwk.lx_zidingyixiew_0619_shizhong;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/19.
 */
public class MyViewGroup extends ViewGroup {

    private int mWidth;
    private int mHeight;
    private boolean bl;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有的子控件没有这个方法，getMeasureWidth不能执行
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth();
        Log.i("tag", "-----mWidth-----"+mWidth);
        mHeight = getHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//用于放置子控件的位置，因为子控件尚未绘制到界面上，这里需要使用测量宽高（getMeasureWidth）
        int offsetX = 0;
        int offsetY = 0;
        int xLength = 0;//一行已用的长度
        int leftX = 0;//一行剩余的长度
        int childMaxY = 0;//这一行子控件的最高值
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childMaxY = Math.max(childMaxY, childView.getMeasuredHeight());
            leftX = mWidth - xLength;//x轴剩余的空间
            Log.i("tag", "---------剩余长度------" + leftX+"--"+i);
            Log.i("tag", "---------Button长度------" +childView.getMeasuredWidth()+"--"+i);
            if (bl = (leftX < childView.getMeasuredWidth())) {
                //如果剩余空间不足
                xLength = 0;//换行这一行的宽度重新赋值
                offsetY += childMaxY;
                childMaxY = 0;
                offsetX = 0;
                Log.i("tag", "---------剩余空间是否不足------" + bl+"--"+i);

            }
            xLength += childView.getMeasuredWidth();
            childView.layout(offsetX, offsetY, childView.getMeasuredWidth() + offsetX, childView.getMeasuredHeight() + offsetY);
            offsetX += childView.getMeasuredWidth();

        }
    }
}
