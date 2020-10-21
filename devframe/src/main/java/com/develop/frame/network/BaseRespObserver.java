package com.develop.frame.network;

import android.os.Handler;
import android.os.Looper;

import com.develop.frame.utils.IGson;
import com.develop.frame.utils.ILoading;
import com.develop.frame.utils.ILog;
import com.develop.frame.utils.IToast;

import org.json.JSONObject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * wangning
 */
public abstract class BaseRespObserver<T> implements SingleObserver<T> {


    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onSuccess(T t) {
        doSuccess(t);
    }

    @Override
    public void onError(final Throwable throwable) {
        ILoading.close();
        if (null != throwable) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    try {
                        ILog.e(IGson.iJsonStr(throwable.getCause()));
                        ILog.e(IGson.iJsonStr(throwable));
                        if (null != throwable.getCause()) {
                            String causeJson = IGson.iJsonStr(throwable.getCause());
                            JSONObject causeObj = new JSONObject(causeJson);
                            if (null != causeObj) {
                                String errorCode = causeObj.getString("errno");
                                if ("110".equals(errorCode)) {
                                    IToast.showLong("错误码：" + errorCode + " -> 无法连接目标地址，连接超时！");
                                } else {
                                    IToast.showLong("错误码：" + errorCode + " -> 未知错误");
                                }
                            }
                        } else {
                            String throwableJson = IGson.iJsonStr(throwable);
                            JSONObject throwableObj = new JSONObject(throwableJson);
                            IToast.show(throwableObj.isNull("detailMessage") ? "服务链接失败" : throwableObj.getString("detailMessage"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public abstract void doSuccess(T t);


}
