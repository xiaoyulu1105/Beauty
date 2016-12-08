package com.lu.beauty.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.lu.beauty.bean.ProductTitleBean;

import java.util.ArrayList;


/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductViewPagerAdapter extends FragmentPagerAdapter{
    private SparseArray<Fragment> arrayList;
    private ArrayList<String> beanArrayList;


    public ProductViewPagerAdapter(FragmentManager fm) {
        super(fm);
        arrayList = new SparseArray<>();
    }

    public void setBeanArrayList(ArrayList<String> beanArrayList) {
        this.beanArrayList = beanArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        if (arrayList.get(position) == null) {
            arrayList.put(position, ProductVpFragment.getInstance(position));
        }
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return beanArrayList == null ? 0: beanArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beanArrayList.get(position);
    }
}
