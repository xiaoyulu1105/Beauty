package com.lu.beauty.liteorm;

import android.os.Handler;

import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by XiaoyuLu on 16/11/26.
 *
 * 数据库LiteOrm的工具类
 */

public class DBTools {

    private SingletonUtils mSingletonUtils;
    private ThreadPoolExecutor mThreadPoolExecutor;
    private LiteOrm mLiteOrm;
    private Handler mHandler;

    public DBTools() {
        mSingletonUtils = SingletonUtils.getInstance(DBValues.DB_NAME);
        mThreadPoolExecutor = mSingletonUtils.getThreadPoolExecutor();
        mLiteOrm = mSingletonUtils.getLiteOrm();
        mHandler = mSingletonUtils.getHandler();
    }

    /**
     * 插入数据库的 泛型 方法
     */
    public <T> void insert(T t) {
        mThreadPoolExecutor.execute(new InsertRunnable(t));
    }

    private class InsertRunnable<T> implements Runnable {
        private T t;

        public InsertRunnable(T t) {
            this.t = t;
        }

        @Override
        public void run() {
            mLiteOrm.insert(t);
        }
    }

    /**
     * 删除 数据库所有数据 泛型方法实现
     */
    public <T> void deleteAllData(Class<T> tClass) {
        mThreadPoolExecutor.execute(new DeleteAllDataRunnable(tClass));
    }

    private class DeleteAllDataRunnable<T> implements Runnable {
        private Class<T> tClass;

        public DeleteAllDataRunnable(Class<T> tClass) {
            this.tClass = tClass;
        }

        @Override
        public void run() {
            mLiteOrm.delete(tClass);
        }
    }

    // 查询数据库的 泛型 方法

    /**
     * 使用接口回调将数据返回到 主线程, 所以返回值不需要有, 也不应该有 !!!
     */
    public <T> void queryAllData(Class<T> tClass, OnQueryListener<T> onQueryListener) {

        mThreadPoolExecutor.execute(new QueryRunnable<>(tClass, onQueryListener));
    }


    /**
     * 实现 查询数据库的  外层 Runnable 泛型 类
     */
    private class QueryRunnable<T> implements Runnable {

        private Class<T> tClass;
        private OnQueryListener onQueryListener;

        public QueryRunnable(Class<T> tClass, OnQueryListener onQueryListener) {
            this.tClass = tClass;
            this.onQueryListener = onQueryListener;
        }

        @Override
        public void run() {
            ArrayList<T> tArrayList = mLiteOrm.query(tClass);
            mHandler.post(new CallbackRunnable<>(onQueryListener, tArrayList));
        }
    }

    /**
     * 实现用 Handler将线程从子线程切换到主线程, 用接口对象将数据存入接口
     */
    private class CallbackRunnable<T> implements Runnable {

        private OnQueryListener onQueryListener;
        private ArrayList<T> tArrayList;

        public CallbackRunnable(OnQueryListener onQueryListener, ArrayList<T> tArrayList) {
            this.onQueryListener = onQueryListener;
            this.tArrayList = tArrayList;
        }

        @Override
        public void run() {
            onQueryListener.onQuery(tArrayList);
        }
    }

    /**
     * 定义 查询数据库的 泛型 接口
     */
    public interface OnQueryListener<T> {
        void onQuery(ArrayList<T> tArrayList);
    }

}
