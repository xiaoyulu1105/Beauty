package com.lu.beauty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by XiaoyuLu on 16/11/26.
 *
 * 用于大家测试的
 * 运行这个java文件 居然不打印集合的数据 why?
 */

public class Test {

    public static void main(String[] args) {
        final ArrayList<String> arrayList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                method(2000, arrayList);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                method(1000, arrayList);
            }
        }).start();


        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i) + " ");
        }
    }

    /**
     * 沉睡 i 时间后像集合添加数据
     * @param i
     * @param arrayList
     */
    private static void method(int i, ArrayList<String> arrayList) {

        try {
            Thread.sleep(i);
            arrayList.add("数据" + i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
