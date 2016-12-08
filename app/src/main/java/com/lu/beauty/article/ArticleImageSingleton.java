package com.lu.beauty.article;

import java.util.ArrayList;

/**
 * Created by XiaoyuLu on 16/12/5.
 *
 * 该单例类 的用途
 * 用来 存放 画报详情页 第一张图片 和富文本里面的图片的
 *
 * 出现的问题
 *
 */
public class ArticleImageSingleton {

    private ArrayList<String> imageUrlArrayList = null;

    private static ArticleImageSingleton ourInstance = new ArticleImageSingleton();

    public static ArticleImageSingleton getInstance() {
        return ourInstance;
    }

    private ArticleImageSingleton() {
    }

    public ArrayList<String> getImageUrlArrayList() {
        return imageUrlArrayList;
    }

    public void setImageUrlArrayList(ArrayList<String> imageUrlArrayList) {
        this.imageUrlArrayList = imageUrlArrayList;
    }
}
