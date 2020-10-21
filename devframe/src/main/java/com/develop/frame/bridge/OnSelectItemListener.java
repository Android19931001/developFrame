package com.develop.frame.bridge;

import java.util.List;

/**
 * Created by wangning on 2018/9/11.
 */

public interface OnSelectItemListener {
    void singleSelectItem(int position, String item);

    void multiSelectItem(List<Integer> positions,List<String> selectedItems);
}
