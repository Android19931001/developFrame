package com.develop.frame.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.develop.frame.R;
import com.develop.frame.bridge.FragmentPresenter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;


/**
 * Created by ht on 2017/9/26.
 */

public abstract class BaseFragment extends Fragment implements FragmentPresenter {


    public View contentView;

    public final int REQUESTCODE = 10010;
    public final int RESULTCODE = 10011;


    /**
     * 获取intent
     *
     * @return
     */
    public Intent getIntent() {
        return getActivity().getIntent();
    }



    /**
     * 查找控件
     *
     * @param viewId
     * @param <V>
     * @return
     */


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
     */

    public void goForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }


    /**
     * 带有过场动画的activity跳转
     *
     * @param intent
     * @param isAnim
     */

    public void goActivity(Intent intent, boolean isAnim) {
        goActivity(intent);
        if (isAnim)
            getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }


    /**
     * 带有过场动画的activity跳转(从下往上)
     *
     * @param intent
     * @param isAnim
     */

    public void goActivitys(Intent intent, boolean isAnim) {
        goActivity(intent);
        if (isAnim)
            getActivity().overridePendingTransition(R.anim.play_in, 0);
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
