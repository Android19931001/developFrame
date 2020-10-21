package com.develop.frame.network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.develop.frame.bridge.OkCallBack;
import com.develop.frame.utils.ILog;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by wangning on 2018/3/6.
 */

public class OkHttpUtil {

    private OkHttpClient mClient;
    private Request.Builder mRequestBuild;
    private FormBody.Builder mBody;

    private String WRONGMESSAGE = "接口请求码>>>%s<<<发生错误";

    private final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static OkHttpUtil getInstance() {
        return new OkHttpUtil();
    }

    /**
     * 实例化请求工具
     */
    private OkHttpUtil() {
        mClient = new OkHttpClient
                .Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        mRequestBuild = new Request.Builder();
        mBody = new FormBody.Builder();
    }

    /**
     * 添加请求参数
     *
     * @param map
     * @return
     */
    private RequestBody getRequestBody(Map map) {
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            mBody.add(key, map.get(key).toString());
        }
        return mBody.build();
    }

    /**
     * 添加上传图片的请求体
     *
     * @param map
     * @return
     */

    private RequestBody getImgBody(Map map) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Object object = map.get(key);
            if (object instanceof File) {
                File file = ((File) map.get(key));
                builder.addFormDataPart("img", file.getName(),
                        RequestBody.create(MediaType.parse("image/png"), file));
            } else {
                builder.addFormDataPart(key, map.get(key).toString());
            }
        }
        return builder.build();
    }


    /**
     * 请求发生错误时的处理
     *
     * @param requestCode
     * @param e
     * @param callBack
     */
    private void requestWrong(int requestCode, Exception e, OkCallBack callBack) {
        if (mClient == null)
            callBack.getResponse(requestCode, "当前网络不可用", true);
        else if (e instanceof TimeoutException)
            callBack.getResponse(requestCode, "请求服务器超时", true);
        else
            callBack.getResponse(requestCode, e.getMessage(), true);
    }


    private final int GENEROUSNESS = 0;

    /**
     * get请求
     *
     * @param requestCode
     * @param url
     * @param callBack
     */
    public void get(final int requestCode, String url, final OkCallBack callBack) {
        try {
            mClient.newCall(mRequestBuild
                    .get()
                    .url(url)
                    .build()).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    requestWrong(requestCode, e, callBack);
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == GENEROUSNESS) {
                                if (judgeRequest(result)) {
                                    callBack.getResponse(requestCode, result, false);
                                } else {
                                    callBack.getResponse(requestCode, String.format(WRONGMESSAGE, requestCode), true);
                                }
                            }
                        }
                    }.sendEmptyMessage(GENEROUSNESS);
                }
            });
        } catch (Exception e) {
            requestWrong(requestCode, e, callBack);
        }
    }

    private final int POSTPROCESSOR = 1;

    /**
     * post请求
     *
     * @param requestCode
     * @param url
     * @param map
     * @param callBack
     */
    public void post(final int requestCode, String url, Map map, final OkCallBack callBack) {
        try {
            int number = new Random(12).nextInt();
            long timeMillis = System.currentTimeMillis();
            mClient.newCall(mRequestBuild
                    .url(url)
                    .addHeader("Nonce", number + "")
                    .addHeader("Timestamp", timeMillis + "")
                    .post(getRequestBody(map))
                    .build()).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    requestWrong(requestCode, e, callBack);
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == POSTPROCESSOR) {
                                if (judgeRequest(result)) {
                                    callBack.getResponse(requestCode, result, false);
                                } else {
                                    callBack.getResponse(requestCode, String.format(WRONGMESSAGE, requestCode), true);
                                }
                            }
                        }
                    }.sendEmptyMessage(POSTPROCESSOR);
                }
            });
        } catch (Exception e) {
            requestWrong(requestCode, e, callBack);
        }
    }

    private final int JACQUETTE = 2;

    /**
     * post请求，向网页提交一个json参数
     *
     * @param requestCode
     * @param url
     * @param map
     * @param callBack
     */
    public void postJson(final int requestCode, String uuid, String url, Object map, final OkCallBack callBack) {
        try {
            String json = new Gson().toJson(map);
            mClient.newCall(mRequestBuild
                    .url(url)
                    .addHeader("Cookie", uuid)
                    .post(RequestBody.create(JSON, json))
                    .build()).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    requestWrong(requestCode, e, callBack);
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == JACQUETTE) {
                                if (judgeRequest(result)) {
                                    callBack.getResponse(requestCode, result, false);
                                } else {
                                    callBack.getResponse(requestCode, String.format(WRONGMESSAGE, requestCode), true);
                                }
                            }
                        }
                    }.sendEmptyMessage(JACQUETTE);
                }
            });
        } catch (Exception e) {
            requestWrong(requestCode, e, callBack);
        }
    }

    private final int IMPERCEPTIBLE = 3;

    /**
     * 向服务器提交图片文件
     *
     * @param requestCode
     * @param url
     * @param map
     * @param callBack
     */
    public void submitFile(final int requestCode, String url, Map map, final OkCallBack callBack) {
        try {
            mClient.newCall(mRequestBuild
                    .url(url)
                    .post(getImgBody(map))
                    .build()).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    requestWrong(requestCode, e, callBack);

                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            if (msg.what == IMPERCEPTIBLE) {
                                if (judgeRequest(result)) {
                                    callBack.getResponse(requestCode, result, false);
                                } else {
                                    callBack.getResponse(requestCode, String.format(WRONGMESSAGE, requestCode), true);
                                }
                            }
                        }
                    }.sendEmptyMessage(IMPERCEPTIBLE);
                }
            });
        } catch (Exception e) {
            requestWrong(requestCode, e, callBack);
        }
    }

    /**
     * 判断是否是json数据
     */
    private boolean judgeRequest(String result) {
        ILog.logJson("请求返回的数据：" + result);
        if (result.startsWith("{") || result.startsWith("[")) {
            return true;
        } else {
            return false;
        }
    }
}
