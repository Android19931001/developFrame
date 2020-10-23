package com.develop.frame.bridge;

import java.util.List;

/**
 * Created by wangning on 2018/9/11.
 */

public interface OnSelectItemListener<T> {


    //单选
    void singleSelectItem(int position, T item);

    //多选
    void multiSelectItem(List<T> selectedItems);
}
