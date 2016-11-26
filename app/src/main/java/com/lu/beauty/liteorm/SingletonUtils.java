package com.lu.beauty.liteorm;

import android.os.Handler;
import android.os.Looper;

import com.litesuits.orm.LiteOrm;
import com.lu.beauty.base.MyApp;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by XiaoyuLu on 16/11/25.
 *
 * 单例实现 线程池, LiteOrm 数据库, Handler的初始化
 * liteOrm的使用需要导jar文件
 */

public class SingletonUtils {

    private static SingletonUtils sSingletonUtils;
    private ThreadPoolExecutor mThreadPoolExecutor;
    private LiteOrm mLiteOrm;
    private Handler mHandler;

    private SingletonUtils(String DBName) {
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1; // cpu核数 + 1
        int maxPoolSize = corePoolSize * 2 + 1; // 核心线程数*2 + 1
        mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60l,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>()); // 任务队列无界

        mLiteOrm = LiteOrm.newSingleInstance(MyApp.getContext(), DBName);
        // Handler 只能在 主线程里 new
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static SingletonUtils getInstance(String DBName) {
        if (sSingletonUtils == null) {
            synchronized (SingletonUtils.class) {
                if (sSingletonUtils == null) {
                    sSingletonUtils = new SingletonUtils(DBName);
                }
            }
        }

        return sSingletonUtils;
    }

    /* 获取 线程池的 方法 */
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return mThreadPoolExecutor;
    }

    /* 获取 LiteOrm 数据库 的方法*/
    public LiteOrm getLiteOrm() {
        return mLiteOrm;
    }

    /* 获取 Handler 的方法 */
    public Handler getHandler() {
        return mHandler;
    }

}
