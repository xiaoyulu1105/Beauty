package com.lu.beauty.product.productitem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.ProductItemBean;
import com.lu.beauty.designer.DesignerItemPagerAdapter;
import com.lu.beauty.designer.PointImageView;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */
public class ProductItemActivity extends BaseActivity implements SwipeBackActivityBase {

    private ArrayList<String> pics;
    private String id;
    private ViewPager viewPager;
    private DesignerItemPagerAdapter viewPagerAdapter;
    private ArrayList<PointImageView> pointImageViews;
    private LinearLayout ll;
    private TextView productItemTv;
    private WebView productItemWeb;
    private ListView productItemImgLv;
    private ImgListViewAdapter adapter;
    private SwipeBackActivityHelper helper;


    @Override
    protected int getLayout() {
        return R.layout.activity_product_item;
    }

    @Override
    protected void initViews() {
        View v = LayoutInflater.from(this).inflate(R.layout.product_list_view_img_head, null);

        viewPager = bindView(v, R.id.activity_product_item_vp);
        ll = bindView(v, R.id.activity_product_item_ll);
        productItemTv = bindView(v, R.id.activity_product_item_tv);
        productItemWeb = bindView(v, R.id.activity_product_item_web);
        productItemImgLv = bindView(R.id.activity_product_item_list_view_img);


        productItemImgLv.addHeaderView(v);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        pics = intent.getStringArrayListExtra("pics");
        id = intent.getStringExtra("id");

        viewPagerAdapter = new DesignerItemPagerAdapter();
        String pic [] = new String[pics.size()];
        for (int i = 0; i < pics.size(); i++) {
            pic[i] = pics.get(i);
        }
        viewPagerAdapter.setUrls(pic);
        viewPager.setAdapter(viewPagerAdapter);
        initPoints();
        okHttp(id);

        adapter = new ImgListViewAdapter();
        productItemImgLv.setAdapter(adapter);

        helper = new SwipeBackActivityHelper(this);
        helper.onActivityCreate();
    }

    private void okHttp(String id) {
        HttpUtil.getProduckItemBean(id, new ResponseCallBack<ProductItemBean>() {
            @Override
            public void onResponse(ProductItemBean productItemBean) {

                productItemTv.setText(productItemBean.getData().getDigest());
                productItemWeb.loadUrl(productItemBean.getData().getDetail_url());

                Log.d("ProductItemActivity", "productItemBean.getData().getImages():" + productItemBean.getData().getImages());
                adapter.setArrayList(productItemBean.getData().getImages());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(ProductItemActivity.this, "网络请求失败, 请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 初始化点
    public void initPoints(){
        pointImageViews = new ArrayList<>();
        for (int i = 0;i < viewPagerAdapter.getImageCount();i++){
            PointImageView point = new PointImageView(this);
            pointImageViews.add(point);
            LinearLayout.LayoutParams layoutParams
                    = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT,1);
            ll.addView(point,layoutParams);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int current = position % viewPagerAdapter.getImageCount();
                for (PointImageView point : pointImageViews){
                    point.setSelected(false);
                }
                pointImageViews.get(current).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        helper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && helper != null)
            return helper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return helper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
