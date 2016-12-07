package com.lu.beauty.designer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseActivity;
import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.tools.EndLessOnScrollListener;

import java.util.ArrayList;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by GuoXuanYu on 16/11/26.
 */

public class DesignerHeadMoreActivity extends BaseActivity implements View.OnClickListener,SwipeBackActivityBase,DesignerClickListner {

    private ImageView back;
    private TextView title;
    private RecyclerView rv;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    private int page = 1;
    private DesignerAllAdapter allAdapter;
    private String type;

    @Override
    protected int getLayout() {
        return R.layout.activity_designer_headmore;
    }

    @Override
    protected void initViews() {
        back = bindView(R.id.designer_headmore_back);
        title = bindView(R.id.designer_headmore_title);
        rv = bindView(R.id.designer_headmore_rv);
        setClick(this,back);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        type = intent.getStringExtra("id");
        Log.d("DesignerHeadMoreActivit", "type:" + type);
        arrayList = new ArrayList<>();
        allAdapter = new DesignerAllAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        headConmmendMore(type,page);
        rv.setAdapter(allAdapter);
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        rv.setOnScrollListener(new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                page = page + 1;
                headConmmendMore(type,page);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.designer_headmore_back:
                onBackPressed();
                break;
        }
    }

    public void headConmmendMore(String type,int page){
        HttpUtil.getDesignerHeadBean(type, page, new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    arrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                allAdapter.setArrayList(arrayList);
                allAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && swipeBackActivityHelper != null)
            return swipeBackActivityHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackActivityHelper.getSwipeBackLayout();
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

    @Override
    public void headItemClick(DesignerRecommendBean.DataBean.CategoriesBeanX beanX) {

    }

    @Override
    public void popItemClick(DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean bean) {

    }

    @Override
    public void allAdapterItemClick(int id) {

    }
}
