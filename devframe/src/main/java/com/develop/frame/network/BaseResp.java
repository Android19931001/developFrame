package com.develop.frame.network;

import com.google.gson.annotations.SerializedName;

/**
 * wangning
 * 根据实际情况改动
 */
public class BaseResp<T> {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String msg;

    @SerializedName("data")
    public T data;

}
