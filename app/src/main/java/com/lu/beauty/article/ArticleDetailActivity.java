package com.lu.beauty.article;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;

/**
 * Created by XiaoyuLu on 16/11/29.
 * 显示 画报详细信息 的Activity
 */
public class ArticleDetailActivity extends BaseActivity implements View.OnClickListener {


    private int mGetId;
    private Button mReturnBtn;
    private WebView mWebView;

    @Override
    protected int getLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initViews() {

        Intent intent = getIntent();
        mGetId = intent.getIntExtra(ArticleFragment.INTENT_ID_KEY, 117);

        mReturnBtn = bindView(R.id.article_detail_return_btn);
        mWebView = bindView(R.id.article_detail_webview);

        setClick(this, mReturnBtn);

    }

    @Override
    protected void initData() {

        // TODO 进行网络请求
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_detail_return_btn:
                finish();

                break;
            default:

                Log.d("ArticleDetailActivity", "点击出错啦!");
                break;
        }
    }
}
