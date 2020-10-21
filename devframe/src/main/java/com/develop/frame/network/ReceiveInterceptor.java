package com.develop.frame.network;

import com.develop.frame.utils.ILog;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wangning on 2019/12/23.
 */

public class ReceiveInterceptor implements Interceptor {

    public static String cookies = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            List<String> headers = originalResponse.headers("Set-Cookie");
            if (null != headers && headers.size() > 0) {
                cookies = headers.get(0);
            }
        }
        return originalResponse;
    }
}
