package com.lu.beauty.designer;

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
public class DesignerVpFragment extends BaseFragment{
    private static final String KEY = "pos";
    private TextView textView;

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
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            switch (getArguments().getInt(KEY)){
                case 0:
                    textView.setText("推荐");
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
}
