package com.develop.frame.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wangning on 2019/1/29.
 */

public class BaseResponse {

    @SerializedName("createdBy")
    public String createdBy;

    @SerializedName("createdDate")
    public String createdDate;

    @SerializedName("lastModifiedBy")
    public String lastModifiedBy;

    @SerializedName("lastModifiedDate")
    public String lastModifiedDate;

    @SerializedName("versionLock")
    public int versionLock;
}
