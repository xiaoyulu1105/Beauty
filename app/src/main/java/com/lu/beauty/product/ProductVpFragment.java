package com.lu.beauty.product;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.bean.ProductCommonBean;
import com.lu.beauty.designer.DesignerAllAdapter;
import com.lu.beauty.designer.DesignerClickListener;
import com.lu.beauty.designer.DesignerHeadMoreActivity;
import com.lu.beauty.designer.PopAdapter;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;
import com.lu.beauty.product.daily.ProductListViewHeadAdapter;
import com.lu.beauty.ui.RefreshLayout;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 * 有物Fragment
 */
public class ProductVpFragment extends BaseFragment implements DesignerClickListener, View.OnClickListener{
    private static final String KEY = "position";
    private static final String JEWELRY = "3";
    private static final String BAG = "1";
    private static final String SHOES = "2";
    private static final String MEN = "65";
    private static final String ACCESSORIES = "4";
    private static final String OTHER = "54";
    private int independencePage = 1;
    private ArrayList<DesignerRecommendBean.DataBean.DesignersBean> independenceArrayList;
    private int page = 1;
    private StickyListHeadersListView productLv;
    private ProductListViewHeadAdapter headAdapter;
    private ProductListViewAdapter adapter;
    private RefreshLayout refreshLayout;
    private ArrayList<ProductCommonBean.DataBean.ProductsBean> arrayList;
    private RelativeLayout pop;
    private LinearLayout coords;
    private TextView popText;
    ArrayList<DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean> headLineBean;
    private PopupWindow popupWindow;
    private PopAdapter mPopadapter;
    private String popTextValue = "全部";
    private String independenceType = "30";

    // 参数就把 当前页面的key传过来 网络请求
    public static Fragment getInstance(int position) {
        ProductVpFragment vpFragment = new ProductVpFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        vpFragment.setArguments(bundle);
        return vpFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product_vp;
    }

    @Override
    protected void initView() {
        productLv = bindView(R.id.product_list_view);
        refreshLayout = bindView(R.id.swipe_layout);

        pop = bindView(R.id.product_pop);
        setClick(this, pop);
        coords = bindView(R.id.product_popcoords);
        popText = bindView(R.id.product_poptext);
    }

    @Override
    protected void initData() {
        adapter = new ProductListViewAdapter();
        arrayList = new ArrayList<>();
        productLv.setAdapter(adapter);

        if (getArguments() != null){
            int type = getArguments().getInt(KEY);

            switch (type) {
                case 0:
//                    textView.setText("Daily");
                    headAdapter = new ProductListViewHeadAdapter();
                    productLv.setAdapter(headAdapter);
                    dailyRefresh();
                    pop.setVisibility(View.GONE);
                    break;
                case 1:
//                    textView.setText("首饰3");
                    okHttp(JEWELRY, 1);
                    refresh(JEWELRY);
                    load(JEWELRY);
                    setHeadLineRV(0);
                    break;
                case 2:
//                    textView.setText("包袋1");
                    okHttp(BAG, 1);
                    refresh(BAG);
                    load(BAG);
                    setHeadLineRV(1);
                    break;
                case 3:
//                    textView.setText("鞋履2");
                    okHttp(SHOES, 1);
                    refresh(SHOES);
                    load(SHOES);
                    setHeadLineRV(2);
                    break;
                case 4:
//                    textView.setText("Men65");
                    okHttp(MEN, 1);
                    refresh(MEN);
                    load(MEN);
                    pop.setVisibility(View.GONE);
                    break;
                case 5:
//                    textView.setText("配饰4");
                    okHttp(ACCESSORIES, 1);
                    refresh(ACCESSORIES);
                    load(ACCESSORIES);
                    setHeadLineRV(4);
                    break;
                case 6:
//                    textView.setText("其他54");
                    okHttp(OTHER, 1);
                    refresh(OTHER);
                    load(OTHER);
                    setHeadLineRV(5);
                    break;
            }
        }
        popWindow();
    }

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
        recyclerView.setAdapter(mPopadapter);
    }

    // Pop里的数据
    public void setHeadLineRV(final int pos){
        HttpUtil.getProductCategories(new ResponseCallBack<DesignerHeadlineBean>() {
            @Override
            public void onResponse(DesignerHeadlineBean designerHeadlineBean) {
                for(int i = 0 ; i < designerHeadlineBean.getData().getCategories().get(pos).getSub_categories().size();i++){
                    headLineBean.add(designerHeadlineBean.getData().getCategories().get(pos).getSub_categories().get(i));
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

    private void dailyRefresh() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    // 刷新
    private void refresh(final String num) {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        okHttp(num, 1);
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    // 加载
    private void load(final String num) {
        refreshLayout.setOnLoadListener( new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                Toast.makeText(mContext, "load", Toast.LENGTH_SHORT).show();
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        page = page + 1;
                        okHttp(num, page);
                        
                    }
                });
            }
        });
    }

    private void okHttp(String num, int page) {
        HttpUtil.getProduckCommonBean(num,page, new ResponseCallBack<ProductCommonBean>() {
            @Override
            public void onResponse(ProductCommonBean productCommonBean) {
                for (int i = 0; i < productCommonBean.getData().getProducts().size(); i++) {
                    arrayList.add(productCommonBean.getData().getProducts().get(i));
                }
                adapter.setArrayList(arrayList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_pop:
                popupWindow.showAsDropDown(coords);
                break;
        }
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
        headCommendMore(independenceType,1);
        independencePage = 1;
        popupWindow.dismiss();
        changePopItemBackground(popTextValue,bean.getId());
    }

    @Override
    public void allAdapterItemClick(int id) {

    }
    
    public void headCommendMore(String type, int page){
        headOkHttp(type, page);
    }

    private void headOkHttp(String num, int page) {
        arrayList = new ArrayList<>();
        HttpUtil.getProduckCommonBean(num,page, new ResponseCallBack<ProductCommonBean>() {
            @Override
            public void onResponse(ProductCommonBean productCommonBean) {
                for (int i = 0; i < productCommonBean.getData().getProducts().size(); i++) {
                    arrayList.add(productCommonBean.getData().getProducts().get(i));
                }
                adapter.setArrayList(arrayList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

                Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
