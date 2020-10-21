package com.develop.frame.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.frame.bridge.AdapterHandlerOption;
import com.develop.frame.utils.RecyclerHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangning on 2018/5/16.
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHelper> implements AdapterHandlerOption<T> {

    public Context context;

    public List<T> dataList;

    private int layoutId;

    public RecyclerAdapter(Context context, List<T> dataList, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
        this.dataList = dataList;
        if (dataList == null)
            this.dataList = new ArrayList<>();
        else
            this.dataList = dataList;
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
     * 清除所有数据
     */
    @Override
    public void clearData() {
        this.dataList.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 想dataList中添加数据
     *
     * @param list
     */
    @Override
    public void addData(List<T> list) {
        this.dataList.addAll(list);
        this.notifyDataSetChanged();
    }

    /**
     * 删除单个数据
     *
     * @param t
     */
    @Override
    public void removeData(T t) {
        this.dataList.remove(t);
        this.notifyDataSetChanged();
    }

    /**
     * 添加单个数据
     *
     * @param t
     */
    @Override
    public void addOneData(T t) {
        this.dataList.add(t);
        this.notifyDataSetChanged();
    }

    /**
     * 加载视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(this.context).inflate(layoutId, parent, false);
        return new RecyclerHelper(contentView);
    }

    @Override
    public void onBindViewHolder(RecyclerHelper helper, int position) {
        onBindViewData(helper, position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 绑定数据
     *
     * @param helper
     * @param position
     */
    public abstract void onBindViewData(RecyclerHelper helper, int position);
}
