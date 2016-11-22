package com.lu.beauty.internet;

/**
 * Created by dllo on 16/11/22.
 */

public interface ResponseCallBack<Bean> {

    void onResponse(Bean bean);

    void onError(Exception e);

}
