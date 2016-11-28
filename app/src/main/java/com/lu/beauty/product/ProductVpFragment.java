package com.lu.beauty.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 * 有物Fragment
 */
public class ProductVpFragment extends BaseFragment{
    private static final String KEY = "position";
    private TextView textView;
    private StickyListHeadersListView productLv;
    private ProductListViewHeadAdapter adapter;

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
        adapter = new ProductListViewHeadAdapter();
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
    //86400000
    //1480003200000

    //http://design.zuimeia.com/api/v1/products/likeuser/86035/?page=1&page_size=30&device_id=864895024466920
    // &platform=android&lang=zh&appVersion=1.2.4&appVersionCode=10240&systemVersion=17
    // &countryCode=CN&user_id=86035&token=4h8-a4e2a822e31549c512fe&package_name=com.zuiapps.zuiworld

    //http://design.zuimeia.com/api/v1/products/daily/?timestamp=1479916800000&device_id=864895024466920
    // &platform=android&lang=zh&appVersion=1.2.4&appVersionCode=10240&systemVersion=17
    // &countryCode=CN&user_id=86035&token=4h8-a4e2a822e31549c512fe&package_name=com.zuiapps.zuiworld

    //http://design.zuimeia.com/api/v1/products/daily/?timestamp=1479830400000&device_id=864895024466920
    // &platform=android&lang=zh&appVersion=1.2.4&appVersionCode=10240&systemVersion=17
    // &countryCode=CN&user_id=86035&token=4h8-a4e2a822e31549c512fe&package_name=com.zuiapps.zuiworld



   
}
