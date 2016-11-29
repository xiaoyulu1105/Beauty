package com.lu.beauty.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lu.beauty.R;

/**
 * Created by dllo on 16/11/22.
 * Fragment基类@wqs
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
<<<<<<< HEAD
=======
    protected Bundle mSavedInstanceState;
>>>>>>> lxy

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
<<<<<<< HEAD
=======

>>>>>>> lxy
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        // 添加 by 小玉
        mSavedInstanceState = savedInstanceState;

        if (getLayout() == 0){
            return inflater.inflate(R.layout.null_layout, container, false);
        }else {

        return inflater.inflate(getLayout(), container, false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initView();
        initData();
    }

    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }
    // 绑定布局
    protected abstract int getLayout();
    // 初始化组件
    protected abstract  void initView();
    // 初始化数据
    protected abstract  void initData();
    // 设置监听
    protected  void setClick(View.OnClickListener clickListener, View ... views) {
        for (View view : views) {
            view.setOnClickListener(clickListener);
        }
    }

}
