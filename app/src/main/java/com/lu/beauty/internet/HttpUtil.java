package com.lu.beauty.internet;

import com.lu.beauty.bean.ArticleBean;

import com.lu.beauty.bean.ArticleDetailBean;
import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;
import com.lu.beauty.bean.DesignerSecondArticlesBean;
import com.lu.beauty.bean.DesignerSecondBasicBean;
import com.lu.beauty.bean.DesignerSecondProductsBean;
import com.lu.beauty.bean.DesignerSecondShopsBean;
import com.lu.beauty.bean.ProductCommonBean;
import com.lu.beauty.bean.ProductDailyBean;
import com.lu.beauty.bean.ProductItemBean;
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

    public static void getProduckItemBean(String id, ResponseCallBack<ProductItemBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_ITEM_URL + id, ProductItemBean.class, responseCallBack);
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
    public static void getProductCategories(ResponseCallBack<DesignerHeadlineBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.PRODUCT_CATEGORIES_URL,DesignerHeadlineBean.class,responseCallBack);
    }

    // 获取画报的第一级数据
    public static void getArticleBean(int page, ResponseCallBack<ArticleBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.ARTICLE_URL + page, ArticleBean.class, responseCallBack);
    }
    // 获取画报的第二级数据
    public static void getArticleDetailBean(int id, ResponseCallBack<ArticleDetailBean> responseCallBack) {
        OkHttpManager.getInstance().get(UrlValues.ARTICLE_DETAIL_URL + id + "/", ArticleDetailBean.class, responseCallBack);
    }


    // 头布局跳转
    public static void getDesignerHeadBean(String type,int page,ResponseCallBack<DesignerRecommendBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_RECOMMEND_LEFTHEAD + type + UrlValues.DESIGNER_RECOMMEND_RIGHTHEAD + page,
                DesignerRecommendBean.class,responseCallBack);
    }

    public static void getDesignerSecondBasic(String id, ResponseCallBack<DesignerSecondBasicBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_SECOND_DESIGHNERHEAD+"designer/"+id+UrlValues.DESIGNER_SECOND_DESIGHNERLAST,
                DesignerSecondBasicBean.class,responseCallBack);
    }

    // 产品
    public static void getDesignerSecondProducts(String id, ResponseCallBack<DesignerSecondProductsBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_SECOND_DESIGHNERHEAD+UrlValues.DESIGNER_SECOND_PRODUCTS+
                id+UrlValues.DESIGNER_SECOND_DESIGHNERLAST,DesignerSecondProductsBean.class,responseCallBack);
    }

    // 画报
    public static void getDesignerSecondArticles(String id, ResponseCallBack<DesignerSecondArticlesBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_SECOND_DESIGHNERHEAD+UrlValues.DESIGNER_SECOND_ARTICLES+
                id+UrlValues.DESIGNER_SECOND_DESIGHNERLAST,DesignerSecondArticlesBean.class,responseCallBack);
    }

    // 旗舰店 店铺
    public static void getDesignerSecondShops(String id, ResponseCallBack<DesignerSecondShopsBean> responseCallBack){
        OkHttpManager.getInstance().get(UrlValues.DESIGNER_SECOND_DESIGHNERHEAD+UrlValues.DESIGNER_SECOND_SHOPS+
                id+UrlValues.DESIGNER_SECOND_DESIGHNERLAST,DesignerSecondShopsBean.class,responseCallBack);
    }

}
