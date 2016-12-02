package com.lu.beauty.product;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.ProductTitleBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

import java.util.ArrayList;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class ProductFragment extends BaseFragment {
    private TabLayout productTabLayout;
    private ViewPager productViewPager;
    private ArrayList<String> arrayList;
    private ProductViewPagerAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initView() {
        productTabLayout = bindView(R.id.product_tab_layout);
        productViewPager = bindView(R.id.product_view_pager);

        arrayList = new ArrayList<>();
    }

    @Override
    protected void initData() {

        adapter = new ProductViewPagerAdapter(getChildFragmentManager());
        productTabLayout.setupWithViewPager(productViewPager);
        productTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        productTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        HttpUtil.getProductTitleBean(new ResponseCallBack<ProductTitleBean>() {
            @Override
            public void onResponse(ProductTitleBean productTitleBean) {
                Log.d("打印这个", "productTitleBean.getData().getCategories().size():" + productTitleBean.getData().getCategories().size());
                for (int i = 0; i < productTitleBean.getData().getCategories().size(); i++) {

                    arrayList.add(productTitleBean.getData().getCategories().get(i).getName());
                }
                    adapter.setBeanArrayList(arrayList);
                    productViewPager.setAdapter(adapter);

            }

            @Override
            public void onError(Exception e) {
                Log.d("打印这个", "网络请求失败");
            }
        });
        arrayList.add(0, "Daily");
//        adapter.setS(arrayList);
        adapter.notifyDataSetChanged();




    }
}
