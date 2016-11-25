package com.lu.beauty.internet;

import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.bean.ProductTitleBean;
import com.lu.beauty.tools.UrlValues;

import okhttp3.OkHttpClient;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class HttpUtil {

    // 网络请求获取有物页标题 by wqs
    public static void getProductTitleBean(ResponseCallBack<ProductTitleBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_CATEGORIES_URL, ProductTitleBean.class, responseCallBack);
    }
    public static void getProduckDailyBean(ResponseCallBack<ProductDailyBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_DAILY_URL, ProductDailyBean.class, responseCallBack);
    }
    public static void getDesignerRecommendBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_RECOMMEND_URL_PAGE+page,DesignerRecommendBean.class,responseCallBack);
    }
    public static void getDesignerIndependenceBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_INDEPENDENT_URL_PAGE + page,DesignerRecommendBean.class,responseCallBack);
    }
    public static void getDesignerMasterBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_BIG_URL_PAGE + page,DesignerRecommendBean.class,responseCallBack);
    }
    public static void getDesignerFavorBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_FAVOR_URL_PAGE + page,DesignerRecommendBean.class,responseCallBack);
    }
}
