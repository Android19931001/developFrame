package com.develop.frame.network;

import android.text.TextUtils;

import com.develop.frame.base.BaseAppActivity;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * wangning
 */
public class ApiGenerator {

    private static Retrofit retrofit;
    private static OkHttpClient.Builder builder;

    static {
        //设置https的请求证书
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            // Install the all-trusting trust manager
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        } catch (Exception ignore) {
        }

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(final String hostname, final SSLSession session) {
                return !TextUtils.isEmpty(hostname);
            }
        };
        Interceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(
                (null == BaseAppActivity.mActivity ? true : ((BaseAppActivity) BaseAppActivity.mActivity).IS_OPEN_LOG) ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
        Dispatcher dispatcher = new Dispatcher(Executors.newScheduledThreadPool(3));
        builder =
                new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .addNetworkInterceptor(loggingInterceptor)
                        .addInterceptor(new HeaderInterceptor())
                        .addInterceptor(new ReceiveInterceptor())
                        .dispatcher(dispatcher);
        if (null != sslContext) {
            builder.sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                    .hostnameVerifier(hostnameVerifier);
        }
    }

    /**
     * 初始化retrofit
     *
     * @param url
     */
    public static void init(String url) {
        OkHttpClient okHttpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


    /**
     * 创建接口
     */
    public static <S> S createApi(Class<S> apiClass) {
        return retrofit.create(apiClass);
    }

}