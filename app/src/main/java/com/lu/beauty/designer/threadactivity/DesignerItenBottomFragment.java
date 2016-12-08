package com.lu.beauty.designer.threadactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.lu.beauty.bean.DesignerSecondArticlesBean;
import com.lu.beauty.bean.DesignerSecondProductsBean;
import com.lu.beauty.bean.DesignerSecondShopsBean;
import com.lu.beauty.internet.HttpUtil;
import com.lu.beauty.internet.ResponseCallBack;

import java.util.ArrayList;

/**
 * Created by GuoXuanYu on 16/12/7.
 */

public class DesignerItenBottomFragment extends BaseFragment {

    private static final String KEY = "pos";
    private static final String ID = "id";
    private static String id ;
    private ArrayList<String> productsArrayList;
    private RecyclerView rv;
    private DesignerProductsAdapter adapter;
    private LinearLayout ll;
    private TextView title;
    private TextView subtitle;
    private ImageView iv;
    private LinearLayout ll2;
    private TextView shopName;
    private ImageView shopImage;

    public static Fragment getInstance(int position,String id) {

        DesignerItenBottomFragment vpFragment = new DesignerItenBottomFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        bundle.putString(ID,id);
        vpFragment.setArguments(bundle);
        return vpFragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.designer_item_bottom;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.designer_item_bottom_rv);
        ll = bindView(R.id.designer_item_bottom_ll);
        title = bindView(R.id.designer_item_bottom_title);
        subtitle = bindView(R.id.designer_item_bottom_subtitle);
        iv = bindView(R.id.designer_item_bottom_image);
        ll2 = bindView(R.id.designer_item_bottom_ll2);
        shopName = bindView(R.id.designer_item_bottom_online_shop_name);
        shopImage = bindView(R.id.designer_item_bottom_online_shop_image);
    }

    @Override
    protected void initData() {
        Log.d("DesignerItenBottomFragm", getArguments().getString(ID));
        id = getArguments().getString(ID);
        productsArrayList = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        rv.setLayoutManager(manager);
        adapter = new DesignerProductsAdapter();
        setProductsMessage();
        rv.setAdapter(adapter);
        setArticlesMessage();
        setShopMessage();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            switch (getArguments().getInt(KEY)){
                case 0:
                    rv.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    ll.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ll2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
    public void setProductsMessage(){
        HttpUtil.getDesignerSecondProducts(id, new ResponseCallBack<DesignerSecondProductsBean>() {
            @Override
            public void onResponse(DesignerSecondProductsBean designerSecondProductsBean) {
//                for (int i = 0; i < designerSecondProductsBean.getData().getProducts().size(); i++) {
//                    productsArrayList.add(designerSecondProductsBean.getData().getProducts().get(i).getCover_images().get(0));
//                }
                adapter.setBean(designerSecondProductsBean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setArticlesMessage(){
        HttpUtil.getDesignerSecondArticles(id, new ResponseCallBack<DesignerSecondArticlesBean>() {
            @Override
            public void onResponse(DesignerSecondArticlesBean designerSecondArticlesBean) {

                Log.d("DesignerItenBottomFragm", id); // 201

                title.setText(designerSecondArticlesBean.getData().getArticles().get(0).getTitle());
                subtitle.setText(designerSecondArticlesBean.getData().getArticles().get(0).getSub_title());
                Glide.with(mContext).load(designerSecondArticlesBean.getData().getArticles().get(0).getImage_url()).into(iv);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setShopMessage(){
        HttpUtil.getDesignerSecondShops(id, new ResponseCallBack<DesignerSecondShopsBean>() {
            @Override
            public void onResponse(DesignerSecondShopsBean designerSecondShopsBean) {
                shopName.setText(designerSecondShopsBean.getData().getOnline_shops().get(0).getName());
                Glide.with(mContext).load(designerSecondShopsBean.getData().getOnline_shop_image()).into(shopImage);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
