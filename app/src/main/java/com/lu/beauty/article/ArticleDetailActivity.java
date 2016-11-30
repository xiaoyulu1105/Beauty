package com.lu.beauty.article;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.ArticleDetailBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

/**
 * Created by XiaoyuLu on 16/11/29.
 * 显示 画报详细信息 的Activity
 */
public class ArticleDetailActivity extends BaseActivity implements View.OnClickListener {


    private int mGetId;

    private RelativeLayout mTopRl;
    private Button mReturnBtn;
    private TextView mUsernameTV;
    private ImageView mAuthorIconIV;

    private WebView mWebView;

    @Override
    protected int getLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initViews() {

        mTopRl = (RelativeLayout) findViewById(R.id.article_detail_top_rl);
        mReturnBtn = bindView(R.id.article_detail_top_return_btn);
        mUsernameTV = bindView(R.id.article_detail_top_username_tv);
        mAuthorIconIV = bindView(R.id.article_detail_top_icon_img);
        mWebView = bindView(R.id.article_detail_webview);

        setClick(this, mReturnBtn);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        mGetId = intent.getIntExtra(ArticleFragment.INTENT_ID_KEY, 117);

        // TODO 进行网络请求
        ArticleDetailDataRequest();
    }

    private void ArticleDetailDataRequest() {
        HttpUtil.getArticleDetailBean(mGetId, new ResponseCallBack<ArticleDetailBean>() {
            @Override
            public void onResponse(ArticleDetailBean articleDetailBean) {
                Log.d("ArticleDetailActivity", "画报第二级数据请求成功!");

                ArticleDetailBean.DataBean dataBean = articleDetailBean.getData();
                String getWebLink = dataBean.getWeb_url();

                // 显示网页内容
                webViewMethod(getWebLink);

            }

            @Override
            public void onError(Exception e) {
                Log.d("ArticleDetailActivity", "画报第二级数据请求失败!");
            }
        });
    }

    /** 实现网页内容铺建的 相关操作 */
    private void webViewMethod(String getWebLink) {
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.loadUrl(getWebLink);
    }

    /** 显示网页时需要手动复写的方法, 添加 finish() 方法 */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_detail_top_return_btn:
                finish();

                break;
            default:

                Log.d("ArticleDetailActivity", "点击出错啦!");
                break;
        }
    }
}
