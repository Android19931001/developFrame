package com.develop.frame.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ht on 2017/9/8.
 */

public class IViewHelper {

    private View contentView;

    //存储已经添加过的view可以直接返回不需要重新查找
    private SparseArray<View> sparseArray;

    /**
     * 初始化实例
     *
     * @param context
     * @param parent
     * @param layoutId
     */

    public IViewHelper(Context context, ViewGroup parent, int layoutId) {
        contentView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        sparseArray = new SparseArray<>();
        contentView.setTag(this);
    }

    /**
     * 初始化
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */

    public static IViewHelper get(Context context, View convertView,
                                  ViewGroup parent, int layoutId) {
        if (null == convertView) {
            return new IViewHelper(context, parent, layoutId);
        }
        return (IViewHelper) convertView.getTag();
    }

    /**
     * 直接设置文本
     *
     * @param viewId
     * @param content
     */
    public void setText(int viewId, String content) {
        TextView tv = getViews(viewId);
        tv.setText(content);
    }

    /**
     * 根据id获得控件
     * 还可用于点击事件
     *
     * @param viewId
     * @param <V>
     * @return
     */
    public <V extends View> V getViews(int viewId) {
        if (sparseArray.get(viewId) == null) {
            View childView = contentView.findViewById(viewId);
            sparseArray.put(viewId, childView);
            return (V) childView;
        } else {
            return (V) sparseArray.get(viewId);
        }
    }

    /**
     * 返回item的View
     *
     * @return
     */
    public View getView() {
        return contentView;
    }

    /**
     * 获取ImageView控件
     *
     * @param viewId
     * @return
     */
    public ImageView getImage(int viewId) {
        return getViews(viewId);
    }

    /**
     * 给控件添加点击事件
     *
     * @param viewId
     * @param clickListener
     */
    public void setClickListener(int viewId, View.OnClickListener clickListener) {
        getViews(viewId).setOnClickListener(clickListener);
    }
}
