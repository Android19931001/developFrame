package com.develop.frame.bridge;

import android.app.Activity;

/**
 * Created by ht on 2017/9/8.
 */

public interface ActivityPresenter {

    Activity getActivity();

    void initView();

    void initData();

}
