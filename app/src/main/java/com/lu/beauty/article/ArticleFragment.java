package com.lu.beauty.article;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.appeaser.deckview.views.DeckChildView;
import com.appeaser.deckview.views.DeckView;
import com.appeaser.deckviewsample.Datum;
import com.lu.beauty.MainActivity;
import com.lu.beauty.R;
import com.lu.beauty.base.BaseFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XiaoyuLu on 16/11/23.
 */
public class ArticleFragment extends BaseFragment {


    private ArrayList<String> getTitles;
    private ArrayList<String> getImageUrls;
    private ArrayList<Integer> getIds;

    private DeckView<Datum> mDeckView;
    private ArrayList<Datum> mEntries;

    private Drawable mDefaultHeaderIcon;
    private Bitmap mDefaultThumbnail; // 默认的图片

    // 保留位置 当 配置改变
    private int scrollToChildIndex = -1;
    // bundle的 key值
    public static final String CURRENT_SCROLL = "current.scroll";
    public static final String CURRENT_LIST = "current.list";

    public static final String INTENT_ID_KEY = "id";

    @Override
    protected int getLayout() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView() {

        getTitles = new ArrayList<>();
        getImageUrls = new ArrayList<>();
        getIds = new ArrayList<>();

        Bundle bundle = getArguments();

        getIds = bundle.getIntegerArrayList(MainActivity.BUNDLE_IDS_KEY);
        getTitles = bundle.getStringArrayList(MainActivity.BUNDLE_TITLES_KEY);
        getImageUrls = bundle.getStringArrayList(MainActivity.BUNDLE_IMAGE_URLS_KEY);

        mDeckView = (DeckView<Datum>) getView().findViewById(R.id.article_deckview);
        mDefaultThumbnail = BitmapFactory.decodeResource(getResources(),
                R.mipmap.loading);
        mDefaultHeaderIcon = getResources().getDrawable(R.drawable.default_header_icon);

        // 1.
        if (mSavedInstanceState != null) {
            if (mSavedInstanceState.containsKey(CURRENT_LIST)) {
                mEntries = mSavedInstanceState.getParcelableArrayList(CURRENT_LIST);
            }
            if (mSavedInstanceState.containsKey(CURRENT_SCROLL)) {
                scrollToChildIndex = mSavedInstanceState.getInt(CURRENT_SCROLL);
            }
        }

        if (mEntries == null) {
            mEntries = new ArrayList<>();
            for (int i = getIds.size() - 1; i >= 0; i--) {
                // 获得画报 标题, 副标题 , 图片和id
                String getTitle = getTitles.get(i);
                String getImageUrl = getImageUrls.get(i);
                int getId = getIds.get(i);

                // 将数据放在 MyArticleDatum 序列化类里
                Datum datum = new Datum();
                datum.id = getId;
                datum.headerTitle = getTitle;
                datum.link = getImageUrl;

                mEntries.add(datum);
            }
        }

        DeckView.Callback<Datum> deckViewCallback = new DeckView.Callback<Datum>() {
            @Override
            public ArrayList<Datum> getData() {
                return mEntries;
            }

            @Override
            public void loadViewData(WeakReference<DeckChildView<Datum>> dcv, Datum item) {
                loadViewDataInternal(item, dcv);
            }

            @Override
            public void unloadViewData(Datum item) {
                // 取消请求
                Picasso.with(mContext).cancelRequest(item.target);
            }

            @Override
            public void onViewDismissed(Datum item) {
                mEntries.remove(item);
                mDeckView.notifyDataSetChanged();
            }

            @Override
            public void onItemClick(Datum item) {
//                Toast.makeText(mContext,
//                        "Item with title: '" + item.headerTitle + "' clicked",
//                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(INTENT_ID_KEY, item.id);
                startActivity(intent);
            }

            @Override
            public void onNoViewsToDeck() {
                Toast.makeText(mContext,
                        "No views to show",
                        Toast.LENGTH_SHORT).show();
            }
        };

        mDeckView.initialize(deckViewCallback);

        if (scrollToChildIndex != -1) {
            mDeckView.post(new Runnable() {
                @Override
                public void run() {
                    // 恢复滚动位置
                    mDeckView.scrollToChild(scrollToChildIndex);
                }
            });
        }

    }

    // 5. Picasso 请求图片
    private void loadViewDataInternal(final Datum item,
                                      final WeakReference<DeckChildView<Datum>> dcv) {

        Picasso.with(mContext).cancelRequest(item.target);

        // 请求大图图片
        item.target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                // 如果 弱引用的 DeckChildView的对象 不为空
                if (dcv.get() != null) {
                    dcv.get().onDataLoaded(item, bitmap, mDefaultHeaderIcon,
                            item.headerTitle, R.color.article_header_background_color);
                    // Color.DKGRAY
                    // 要是这里将Color 改为0, 就完全透明了
                }
            }

            @Override
            public void onBitmapFailed(Drawable drawable) {
                // 下载失败, 给默认的 缩略图 代替
                if (dcv.get() != null) {
                    dcv.get().onDataLoaded(item, mDefaultThumbnail,
                            mDefaultHeaderIcon, item.headerTitle + " Failed", 0);
                }
            }

            @Override
            public void onPrepareLoad(Drawable drawable) {
                // 当还在下载先将 缩略图 进行显示
                if (dcv.get() != null) {
                    dcv.get().onDataLoaded(item, mDefaultThumbnail,
                            mDefaultHeaderIcon, "Loading...", 0);
                }
            }
        };

        Picasso.with(mContext).load(item.link).into(item.target);
    }


    @Override
    protected void initData() {


    }


}
