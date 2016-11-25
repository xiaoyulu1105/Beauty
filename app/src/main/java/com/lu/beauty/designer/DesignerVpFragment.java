package com.lu.beauty.designer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

import java.util.ArrayList;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */
public class DesignerVpFragment extends BaseFragment{
    private static final String KEY = "pos";
    private TextView textView;
    private RecyclerView recyclerView;
    private DesignerAllAdapter allAdapter;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> arrayList;

    public static Fragment getInstance(int position) {

        DesignerVpFragment vpFragment = new DesignerVpFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        vpFragment.setArguments(bundle);
        return vpFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_vp_designer;
    }

    @Override
    protected void initView() {

        textView = bindView(R.id.designer_tv);
        recyclerView = bindView(R.id.designer_recyler);
    }

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        allAdapter = new DesignerAllAdapter(getContext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            switch (getArguments().getInt(KEY)){
                case 0:
                    textView.setText("推荐");
                    setRecommendRV();

                    break;
                case 1:
                    textView.setText("最受欢迎");
                    break;
                case 2:
                    textView.setText("独立设计师");
                    break;
                case 3:
                    textView.setText("大牌设计师");
                    break;
            }
        }
    }
    public void setRecommendRV(){

        HttpUtil.getDesignerRecommendBean(new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    arrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                allAdapter.setArrayList(arrayList);
                recyclerView.setAdapter(allAdapter);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

}
