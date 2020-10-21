package com.develop.frame.utils;

import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wangning on 2018/5/16.
 */

public class RecyclerHelper extends RecyclerView.ViewHolder {


    public RecyclerHelper(View itemView) {
        super(itemView);
    }

    /**
     * 获取ImageView
     *
     * @param viewId
     * @return
     */
    public ImageView getImgView(int viewId) {
        return ((ImageView) getView(viewId));
    }

    /**
     * 给TextView 设置内容
     *
     * @param viewId
     * @param content
     */
    public void setText(int viewId, String content) {
        TextView tv = getView(viewId);
        tv.setText(content);
    }

    /**
     * 给TextView 设置内容(带有富文本)
     *
     * @param viewId
     * @param spanned
     */
    public void setText(int viewId, Spanned spanned) {
        TextView tv = getView(viewId);
        tv.setText(spanned);
    }


    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setViewListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
    }

    /**
     * 是否隐藏
     *
     * @param viewId
     * @param isVisible
     */
    public void setVisible(int viewId, int isVisible) {
        getView(viewId).setVisibility(isVisible);
    }

    /**
     * 获取View
     *
     * @param viewId
     * @param <V>
     * @return
     */
    public <V extends View> V getView(int viewId) {
        return this.itemView.findViewById(viewId);
    }
}
