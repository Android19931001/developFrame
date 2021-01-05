package com.develop.frame.bridge;

import java.util.List;

/**
 * Created by wangning on 2018/5/16.
 */

public interface AdapterHandlerOption<T> {

    void setData(List<T> list);

    void clearData();

    void addData(List<T> list);

    void removeData(T t);

    void addData(T t);
}
