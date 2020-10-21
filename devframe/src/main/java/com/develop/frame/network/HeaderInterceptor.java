package com.develop.frame.network;

import com.develop.frame.base.BaseAppActivity;
import com.develop.frame.utils.AppConfig;
import com.develop.frame.utils.ILog;
import com.develop.frame.utils.ISp;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangning on 2019/12/23.
 */

public class HeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if (!ReceiveInterceptor.cookies.isEmpty()) {
            builder.addHeader("Cookie", ReceiveInterceptor.cookies);
        }
        String token = ISp.getStringValue(BaseAppActivity.mActivity, AppConfig.USER_TOKEN_KEY);
        if (!token.isEmpty()) {
            builder.addHeader("token", token);
        }
        //原始的url
        HttpUrl originUrl = request.url();
        List<String> headers = request.headers("url_name");
        if (null != headers && headers.size() > 0) {
            // 如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_name");
            String headerValue = headers.get(0);

            HttpUrl currentUrl = null;
            if (headerValue.contains("rong")) {
                currentUrl = HttpUrl.parse(AppConfig.RONG_URL);
            } else if (headerValue.contains("main")) {
                currentUrl = HttpUrl.parse(AppConfig.REQUEST_URL);
            } else {
                currentUrl = originUrl;
            }
            ILog.e("-----" + currentUrl.url());
            return chain.proceed(builder.url(originUrl
                    .newBuilder()
                    .scheme(currentUrl.scheme())
                    .host(currentUrl.host())
                    .port(currentUrl.port())
                    .build())
                    .build());
        }
        return chain.proceed(builder.build());
    }

}
