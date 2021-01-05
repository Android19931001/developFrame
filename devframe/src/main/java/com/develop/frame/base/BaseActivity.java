package com.develop.frame.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.develop.frame.R;
import com.develop.frame.bridge.ActivityPresenter;
import com.develop.frame.utils.ILoading;
import com.gyf.immersionbar.ImmersionBar;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by ht on 2017/9/8.
 */

public abstract class BaseActivity extends FragmentActivity implements ActivityPresenter {

    public Activity mContext;

    public FragmentManager fm;

    public ImmersionBar immersionBar = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        immersionBar = ImmersionBar.with(this);

        immersionBar
                .statusBarColor(R.color.transparent)
                .navigationBarColor(R.color.black_color)
                .fitsSystemWindows(true)
                .init();

        mContext = getActivity();

        fm = getSupportFragmentManager();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    /**
     * 隐藏标题栏和导航栏
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 跳转
     *
     * @param intent
     */

    public void goActivity(Intent intent) {
        startActivity(intent);
    }

    /**
     * 跳转
     *
     * @param intent
     * @param requestCode
     */
    public void jump(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }


    /**
     * 带有过场动画的activity跳转
     *
     * @param intent
     * @param isAnim
     */

    public void jump(Intent intent, boolean isAnim) {
        goActivity(intent);
        if (isAnim)
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    /**
     * 带有过场动画的activity跳转(从下往上)
     *
     * @param intent
     * @param isAnim
     */

    public void jumpActivity(Intent intent, boolean isAnim) {
        goActivity(intent);
        if (isAnim)
            overridePendingTransition(R.anim.play_in, 0);
    }


    /**
     * 查找控件不用强转
     *
     * @param viewId
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int viewId) {
        V v = this.findViewById(viewId);
        return v;
    }


    //隐藏弹出的输入法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isShouldHideInput(view, ev)) {
                hideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    public boolean isShouldHideInput(View view, MotionEvent event) {
        if (null != view && (view instanceof EditText)) {
            int[] pos = {0, 0};
            view.getLocationInWindow(pos);
            int left = pos[0];
            int top = pos[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //隐藏输入法
    public void hideSoftInput(IBinder token) {
        if (null != token) {
            InputMethodManager im = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ILoading.close();
    }

    /**
     * 停止加载
     */
    public void finishRefreshLoading(TwinklingRefreshLayout refreshLayout) {
        if (null != refreshLayout) {
            refreshLayout.finishLoadmore();
            refreshLayout.onFinishRefresh();
        }
    }
}
