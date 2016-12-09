package com.lu.beauty.designer.threadactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/12/7.
 */

public class DesignerItemBottomAdapter extends FragmentPagerAdapter {
    private SparseArray<Fragment> sparseArray;
    private ArrayList<String> s;
    private String id;
    public DesignerItemBottomAdapter(FragmentManager fm,ArrayList<String> strings,String id) {
        super(fm);
        sparseArray = new SparseArray<>();
        s = strings;
        this.id = id;
    }


    @Override
    public Fragment getItem(int position) {
        if (sparseArray.get(position) == null) {
            sparseArray.put(position, DesignerItenBottomFragment.getInstance(s.get(position),id));
        }
        return sparseArray.get(position);
    }

    @Override
    public int getCount() {
        return s.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return s.get(position);
    }
}
