package com.lu.beauty.designer;

import android.graphics.Color;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class DesignerFragment extends BaseFragment {
    private TabLayout designerTabLayout;
    private ViewPager designerViewPager;
    private static String designersArray[] = {"推荐", "最受欢迎", "独立设计师", "大牌设计师"};


    @Override
    protected int getLayout() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void initView() {
        designerTabLayout = bindView(R.id.designer_tab_layout);
        designerViewPager = bindView(R.id.designer_view_pager);

    }

    @Override
    protected void initData() {
        DesignerVpAdapter adapter = new DesignerVpAdapter(getChildFragmentManager(), designersArray);
        designerViewPager.setAdapter(adapter);
        designerTabLayout.setupWithViewPager(designerViewPager);
        designerTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        designerTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        Fragment fragment = new Fragment();
//        //Obj
//        Object data = new Object();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("dsf",data);
//        fragment.setArguments(bundle);


    }
}















