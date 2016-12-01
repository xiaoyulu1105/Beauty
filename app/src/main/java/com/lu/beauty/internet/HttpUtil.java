package com.lu.beauty.internet;

import com.lu.beauty.bean.ArticleBean;
import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.bean.ProductCommonBean;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.bean.ProductTitleBean;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class HttpUtil {

    // 网络请求获取有物页标题 by wqs
    public static void getProductTitleBean(ResponseCallBack<ProductTitleBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_CATEGORIES_URL, ProductTitleBean.class, responseCallBack);
    }
    // 网络请求获取daily一级页数据 by wqs
    public static void getProduckDailyBean(String date, ResponseCallBack<ProductDailyBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_DAILY_URL + date, ProductDailyBean.class, responseCallBack);
    }



    // 网络请求获取有物页通用数据 by wqs
    public static void getProduckCommonBean(String num,int page, ResponseCallBack<ProductCommonBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_COMMON_URL_START + num + UrlValues.PRODUCT_COMMON_URL_CENTER
                + page + UrlValues.PRODUCT_COMMON_URL_END, ProductCommonBean.class, responseCallBack);
    }
    // 推荐
    public static void getDesignerRecommendBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_RECOMMEND_URL_PAGE+page,DesignerRecommendBean.class,responseCallBack);
    }
    // 独立设计师
    public static void getDesignerIndependenceBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_INDEPENDENT_URL_PAGE + page,DesignerRecommendBean.class,responseCallBack);
    }
    // 大师
    public static void getDesignerMasterBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_BIG_URL_PAGE + page,DesignerRecommendBean.class,responseCallBack);
    }
    // 喜欢
    public static void getDesignerFavorBean(int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_FAVOR_URL_PAGE + page,DesignerRecommendBean.class,responseCallBack);
    }
    public static void getDesignerCategories(ResponseCallBack<DesignerHeadlineBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_CATEGORIES_URL,DesignerHeadlineBean.class,responseCallBack);
    }


    // 获取画报的数据
    public static void getArticleBean(int page, ResponseCallBack<ArticleBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.ARTICLE_URL + page, ArticleBean.class, responseCallBack);
    }


    // 头布局跳转
    public static void getDesignerHeadBean(String type,int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_RECOMMEND_LEFTHEAD + type + UrlValues.DESIGNER_RECOMMEND_RIGHTHEAD + page,
                DesignerRecommendBean.class,responseCallBack);
    }

}
