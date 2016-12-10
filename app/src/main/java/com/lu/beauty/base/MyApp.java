package com.lu.beauty.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by XiaoyuLu on 16/11/25.
 */

public class MyApp extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        //注册Bmob
     // Bmob.initialize(this, "3256dd5402217b9c2ffa7179fd939dc9");

        // 食物派
//       Bmob.initialize(this,"a35a84d79394af8be41cdc009fd76225");// 最美

        // 小玉的 最美 数据库
        Bmob.initialize(this, "e418e0e694af7e7713e9a830480a7f8a");

      //mob初始化
        ShareSDK.initSDK(this,"197416892563e");
    }

    public static Context getContext(){
        return sContext;
    }
}
