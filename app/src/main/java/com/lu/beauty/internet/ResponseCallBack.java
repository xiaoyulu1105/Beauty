package com.lu.beauty.internet;

/**
 * Created by dllo on 16/11/22.
 * 网络请求用到的接口@wqs
 */

public interface ResponseCallBack<Bean> {

    void onResponse(Bean bean);

    void onError(Exception e);

}
