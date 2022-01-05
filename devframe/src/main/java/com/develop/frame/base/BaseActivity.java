package com.develop.frame.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.develop.frame.R;
import com.develop.frame.bridge.ActivityPresenter;
import com.develop.frame.utils.ILoading;
import com.develop.frame.utils.IPhone;
import com.gyf.barlibrary.ImmersionBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

/**
 * Created by ht on 2017/9/8.
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityPresenter {


    public FragmentManager fm;

    public ImmersionBar immersionBar = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        immersionBar = ImmersionBar.with(this);
        initUInterface();
        fm = getSupportFragmentManager();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    /**
     * 初始化UI界面状态栏导航栏
     */
    private void initUInterface() {
        immersionBar
                .navigationBarColor(R.color.black_color)
                .fitsSystemWindows(true);
        if ("Meizu".equals(IPhone.iBrand())) {
            immersionBar.flymeOSStatusBarFontColor(R.color.black);
        } else {
            immersionBar.statusBarColor(R.color.black);
        }
        immersionBar.init();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        initView();
        initData();
        return super.onCreateView(name, context, attrs);
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



    /**
     * 跳转
     *
     * @param intent
     */

    public void jump(Intent intent) {
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
     * 带有过场动画的activity跳转左近右出
     *
     * @param intent
     * @param isAnim
     */

    public void jump(Intent intent, boolean isAnim) {
        jump(intent);
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
        jump(intent);
        if (isAnim)
            overridePendingTransition(R.anim.play_in, 0);
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

    /**
     * 判断点击的区域是否是输入框的以外的部分
     *
     * @param view
     * @param event
     * @return
     */
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
        ImmersionBar.with(this).destroy();
    }

}
