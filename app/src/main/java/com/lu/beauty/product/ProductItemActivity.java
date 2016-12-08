package com.lu.beauty.product;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

import java.util.ArrayList;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */
public class ProductItemActivity extends BaseActivity{

    private ArrayList<String> pics;
    private String id;

    @Override
    protected int getLayout() {
        return R.layout.activity_product_item;
    }

    @Override
    protected void initViews() {
//        ViewPager
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        pics = intent.getStringArrayListExtra("pics");
        id = intent.getStringExtra("id");

        Log.d("ProductItemActivity11", "pics:" + pics);
        Log.d("ProductItemActivity11", id);
    }
}
