package com.develop.frame.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wangning on 2020/8/31.
 */

public class NoBodyRes implements Serializable{

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String msg;
}
