package com.develop.frame.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develop.frame.bridge.AdapterHandlerOption;
import com.develop.frame.utils.IViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ht on 2017/9/8.
 */

public abstract class AppAdapter<T> extends BaseAdapter implements AdapterHandlerOption<T> {

    public List<T> data;
    public Context context;

    private int layoutId;


    /**
     * 构造函数
     *
     * @param context
     * @param list
     * @param layoutId
     */
    public AppAdapter(Context context, List<T> list, int layoutId) {
        this.context = context;
        this.data = list;
        this.layoutId = layoutId;
        if (null == list)
            this.data = new ArrayList<>();
        else
            this.data = list;
    }


    @Override
    public int getCount() {
        return null == data ? 0 : data.size();
    }

    @Override
    public T getItem(int i) {
        return null == this.data ? null : this.data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    @Override
    public void setData(List<T> list) {
        this.data = list;
        this.notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param list
     */
    @Override
    public void addData(List<T> list) {
        this.data.addAll(list);
        this.notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    @Override
    public void clearData() {
        this.data.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 添加单个数据
     *
     * @param t
     */
    @Override
    public void addData(T t) {
        this.data.remove(t);
        this.notifyDataSetChanged();
    }

    /**
     * 删除某个数据
     *
     * @param t
     */
    @Override
    public void removeData(T t) {
        this.data.remove(t);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        IViewHelper helper = IViewHelper.get(context, view, viewGroup, layoutId);
        return getChildView(i, helper);
    }

    public abstract View getChildView(int position, IViewHelper helper);
}
