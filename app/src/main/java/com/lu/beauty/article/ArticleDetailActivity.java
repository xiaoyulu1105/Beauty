package com.lu.beauty.article;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.ArticleDetailBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.richtext.HtmlTextView;

/**
 * Created by XiaoyuLu on 16/11/29.
 * 显示 画报详细信息 的Activity
 */
public class ArticleDetailActivity extends BaseActivity implements View.OnClickListener {


    private int mGetId;

    private RelativeLayout mTopRl; // 显示和隐藏的相对布局
    private Button mTopReturnBtn;   // 返回
    private TextView mTopUsernameTV; // 显示的设计师名字
    private TextView mTopWhereTV;   // 设计师来自的地方
    private ImageView mTopAuthorIconIV; // 设计师头像
    private HtmlTextView mHtmlTextView;
    private TextView mTitleTV;
    private TextView mSubTitleTV;
    private ImageView mTitleImageIV;
    private ImageView mAuthorIconIV;
    private TextView mAuthorNameTV;
    private TextView mAuthorSignTV;


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
        mTopAuthorIconIV = bindView(R.id.article_detail_top_icon_img);

        // 界面开始 与Title相关 的组件, 标题, 副标题, 图片
        mTitleTV = bindView(R.id.article_detail_title_title_tv);
        mSubTitleTV = bindView(R.id.article_detail_title_subtitle_tv);
        mTitleImageIV = bindView(R.id.article_detail_title_image_iv);

        // 与作者相关相关的组件, 头像, 姓名, 标签sign
        mAuthorIconIV = bindView(R.id.article_detail_author_icon_iv);
        mAuthorNameTV = bindView(R.id.article_detail_author_username_tv);
        mAuthorSignTV = bindView(R.id.article_detail_author_sign_tv);

        mHtmlTextView = (HtmlTextView) findViewById(R.id.article_detail_html_tv);

        setClick(this, mTopReturnBtn, mTitleImageIV);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        mGetId = intent.getIntExtra(ArticleFragment.INTENT_ID_KEY, 117);

        articleDetailDataRequest();
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
        // 获取 设计师的简单信息: 名字, 城市, 头像
        String designerName = dataBean.getDesigners().get(0).getName();
        String designerCity = dataBean.getDesigners().get(0).getCity();
        String designerIcon = dataBean.getDesigners().get(0).getAvatar_url();
        // 获得 大标题, 副标题, 图片
        String title = dataBean.getTitle();
        String subTitle = dataBean.getSub_title();
        String image_url = dataBean.getImage_url();
        // 获得 编辑者的信息: 头像, 名字, 标签sign
        String authorIcon = dataBean.getAuthor().getAvatar_url();
        String authorName = dataBean.getAuthor().getUsername();
        String authorSign = dataBean.getAuthor().getSign();
        // 获得 富文本数据
        String content = dataBean.getContent();

        // 显示 富文本数据
        mHtmlTextView.setHtmlFromString(content);
        // 显示 标题信息
        mTitleTV.setText(title);
        mSubTitleTV.setText(subTitle);
        Glide.with(ArticleDetailActivity.this).load(image_url).into(mTitleImageIV);
        // 显示 作者信息
        Glide.with(ArticleDetailActivity.this).load(authorIcon).into(mAuthorIconIV);
        mAuthorNameTV.setText(authorName);
        mAuthorSignTV.setText(authorSign);
        // 显示 top 栏设计师的信息
        mTopUsernameTV.setText(designerName);
        mTopWhereTV.setText(designerCity);
        Glide.with(ArticleDetailActivity.this).load(designerIcon).into(mTopAuthorIconIV);
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
}
