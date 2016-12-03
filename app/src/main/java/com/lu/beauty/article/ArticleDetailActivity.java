package com.lu.beauty.article;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.ArticleDetailBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.richtext.HtmlTextView;
import com.lu.beauty.tools.CircleDrawable;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by XiaoyuLu on 16/11/29.
 * 显示 画报详细信息 的Activity
 * 实现了侧滑退出
 */
public class ArticleDetailActivity extends BaseActivity implements View.OnClickListener, SwipeBackActivityBase, View.OnTouchListener {


    private int mGetId;

    private RelativeLayout mTopRl; // 显示和隐藏的相对布局
    private Button mTopReturnBtn;   // 返回
    private TextView mTopUsernameTV; // 显示的设计师名字
    private TextView mTopWhereTV;   // 设计师来自的地方
    private ImageView mTopDesignerIconIV; // 设计师头像

    private TextView mTitleTV; // title相关的组件
    private TextView mSubTitleTV;
    private ImageView mTitleImageIV;

    private ImageView mAuthorIconIV; // 编辑者相关的组件
    private TextView mAuthorNameTV;
    private TextView mAuthorSignTV;

    private HtmlTextView mHtmlTextView;
    private ScrollView mScrollView;
    private SwipeBackActivityHelper swipeBackActivityHelper; // 侧滑退出所用
    private MyArticleGestureDetectorListener mGestureDetectorListener;
    private GestureDetector mGestureDetector;


    @Override
    protected int getLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initViews() {

        mTopRl = (RelativeLayout) findViewById(R.id.article_detail_top_rl);
        mTopReturnBtn = bindView(R.id.article_detail_top_return_btn);
        mTopUsernameTV = bindView(R.id.article_detail_top_username_tv);
        mTopWhereTV = bindView(R.id.article_detail_top_where_tv);
        mTopDesignerIconIV = bindView(R.id.article_detail_top_icon_img);

        // 界面开始 与Title相关 的组件, 标题, 副标题, 图片
        mTitleTV = bindView(R.id.article_detail_title_title_tv);
        mSubTitleTV = bindView(R.id.article_detail_title_subtitle_tv);
        mTitleImageIV = bindView(R.id.article_detail_title_image_iv);

        // 与作者相关相关的组件, 头像, 姓名, 标签sign
        mAuthorIconIV = bindView(R.id.article_detail_author_icon_iv);
        mAuthorNameTV = bindView(R.id.article_detail_author_username_tv);
        mAuthorSignTV = bindView(R.id.article_detail_author_sign_tv);

        mHtmlTextView = (HtmlTextView) findViewById(R.id.article_detail_html_tv);
        mScrollView = (ScrollView) findViewById(R.id.article_detail_scroll_view);

        setClick(this, mTopReturnBtn, mTitleImageIV);
        mScrollView.setOnTouchListener(this);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        mGetId = intent.getIntExtra(ArticleFragment.INTENT_ID_KEY, 117);

        // 使用OkHTTP 请求画报的二级数据
        articleDetailDataRequest();

        // 实现侧滑 需要绑定 activity
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();

        // 初始化 自定义的 GestureDetectorListener 监听接口的对象
        mGestureDetectorListener = new MyArticleGestureDetectorListener(mTopRl);
        mGestureDetector = new GestureDetector(ArticleDetailActivity.this, mGestureDetectorListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_detail_top_return_btn:
                finish();

                break;
            case R.id.article_detail_title_image_iv:
                Toast.makeText(this, "点击了图片", Toast.LENGTH_SHORT).show();
                Log.d("ArticleDetailActivity", "点击了图片");

                break;
            default:
                Log.d("ArticleDetailActivity", "点击出错啦!");
                break;
        }
    }


    private void articleDetailDataRequest() {
        HttpUtil.getArticleDetailBean(mGetId, new ResponseCallBack<ArticleDetailBean>() {
            @Override
            public void onResponse(ArticleDetailBean articleDetailBean) {
                Log.d("ArticleDetailActivity", "画报第二级数据请求成功!");

                showHttpData(articleDetailBean);

            }

            @Override
            public void onError(Exception e) {
                Log.d("ArticleDetailActivity", "画报第二级数据请求失败!");
                Toast.makeText(ArticleDetailActivity.this, "请返回后重新请求!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 将网络请求获得的图片进行显示
     * @param articleDetailBean 网络请求的数据
     */
    private void showHttpData(ArticleDetailBean articleDetailBean) {

        ArticleDetailBean.DataBean dataBean = articleDetailBean.getData();

        // 显示 顶端的数据 的方法
        showTopData(dataBean);
        // 显示 Title相关的数据 的方法
        showTitleData(dataBean);
        // 显示 编辑者相关的数据 的方法
        showAuthorData(dataBean);

        // 获得 富文本数据
        String content = dataBean.getContent();
        // 显示 富文本数据
        mHtmlTextView.setHtmlFromString(content);



    }

    /**
     * 显示画报二级 author编辑者相关 的数据
     * @param dataBean 传递过来的数据: articleDetailBean.getData()
     */
    private void showAuthorData(ArticleDetailBean.DataBean dataBean) {
        // 获得 编辑者的信息: 头像, 名字, 标签sign
        String authorIcon = dataBean.getAuthor().getAvatar_url();
        String authorName = dataBean.getAuthor().getUsername();
        String authorSign = dataBean.getAuthor().getSign();

        // 显示 作者信息
        mAuthorNameTV.setText(authorName);
        mAuthorSignTV.setText(authorSign);
        Glide.with(ArticleDetailActivity.this).load(authorIcon).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                CircleDrawable drawable = new CircleDrawable(resource);
                mAuthorIconIV.setImageDrawable(drawable);
            }
        });
    }

    /**
     * 显示画报二级 title相关 的数据
     * @param dataBean 传递过来的数据: articleDetailBean.getData()
     */
    private void showTitleData(ArticleDetailBean.DataBean dataBean) {
        // 获得 大标题, 副标题, 图片
        String title = dataBean.getTitle();
        String subTitle = dataBean.getSub_title();
        String image_url = dataBean.getImage_url();

        // 显示 标题信息
        mTitleTV.setText(title);
        mSubTitleTV.setText(subTitle);
        Glide.with(ArticleDetailActivity.this).load(image_url).into(mTitleImageIV);

    }

    /**
     * 显示画报二级顶端Top 的数据
     * @param dataBean 传递过来的数据: articleDetailBean.getData()
     */
    private void showTopData(ArticleDetailBean.DataBean dataBean) {
        // 获取 设计师的简单信息: 名字, 城市, 头像
        String designerName = dataBean.getDesigners().get(0).getName();
        String designerCity = dataBean.getDesigners().get(0).getCity();
        String designerIcon = dataBean.getDesigners().get(0).getAvatar_url();

        // 显示 top 栏设计师的信息
        mTopUsernameTV.setText(designerName);
        mTopWhereTV.setText(designerCity);
        Glide.with(ArticleDetailActivity.this).load(designerIcon).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                CircleDrawable drawable = new CircleDrawable(resource);
                mTopDesignerIconIV.setImageDrawable(drawable);
            }
        });
    }


    /**
     * 复写 SwipeBackActivityBase接口的三个抽象方法:
     * getSwipeBackLayout, setSwipeBackEnable, scrollToFinishActivity.
     * 此外还需要手动复写另外两个方法: onPostCreate, findViewById
     */
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        // 修改了返回值
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        // 添加一行代码
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(@IdRes int id) {
        View v = super.findViewById(id);
        if (v == null && swipeBackActivityHelper != null) {
            return swipeBackActivityHelper.findViewById(id);
        }
        return v;
    }

    /**
     * 实现 接口View.OnTouchListener, 需要复写该方法
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
