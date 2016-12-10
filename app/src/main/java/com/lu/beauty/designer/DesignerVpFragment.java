package com.lu.beauty.designer;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.designer.threadactivity.DesignerItemActivity;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.tools.EndLessOnScrollListener;

import java.util.ArrayList;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */
public class DesignerVpFragment extends BaseFragment implements DesignerClickListener, View.OnClickListener {
    private static final String KEY = "pos";
//    private TextView textView;
    private RecyclerView recyclerView;
    private DesignerAllAdapter allAdapter;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> recommendArrayList;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> independenceArrayList;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> masterArrayList;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> favorArrayList;
    private ArrayList<DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean> headLineBean;
    private int recommendPage = 1;
    private int independencePage = 1;
    private int masterPage = 1;
    private int favorPage = 1;
    private String type = "";
    private EndLessOnScrollListener endLessOnScrollListener;
    private CommendHeadAdapter conmmendHeadAdapter;
    private View headView;
    private ArrayList<DesignerRecommendBean.DataBean.CategoriesBeanX> headArrayList;
    private HeadItemAdapter headItemAdapter;
    private RecyclerView headItemRV;
    private RelativeLayout pop;
    private PopupWindow popupWindow;
    private PopAdapter mPopadapter;
    private LinearLayout coords;
    private TextView popText;
    private String popTextValue = "全部";
    private String independenceType = "30";

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

        pop = bindView(R.id.designer_pop);
//        textView = bindView(R.id.designer_tv);
        recyclerView = bindView(R.id.designer_recyler);
        setClick(this,pop);
        coords = bindView(R.id.designer_popcoords);
        popText = bindView(R.id.designer_poptext);
    }

    @Override
    protected void initData() {
        recommendArrayList = new ArrayList<>();
        independenceArrayList = new ArrayList<>();
        masterArrayList = new ArrayList<>();
        favorArrayList = new ArrayList<>();
        headArrayList = new ArrayList<DesignerRecommendBean.DataBean.CategoriesBeanX>();
        allAdapter = new DesignerAllAdapter(this, mContext);
        headItemAdapter = new HeadItemAdapter(this);

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
                    headConmmendMore(independenceType,independencePage);
                }else if (type.equals("Master")){
                    masterPage = masterPage + 1;
                    setMasterMoreRV(masterPage);
                }else if (type.equals("Favor")){
                    favorPage = favorPage + 1;
                    setFavorMoreRV(favorPage);
                }
            }
        });
        popWindow();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            switch (getArguments().getInt(KEY)){
                case 0:
                    type = "Conmmend";
//                    textView.setText("推荐");
                    endLessOnScrollListener.resetPreviousTotal();

                    setRecommendRV(1);


                    break;
                case 1:
//                    textView.setText("最受欢迎");
                    type = "Favor";
                    endLessOnScrollListener.resetPreviousTotal();
                    setFavorMoreRV(1);
                    recyclerView.setAdapter(allAdapter);
                    break;
                case 2:
                    pop.setVisibility(View.VISIBLE);
                    type = "Independence";
//                    textView.setText("独立设计师");
                    endLessOnScrollListener.resetPreviousTotal();
                    headConmmendMore(independenceType,1);
                    recyclerView.setAdapter(allAdapter);
                    popText.setText(popTextValue);
                    break;
                case 3:
                    type = "Master";
//                    textView.setText("大牌设计师");
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
                conmmendHeadAdapter = new CommendHeadAdapter(allAdapter);
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
    // 推荐
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
    // 独立设计师
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
    // 大牌设计师
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

    // 最喜欢的
    public void setFavorMoreRV(int page){
        HttpUtil.getDesignerFavorBean(page, new ResponseCallBack<DesignerRecommendBean>() {
            @Override
            public void onResponse(DesignerRecommendBean designerRecommendBean) {
                for (int i = 0; i < designerRecommendBean.getData().getDesigners().size(); i++) {
                    DesignerRecommendBean.DataBean.DesignersBean bean = designerRecommendBean.getData().getDesigners().get(i);
                    bean.setType(1);
                    favorArrayList.add(bean);
                }
                allAdapter.setArrayList(favorArrayList);
                allAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    // Pop里的数据
    public void setHeadLineRV(){
        HttpUtil.getDesignerCategories(new ResponseCallBack<DesignerHeadlineBean>() {
            @Override
            public void onResponse(DesignerHeadlineBean designerHeadlineBean) {
                for(int i = 0 ; i <designerHeadlineBean.getData().getCategories().get(0).getSub_categories().size();i++){
                    headLineBean.add(designerHeadlineBean.getData().getCategories().get(0).getSub_categories().get(i));
                }
                changePopItemBackground("全部",30);
                mPopadapter.setArrayList(headLineBean);
                mPopadapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    @Override
    public void headItemClick(DesignerRecommendBean.DataBean.CategoriesBeanX beanX) {
        Intent intent = new Intent(getContext(),DesignerHeadMoreActivity.class);
        intent.putExtra("title",beanX.getName());
        intent.putExtra("id",beanX.getId() + "");
        Log.d("DesignerVpFragment", "beanX:" + beanX.getName());
        Log.d("DesignerVpFragment", "beanX:" + beanX.getId());
        startActivity(intent);
    }

    @Override
    public void popItemClick(DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean bean) {
        independenceArrayList = new ArrayList<>();
        popTextValue = bean.getName();
        popText.setText(popTextValue);
        independenceType = String.valueOf(bean.getId());
        headConmmendMore(independenceType,1);
        endLessOnScrollListener.resetPreviousTotal();
        independencePage = 1;
        popupWindow.dismiss();
        changePopItemBackground(popTextValue,bean.getId());
    }

    // 修改 by 小玉
    @Override
    public void allAdapterItemClick(int id) {
        String stringId = String.valueOf(id);
        Intent intent = new Intent(mContext, DesignerItemActivity.class);
        intent.putExtra(DesignerItemActivity.INTENT_ID_KEY, id + "");
        startActivity(intent);
    }

    public void changePopItemBackground(String name,int id){
        for (int i = 0; i < headLineBean.size(); i++) {
            if (name.equals(headLineBean.get(i).getName()) && id == headLineBean.get(i).getId()){
                headLineBean.get(i).setSelect(1);
            }else if (!name.equals(headLineBean.get(i).getName())||id != headLineBean.get(i).getId()){
                headLineBean.get(i).setSelect(0);
            }
            Log.d("DesignerVpFragment", "headLineBean.get(i).getSelect():" + headLineBean.get(i).getSelect());
        }
        mPopadapter.setArrayList(headLineBean);
        mPopadapter.notifyDataSetChanged();
    }
    public void headConmmendMore(String type,int page){
        HttpUtil.getDesignerHeadBean(type, page, new ResponseCallBack<DesignerRecommendBean>() {
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

    // 创建一个pop
    public void popWindow(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.designer_popwindow,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.designer_pop_rv);
        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(manager);
        mPopadapter = new PopAdapter(this);
        headLineBean = new ArrayList<>();
        DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean bean = new DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean();
        bean.setId(30);
        bean.setName("全部");
        bean.setSelect(1);
        Log.d("DesignerVpFragment", bean.getName() + bean.getId());
        headLineBean.add(bean);
        setHeadLineRV();
        recyclerView.setAdapter(mPopadapter);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.designer_pop:
                popupWindow.showAsDropDown(coords);
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}