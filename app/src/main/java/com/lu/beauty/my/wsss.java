package com.lu.beauty.my;

/**
 * Created by  AngleXiao on 16/12/9.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 * 懒汉式
 */
public class wsss {
    private static wsss ourInstance;

    public static wsss getInstance() {
        if (ourInstance == null){
            synchronized (wsss.class){
                if (ourInstance == null){
                    ourInstance = new wsss();
                }
            }
        }
        return ourInstance;
    }

    private wsss() {
    }
}
