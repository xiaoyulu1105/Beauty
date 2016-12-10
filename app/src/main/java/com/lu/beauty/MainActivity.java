package com.lu.beauty;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lu.beauty.article.ArticleFragment;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.ArticleBean;
import com.lu.beauty.designer.DesignerFragment;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.my.MyFragment;
import com.lu.beauty.product.ProductFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout mFrame;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;
    private FragmentManager mFragmentManager;

    public static final String BUNDLE_IDS_KEY = "ids";
    public static final String BUNDLE_TITLES_KEY = "titles";
    public static final String BUNDLE_IMAGE_URLS_KEY = "imageUrls";
    private ArticleFragment mFragment;

    private long firstTime=0;



    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        mFrame = (FrameLayout) findViewById(R.id.main_frame_layout);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_radio_group);
        mRadioButton = (RadioButton) findViewById(R.id.main_article_radio);

        mFragmentManager = getSupportFragmentManager();
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {


        mRadioButton.setChecked(true);
        ArticleDateRequest();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(MainActivity.this,"啊啊啊  你不要离开我",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_article_radio:
                // 请求画报数据
                ArticleDateRequest();

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

    /**
     * 请求画报数据, 并通过Fragment的SetArgument方法将数据传递过去
     *
     */
    private void ArticleDateRequest() {
        HttpUtil.getArticleBean(1, new ResponseCallBack<ArticleBean>() {
            @Override
            public void onResponse(ArticleBean articleBean) {

                Log.d("MainActivity11", "画报数据请求成功!");

                ArrayList<ArticleBean.DataBean.ArticlesBean> arrayList =
                        (ArrayList<ArticleBean.DataBean.ArticlesBean>)
                                articleBean.getData().getArticles();

                ArrayList<String> titles = new ArrayList<>();
                ArrayList<String> imageUrls = new ArrayList<>();
                ArrayList<Integer> ids = new ArrayList<>();

                for (int i = 0; i < arrayList.size(); i++) {
                    // 获得设计师的头像和名字
                    String getAvatar_url = arrayList.get(i).getAuthor().getAvatar_url();
                    String getUsername = arrayList.get(i).getAuthor().getUsername();
                    // 获得画报 标题, 副标题 , 图片和id
                    String getTitle = arrayList.get(i).getTitle();
                    String getSubTitle = arrayList.get(i).getSub_title();
                    String getImageUrl = arrayList.get(i).getImage_url();
                    int getId = arrayList.get(i).getId();

                    titles.add(getTitle);
                    imageUrls.add(getImageUrl);
                    ids.add(getId);
                }

                mFragment = new ArticleFragment();

                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList(BUNDLE_IDS_KEY, ids);
                bundle.putStringArrayList(BUNDLE_TITLES_KEY, titles);
                bundle.putStringArrayList(BUNDLE_IMAGE_URLS_KEY, imageUrls);
                mFragment.setArguments(bundle);

                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.main_frame_layout, mFragment);
                transaction.commit();
            }

            @Override
            public void onError(Exception e) {
                Log.d("MainActivity11", "画报数据请求失败!");
            }
        });
    }
}
