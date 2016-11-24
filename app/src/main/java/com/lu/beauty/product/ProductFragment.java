package com.lu.beauty.product;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class ProductFragment extends BaseFragment {
    private TabLayout productTabLayout;
    private ViewPager productViewPager;
    private String [] s = {"Daily", "手势", "包袋", "鞋履", "Men", "配饰", "其他"};



    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initView() {
        productTabLayout = bindView(R.id.product_tab_layout);
        productViewPager = bindView(R.id.product_view_pager);
    }

    @Override
    protected void initData() {

        ProductViewPagerAdapter adapter = new ProductViewPagerAdapter(getChildFragmentManager(), s);
        productViewPager.setAdapter(adapter);
        productTabLayout.setupWithViewPager(productViewPager);
        productTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        productTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }
}
