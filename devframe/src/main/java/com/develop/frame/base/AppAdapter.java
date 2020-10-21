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

    public List<T> dataList;
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
        this.dataList = list;
        this.layoutId = layoutId;
        if (list == null)
            this.dataList = new ArrayList<>();
        else
            this.dataList = list;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int i) {
        return this.dataList.get(i);
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
        this.dataList = list;
        this.notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param list
     */
    @Override
    public void addData(List<T> list) {
        this.dataList.addAll(list);
        this.notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    @Override
    public void clearData() {
        this.dataList.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 添加单个数据
     *
     * @param t
     */
    @Override
    public void addOneData(T t) {
        this.dataList.remove(t);
        this.notifyDataSetChanged();
    }

    /**
     * 删除某个数据
     *
     * @param t
     */
    @Override
    public void removeData(T t) {
        this.dataList.remove(t);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        IViewHelper helper = IViewHelper.get(context, view, viewGroup, layoutId);
        return getChildView(i, helper);
    }

    public abstract View getChildView(int position, IViewHelper helper);
}
