package com.lu.beauty.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

import java.util.ArrayList;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 * 有物Fragment
 */
public class ProductVpFragment extends BaseFragment{
    private static final String KEY = "position";
    private TextView textView;
    private ListView productLv;
    private ProductListViewAdapter adapter;

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
        textView = bindView(R.id.product_vp_tv);
        productLv = bindView(R.id.product_list_view);
    }

    @Override
    protected void initData() {
        adapter = new ProductListViewAdapter();
        if (getArguments() != null){
            int type = getArguments().getInt(KEY);

            switch (type) {
                case 0:
//                    textView.setText("Daily");
                    HttpUtil.getProduckDailyBean(new ResponseCallBack<ProductDailyBean>() {
                        @Override
                        public void onResponse(ProductDailyBean productDailyBean) {
                            adapter.setBean(productDailyBean);
                            productLv.setAdapter(adapter);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                    break;
                case 1:
                    textView.setText("首饰");
                    break;
                case 2:
                    textView.setText("包袋");
                    break;
                case 3:
                    textView.setText("鞋履");
                    break;
                case 4:
                    textView.setText("Men");
                    break;
                case 5:
                    textView.setText("配饰");
                    break;
                case 6:
                    textView.setText("其他");
                    break;
            }
        }
    }

   
}
