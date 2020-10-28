package com.develop.frame.view;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.develop.frame.R;

import java.lang.reflect.Field;

/**
 * Created by ht on 2017/9/8.
 */

public class SlidePopWindow implements View.OnClickListener {

    private static SlidePopWindow popWindow;

    private static PopupWindow window;

    private Activity activity;

    private View contentView;

    private int popWidth, popHeight;


    private String userName, account;

    private SlidePopWindow(String userName, String account) {
        this.userName = userName;
        this.account = account;
    }

    /**
     * 只在首页需要显示，所以可以使用单例模式
     *
     * @return
     */
    public static SlidePopWindow newInstance(String userName, String account) {
        if (popWindow == null) {
            return popWindow = new SlidePopWindow(userName, account);
        }
        return popWindow;
    }

    /**
     * 至于null;
     */
    public static void setNullValue() {
        popWindow = null;
    }


    public SlidePopWindow init(Activity activity) {
        this.activity = activity;
        setWiHe();
        contentView = LayoutInflater.from(activity).inflate(R.layout.silde_popu_layout, null);
        window = new PopupWindow(contentView, popWidth * 2 / 3, popHeight, true);
        window.setAnimationStyle(R.style.popuwin_transalte);
        window.setClippingEnabled(false);


        initView();
        return popWindow;
    }



    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(true);
                mLayoutInScreen.set(window, true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 得到宽高
     */
    private void setWiHe() {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        popWidth = metrics.widthPixels;
        popHeight = metrics.heightPixels;
    }

    public void showWindow(View view) {
        window.showAtLocation(view, Gravity.LEFT, 0, 0);
    }


    /**
     * 点击事件
     *
     * @param view
     */

    @Override
    public void onClick(View view) {

    }
}
