package com.lu.beauty.product;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.base.CommonViewHolder;
import com.lu.beauty.bean.ProductCommonBean;
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
public class ProductVpFragment extends BaseFragment{
    private static final String KEY = "position";
    private static final String JEWELRY = "3";
    private static final String BAG = "1";
    private static final String SHOES = "2";
    private static final String MEN = "65";
    private static final String ACCESSORIES = "4";
    private static final String OTHER = "54";
    private int page = 1;
    private TextView textView;
    private StickyListHeadersListView productLv;
    private ProductListViewHeadAdapter headAdapter;
    private ProductListViewAdapter adapter;
    private RefreshLayout refreshLayout;
    private ArrayList<ProductCommonBean.DataBean.ProductsBean> arrayList;

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
                    break;
                case 1:
//                    textView.setText("首饰3");
                    okHttp(JEWELRY, 1);
                    refresh(JEWELRY);
                    load(JEWELRY);
                    break;
                case 2:
//                    textView.setText("包袋1");
                    okHttp(BAG, 1);
                    refresh(BAG);
                    load(BAG);
                    break;
                case 3:
//                    textView.setText("鞋履2");
                    okHttp(SHOES, 1);
                    refresh(SHOES);
                    load(SHOES);
                    break;
                case 4:
//                    textView.setText("Men65");
                    okHttp(MEN, 1);
                    refresh(MEN);
                    load(MEN);
                    break;
                case 5:
//                    textView.setText("配饰4");
                    okHttp(ACCESSORIES, 1);
                    refresh(ACCESSORIES);
                    load(ACCESSORIES);
                    break;
                case 6:
//                    textView.setText("其他54");
                    okHttp(OTHER, 1);
                    refresh(OTHER);
                    load(OTHER);
                    break;
            }
        }
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
        refreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                Toast.makeText(mContext, "load", Toast.LENGTH_SHORT).show();
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = page + 1;
                        okHttp(num, page);
                        refreshLayout.setLoading(false);
                    }
                }, 1500);
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


}
