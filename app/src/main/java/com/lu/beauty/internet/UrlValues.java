package com.lu.beauty.internet;

/**
 * Created by XiaoyuLu on 16/11/22.
 */

public class UrlValues {
    // 画报 接口
    // 第一级 和 第二级 接口
    public static final String ARTICLE_URL = "http://design.zuimeia.com/api/v1/articles/daily/simple/?page_size=100&page=";
    public static final String ARTICLE_DETAIL_URL = "http://design.zuimeia.com/api/v1/article/";
    public static final String ARTICLE_DETAIL_EXAMPLE_URL = "http://design.zuimeia.com/api/v1/article/117/";

    // 有物 接口
    // 物品分类的接口
    public static final String PRODUCT_CATEGORIES_URL = "http://design.zuimeia.com/api/v1/product/categories/";
    // Daily项第一级
    public static final String PRODUCT_DAILY_URL = "http://design.zuimeia.com/api/v1/products/daily/?timestamp=";
    // daily第一级 和 daily第二级 接口
    public static final String PRODUCT_DAILY_URLT = "http://design.zuimeia.com/api/v1/products/category/3/?page=1&page_size=30";
    // product页通用url
    public static final String PRODUCT_COMMON_URL_START = "http://design.zuimeia.com/api/v1/products/category/";
    public static final String PRODUCT_COMMON_URL_CENTER = "?page=";
    public static final String PRODUCT_COMMON_URL_END = "&page_size=30";
    // 例:                                               拼接的我也不知道是什么玩意
//     http://design.zuimeia.com/api/v1/products/category/**1**/?page=1&page_size=30
//     &device_id=864895024466920&platform=android&lang=zh&appVersion=1.2.4&appVersionCode=10240
//     &systemVersion=17&countryCode=CN&user_id=86035&token=4h8-a4e2a822e31549c512fe&package_name=com.zuiapps.zuiworld
    // product页通用二级url
    public static final String PRODUCT_COMMON_SECOND = "http://design.zuimeia.com/api/v1/product/";
    // 例:                                      拼接id
    //http://design.zuimeia.com/api/v1/product/**914**/?device_id=864895024466920
    // &platform=android&lang=zh&appVersion=1.2.4&appVersionCode=10240&systemVersion=17&countryCode=CN&user_id=86035
    // &token=4h8-a4e2a822e31549c512fe&package_name=com.zuiapps.zuiworld

    public static final String PRODUCT_DETAIL_URL = "http://design.zuimeia.com/api/v1/product/1559/";

    // 设计师 接口
    // 设计师分类
    public static final String DESIGNER_CATEGORIES_URL = "http://design.zuimeia.com/api/v1/designer/categories/";
    // 推荐 第一级
    public static final String DESIGNER_RECOMMEND_URL_PAGE = "http://design.zuimeia.com/api/v1/designers/recommend/?page_size=30&page=";
    // 独立设计师 第一级
    public static final String DESIGNER_INDEPENDENT_URL_PAGE = "http://design.zuimeia.com/api/v1/designers/category/30/?page_size=30&page=";
    // 大牌设计师 第一级
    public static final String DESIGNER_BIG_URL_PAGE = "http://design.zuimeia.com/api/v1/designers/category/31/?page_size=30&page=";
    // 最受欢迎第一级
    public  static final String DESIGNER_FAVOR_URL_PAGE = "http://design.zuimeia.com/api/v1/designers/mostfavor/?page_size=30&page=";
    // 推荐 第一级 点击头布局
    public static final String DESIGNER_RECOMMEND_LEFTHEAD ="http://design.zuimeia.com/api/v1/designers/category/";
    public static final String DESIGNER_RECOMMEND_RIGHTHEAD = "/?page_size=30&page=";

    // 第二级页面  信息获取
    public static final String DESIGNER_SECOND_DESIGHNERHEAD = "http://design.zuimeia.com/api/v1/";
    public static final String DESIGNER_SECOND_DESIGHNERLAST = "/?page=1&page_size=30";
    public static final String DESIGNER_SECOND_PRODUCTS = "products/designer/";
    public static final String DESIGNER_SECOND_ARTICLES = "articles/designer/";
    public static final String DESIGNER_SECOND_SHOPS = "shops/designer/";

    // 第二级
    public static final String DESIGNER_DETAIL_URL = "http://design.zuimeia.com/api/v1/designer/145/";

}
