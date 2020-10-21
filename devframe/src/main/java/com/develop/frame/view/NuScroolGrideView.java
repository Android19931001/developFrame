package com.develop.frame.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by wangning on 2018/1/10.
 */

public class NuScroolGrideView extends GridView {
    //防止计算多次导致界面显示错误
    public boolean isOnMeasure;

    public NuScroolGrideView(Context context) {
        super(context);
    }

    public NuScroolGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NuScroolGrideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = true;
        int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }
}
