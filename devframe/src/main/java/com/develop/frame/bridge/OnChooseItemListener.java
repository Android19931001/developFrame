package com.develop.frame.bridge;

import java.util.List;

/**
 * Created by wangning on 2020/10/22.
 */

public abstract class OnChooseItemListener<T> implements OnSelectItemListener<T> {


    @Override
    public void singleSelectItem(int position, T item) {

    }

    @Override
    public void multiSelectItem(List<T> selectedItems) {

    }
}
