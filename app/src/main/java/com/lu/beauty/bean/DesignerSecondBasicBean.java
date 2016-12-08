package com.lu.beauty.bean;

import java.util.List;

/**
 * Created by GuoXuanYu on 16/12/3.
 */

public class DesignerSecondBasicBean {

    /**
     * data : {"follow_num":6524,"city":"温哥华","concept":"为无畏的灵魂而设计","article_num":1,"name":"Sandra Silveyra","product_num":11,"label":"Olivia The Wolf 创始人","introduce_images":["http://dstatic.zuimeia.com/common/image/2016/8/5/882bb33a-ba2e-4527-95f9-0c8d5697f3cb_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/58b7690a-c9a2-4e54-98a7-31258c23b5e5_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/0fa4d612-c6ff-4c90-83b1-2c6eebf8e8ed_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/c1a2b4aa-4b34-40d4-a35c-b27435831136_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/7049115f-c573-443f-9c2e-b5070b87b267_1000x1000.jpeg"],"avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/5/fc1f27ba-ed73-478e-97bb-aaf1d6b31f6c.jpg","is_followed":0,"id":68,"categories":[{"id":2,"name":"配饰"},{"id":14,"name":"华丽"},{"id":30,"name":"独立设计师"},{"id":46,"name":"温哥华"},{"id":58,"name":"优雅"}],"description":"Sandra Silveyra是墨西哥裔加拿大设计师，在大学时主修珠宝设计，现在她创立了自己的婚纱头饰品牌 Olivia The Wolf。从波西米亚头环到浪漫花朵头饰，她的设计具有浓浓拉美热烈奔放的异域风格。"}
     * result : 1
     */

    private DataBean data;
    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * follow_num : 6524
         * city : 温哥华
         * concept : 为无畏的灵魂而设计
         * article_num : 1
         * name : Sandra Silveyra
         * product_num : 11
         * label : Olivia The Wolf 创始人
         * introduce_images : ["http://dstatic.zuimeia.com/common/image/2016/8/5/882bb33a-ba2e-4527-95f9-0c8d5697f3cb_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/58b7690a-c9a2-4e54-98a7-31258c23b5e5_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/0fa4d612-c6ff-4c90-83b1-2c6eebf8e8ed_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/c1a2b4aa-4b34-40d4-a35c-b27435831136_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/5/7049115f-c573-443f-9c2e-b5070b87b267_1000x1000.jpeg"]
         * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/8/5/fc1f27ba-ed73-478e-97bb-aaf1d6b31f6c.jpg
         * is_followed : 0
         * id : 68
         * categories : [{"id":2,"name":"配饰"},{"id":14,"name":"华丽"},{"id":30,"name":"独立设计师"},{"id":46,"name":"温哥华"},{"id":58,"name":"优雅"}]
         * description : Sandra Silveyra是墨西哥裔加拿大设计师，在大学时主修珠宝设计，现在她创立了自己的婚纱头饰品牌 Olivia The Wolf。从波西米亚头环到浪漫花朵头饰，她的设计具有浓浓拉美热烈奔放的异域风格。
         */

        private int follow_num;
        private String city;
        private String concept;
        private int article_num;
        private String name;
        private int product_num;
        private String label;
        private String avatar_url;
        private int is_followed;
        private int id;
        private String description;
        private List<String> introduce_images;
        private List<CategoriesBean> categories;

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

        public int getArticle_num() {
            return article_num;
        }

        public void setArticle_num(int article_num) {
            this.article_num = article_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProduct_num() {
            return product_num;
        }

        public void setProduct_num(int product_num) {
            this.product_num = product_num;
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

        public int getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(int is_followed) {
            this.is_followed = is_followed;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getIntroduce_images() {
            return introduce_images;
        }

        public void setIntroduce_images(List<String> introduce_images) {
            this.introduce_images = introduce_images;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            /**
             * id : 2
             * name : 配饰
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
}
