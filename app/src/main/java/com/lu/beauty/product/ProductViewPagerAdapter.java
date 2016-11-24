package com.lu.beauty.product;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;


/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductViewPagerAdapter extends FragmentPagerAdapter{
    private SparseArray<Fragment> arrayList;
    private final String[] s;


    public ProductViewPagerAdapter(FragmentManager fm, String[] strings) {
        super(fm);
        arrayList = new SparseArray<>();
        s = strings;
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
        return s.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return s[position];
    }
}
