package com.lu.beauty.internet;

/**
 * Created by XiaoyuLu on 16/11/22.
 */

public class UrlValues {
    // 画报 接口
    // 第一级 和 第二级 接口
    public static final String ARTICLE_URL = "http://design.zuimeia.com/api/v1/articles/daily/simple/?page_size=100&page=";
    public static final String ARTICLE_DETAIL_URL = "http://design.zuimeia.com/api/v1/article/112/";

    // 有物 接口
    // 物品分类的接口
    public static final String PRODUCT_CATEGORIES_URL = "http://design.zuimeia.com/api/v1/product/categories/";
    // Daily项第一级
    public static final String PRODUCT_DAILY_URL = "http://design.zuimeia.com/api/v1/products/daily/?timestamp=";
    // 普通第一级 和 第二级 接口
    public static final String PRODUCT_COMMON_URL = "http://design.zuimeia.com/api/v1/products/category/3/?page=1&page_size=30";
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

    // 第二级
    public static final String DESIGNER_DETAIL_URL = "http://design.zuimeia.com/api/v1/designer/145/";

}
