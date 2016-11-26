package com.lu.beauty.bean;

import java.util.List;

/**
 * Created by GuoXuanYu on 16/11/24.
 */

public class DesignerRecommendItemBean {
    private int follow_num;
    private String city;
    private String concept;
    private String name;
    private String label;
    private String avatar_url;
    private int id;
    private List<String> recommend_images;
    private List<DesignerRecommendBean.DataBean.DesignersBean.CategoriesBean> categories;

    public int getFollow_num() {
        return follow_num;
    }

    public void setFollow_num(int follow_num) {
        this.follow_num = follow_num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getRecommend_images() {
        return recommend_images;
    }

    public void setRecommend_images(List<String> recommend_images) {
        this.recommend_images = recommend_images;
    }

    public List<DesignerRecommendBean.DataBean.DesignersBean.CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<DesignerRecommendBean.DataBean.DesignersBean.CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class CategoriesBean {
        /**
         * id : 8
         * name : 文艺
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
