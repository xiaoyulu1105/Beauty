package com.lu.beauty.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;


/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductViewPagerAdapter extends FragmentPagerAdapter{
    private SparseArray<Fragment> arrayList;
    private ArrayList<String> s;//TODO 命名改了


    public ProductViewPagerAdapter(FragmentManager fm) {
        super(fm);
        arrayList = new SparseArray<>();
    }

    public void setS(ArrayList<String> s) {
        this.s = s;
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
        return s == null ? 0:s.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return s.get(position);
    }
}
