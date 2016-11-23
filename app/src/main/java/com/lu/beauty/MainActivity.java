package com.lu.beauty;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lu.beauty.base.BaseActivity;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout mFrame;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;
    private FragmentManager mFragmentManager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        mFrame = (FrameLayout) findViewById(R.id.main_frame_layout);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        mRadioButton = (RadioButton) findViewById(R.id.main_article_radio);

        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {

        mRadioButton.setChecked(true);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, new ArticleFragment());
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFragmentManager = getSupportFragmentManager();
        switch (checkedId) {
            case R.id.main_article_radio:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout, new ArticleFragment()).commit();

                break;
            case R.id.main_product_radio:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout, new ProductFragment()).commit();

                break;
            case R.id.main_designer_radio:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout, new DesignerFragment()).commit();

                break;
            case R.id.main_my_radio:
                mFragmentManager.beginTransaction()
                        .replace(R.id.main_frame_layout, new MyFragment()).commit();

                break;
            default:
                Log.d("MainActivity", "出错啦!");
                break;
        }
    }
}
