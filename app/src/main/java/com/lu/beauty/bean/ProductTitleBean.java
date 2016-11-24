package com.lu.beauty.bean;

import java.util.List;

/**
 * If the operation is no problem, it is written by wangqiaosheng
 * , otherwise it is written by zhouyunxiao
 */

public class ProductTitleBean {

    /**
     * data : {"categories":[{"sub_categories":[{"id":24,"name":"项链"},{"id":23,"name":"耳环"},{"id":22,"name":"戒指"},{"id":21,"name":"手链"},{"id":20,"name":"手镯"}],"id":3,"name":"首饰"},{"sub_categories":[{"id":51,"name":"功能包"},{"id":32,"name":"双肩包"},{"id":10,"name":"旅行箱"},{"id":9,"name":"钱包"},{"id":8,"name":"手拿包"},{"id":7,"name":"手提包"},{"id":6,"name":"斜挎包"},{"id":5,"name":"单肩包"}],"id":1,"name":"包袋"},{"sub_categories":[{"id":14,"name":"高跟鞋"},{"id":49,"name":"短靴"},{"id":48,"name":"长靴"},{"id":38,"name":"运动鞋"},{"id":16,"name":"拖鞋"},{"id":15,"name":"平底鞋"},{"id":11,"name":"凉鞋"}],"id":2,"name":"鞋履"},{"id":65,"name":"Men"},{"sub_categories":[{"id":53,"name":"手表"},{"id":52,"name":"袖扣"},{"id":45,"name":"胸针"},{"id":37,"name":"领带"},{"id":36,"name":"发饰"},{"id":29,"name":"腰带"},{"id":27,"name":"丝巾"},{"id":26,"name":"围巾"},{"id":25,"name":"墨镜"}],"id":4,"name":"配饰"},{"sub_categories":[{"id":68,"name":"家居饰品"},{"id":64,"name":"文具"},{"id":61,"name":"家具"},{"id":58,"name":"数码配件"},{"id":56,"name":"香薰"},{"id":42,"name":"钥匙扣"}],"id":54,"name":"其他"}]}
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
        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            /**
             * sub_categories : [{"id":24,"name":"项链"},{"id":23,"name":"耳环"},{"id":22,"name":"戒指"},{"id":21,"name":"手链"},{"id":20,"name":"手镯"}]
             * id : 3
             * name : 首饰
             */

            private int id;
            private String name;
            private List<SubCategoriesBean> sub_categories;

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

            public List<SubCategoriesBean> getSub_categories() {
                return sub_categories;
            }

            public void setSub_categories(List<SubCategoriesBean> sub_categories) {
                this.sub_categories = sub_categories;
            }

            public static class SubCategoriesBean {
                /**
                 * id : 24
                 * name : 项链
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
}
