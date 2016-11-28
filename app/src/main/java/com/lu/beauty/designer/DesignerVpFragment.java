package com.lu.beauty.designer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.tools.EndLessOnScrollListener;

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
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> recommendArrayList;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> independenceArrayList;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> masterArrayList;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> favorArrayList;
    private int recommendPage = 1;
    private int independencePage = 1;
    private int masterPage = 1;
    private int favorPage = 1;
    private String type = "";
    private EndLessOnScrollListener endLessOnScrollListener;
    private ConmmendHeadAdapter conmmendHeadAdapter;
    private View headView;
    private ArrayList<DesignerRecommendBean.DataBean.CategoriesBeanX> headArrayList;
    private HeadItemAdapter headItemAdapter;
    private RecyclerView headItemRV;

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
        recommendArrayList = new ArrayList<>();
        independenceArrayList = new ArrayList<>();
        masterArrayList = new ArrayList<>();
        favorArrayList = new ArrayList<>();
        headArrayList = new ArrayList<DesignerRecommendBean.DataBean.CategoriesBeanX>();
        allAdapter = new DesignerAllAdapter(getContext());
        headItemAdapter = new HeadItemAdapter();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        recyclerView.setOnScrollListener(endLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            protected void onLoadMore(int curentPage) {
                if(type.equals("Conmmend")) {
                    recommendPage = recommendPage + 1;
                    setRecommendMoreRV(recommendPage);
                }else if (type.equals("Independence")){
                    independencePage = independencePage + 1;
                    setIndependenceMoreRV(independencePage);
                }else if (type.equals("Master")){
                    masterPage = masterPage + 1;
                    setMasterMoreRV(masterPage);
                }else if (type.equals("Favor")){
                    favorPage = favorPage + 1;
                    setFavorMoreRV(favorPage);
                }
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            switch (getArguments().getInt(KEY)){
                case 0:
                    type = "Conmmend";
                    textView.setText("推荐");
                    endLessOnScrollListener.resetPreviousTotal();

                    setRecommendRV(1);


                    break;
                case 1:
                    textView.setText("最受欢迎");
                    type = "Favor";
                    endLessOnScrollListener.resetPreviousTotal();
                    setFavorMoreRV(1);
                    recyclerView.setAdapter(allAdapter);
                    break;
                case 2:
                    type = "Independence";
                    textView.setText("独立设计师");
                    endLessOnScrollListener.resetPreviousTotal();
                    setIndependenceMoreRV(1);
                    recyclerView.setAdapter(allAdapter);
                    break;
                case 3:
                    type = "Master";
                    textView.setText("大牌设计师");
                    endLessOnScrollListener.resetPreviousTotal();
                    setMasterMoreRV(1);
                    recyclerView.setAdapter(allAdapter);
                    break;
            }
        }
    }
    public void setRecommendRV(int page){

        HttpUtil.getDesignerRecommendBean(page,new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    recommendArrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                for (int i = 0; i < designerRecommendBean.getData().getCategories().size(); i++) {
                    headArrayList.add(designerRecommendBean.getData().getCategories().get(i));
                }
                headItemAdapter.setArrayList(headArrayList);
                allAdapter.setArrayList(recommendArrayList);
                conmmendHeadAdapter = new ConmmendHeadAdapter(allAdapter);
                headView = LayoutInflater.from(mContext).inflate(R.layout.activity_designer_header,null);
                headItemRV = (RecyclerView) headView.findViewById(R.id.activity_designer_headerRV);
                GridLayoutManager manager = new GridLayoutManager(getContext(),4);
                headItemRV.setLayoutManager(manager);
                headItemRV.setAdapter(headItemAdapter);
                conmmendHeadAdapter.addHeadView(headView);
                recyclerView.setAdapter(conmmendHeadAdapter);
                
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setRecommendMoreRV(int page){

        HttpUtil.getDesignerRecommendBean(page,new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    recommendArrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                allAdapter.setArrayList(recommendArrayList);
                allAdapter.notifyDataSetChanged();
                conmmendHeadAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setIndependenceMoreRV(int page){
        HttpUtil.getDesignerIndependenceBean(page, new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    independenceArrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                allAdapter.setArrayList(independenceArrayList);
                allAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setMasterMoreRV(int page){
        HttpUtil.getDesignerMasterBean(page, new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    masterArrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                allAdapter.setArrayList(masterArrayList);
                allAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public void setFavorMoreRV(int page){
        HttpUtil.getDesignerFavorBean(page, new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    favorArrayList.add(designerRecommendBean.getData().getDesigners().get(i));
                }
                allAdapter.setArrayList(favorArrayList);
                allAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


}
