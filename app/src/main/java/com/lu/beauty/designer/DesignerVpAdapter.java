package com.lu.beauty.designer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class DesignerVpAdapter extends FragmentPagerAdapter{
    private SparseArray<Fragment> sparseArray;
    private String [] designersArray;

    public DesignerVpAdapter(FragmentManager fm, String[] strings) {
        super(fm);
        sparseArray = new SparseArray<>();
        designersArray = strings;
    }

    @Override
    public Fragment getItem(int position) {
        if (sparseArray.get(position) == null) {
            sparseArray.put(position, DesignerVpFragment.getInstance(position));
        }
        return sparseArray.get(position);
    }

    @Override
    public int getCount() {
        return designersArray.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return designersArray[position];
    }
}
