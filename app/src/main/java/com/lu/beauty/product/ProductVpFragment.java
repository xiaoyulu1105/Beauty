package com.lu.beauty.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */
public class ProductVpFragment extends BaseFragment{
    private static final String KEY = "position";
    private TextView textView;


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
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            int type = getArguments().getInt(KEY);

            switch (type) {
                case 0:
                    textView.setText("Daily");
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
