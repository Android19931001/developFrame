package com.develop.frame.network;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * wangning
 */
public class RespTransformer<T> implements SingleTransformer<T, T> {

    // 缓存T
    private static Map<Type, RespTransformer> map = new HashMap<>();

    /**
     * 统一创建实例，配合缓存，实例复用
     *
     * @param type 数据类型的type，作为缓存的key，参考自Gson，处理列表类型
     * @param <R>  数据类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <R> RespTransformer<R> newInstance(Type type) {
        RespTransformer<R> respTransformer = map.get(type);
        if (respTransformer == null) {
            respTransformer = new RespTransformer<>();
            map.put(type, respTransformer);
        }
        return respTransformer;
    }

    private RespTransformer() {

    }


    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<T, SingleSource<? extends T>>() {
                    @Override
                    public SingleSource<? extends T> apply(final T baseResp) throws Exception {
                        return Single.just(baseResp)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());//在主线程观察
                    }
                });
    }
}
