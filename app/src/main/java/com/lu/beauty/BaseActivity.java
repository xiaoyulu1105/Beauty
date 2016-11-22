package com.lu.beauty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/11/22.
 * Activity基类@wqs
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());

        initViews();

        initData();

    }
    // 绑定布局
    protected abstract int getLayout();
    // 初始化组件
    protected abstract void initViews();
    // 初始化数据
    protected abstract void initData();

    // findViewById
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }

    // 设置监听
    protected void setClick(View.OnClickListener clickListener, View ... views){
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }

}
