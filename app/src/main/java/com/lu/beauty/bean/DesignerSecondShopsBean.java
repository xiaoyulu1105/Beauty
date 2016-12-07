package com.lu.beauty.bean;

import java.util.List;

/**
 * Created by GuoXuanYu on 16/12/3.
 */

public class DesignerSecondShopsBean {

    /**
     * data : {"shop_image":"http://dstatic.zuimeia.com/brand/shop/2016/8/25/5752c915-1441-4019-969b-1fd6295c06ee.jpg","shops":[{"city":"北京","address":"N3-11 and N3-21, New Sanlitun, North District of Beijing\r\n","id":143,"name":"Dsquared2"}],"online_shop_image":"http://dstatic.zuimeia.com/brand/shop/2016/8/25/edbed9fa-79a5-4364-b2b0-683e57d0b321.jpg","online_shops":[{"link_url":"http://www.dsquared2.com/wx","id":135,"name":"Dsquared2 官方网店"}]}
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
         * shop_image : http://dstatic.zuimeia.com/brand/shop/2016/8/25/5752c915-1441-4019-969b-1fd6295c06ee.jpg
         * shops : [{"city":"北京","address":"N3-11 and N3-21, New Sanlitun, North District of Beijing\r\n","id":143,"name":"Dsquared2"}]
         * online_shop_image : http://dstatic.zuimeia.com/brand/shop/2016/8/25/edbed9fa-79a5-4364-b2b0-683e57d0b321.jpg
         * online_shops : [{"link_url":"http://www.dsquared2.com/wx","id":135,"name":"Dsquared2 官方网店"}]
         */

        private String shop_image;
        private String online_shop_image;
        private List<ShopsBean> shops;
        private List<OnlineShopsBean> online_shops;

        public String getShop_image() {
            return shop_image;
        }

        public void setShop_image(String shop_image) {
            this.shop_image = shop_image;
        }

        public String getOnline_shop_image() {
            return online_shop_image;
        }

        public void setOnline_shop_image(String online_shop_image) {
            this.online_shop_image = online_shop_image;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public List<OnlineShopsBean> getOnline_shops() {
            return online_shops;
        }

        public void setOnline_shops(List<OnlineShopsBean> online_shops) {
            this.online_shops = online_shops;
        }

        public static class ShopsBean {
            /**
             * city : 北京
             * address : N3-11 and N3-21, New Sanlitun, North District of Beijing

             * id : 143
             * name : Dsquared2
             */

            private String city;
            private String address;
            private int id;
            private String name;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

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

        public static class OnlineShopsBean {
            /**
             * link_url : http://www.dsquared2.com/wx
             * id : 135
             * name : Dsquared2 官方网店
             */

            private String link_url;
            private int id;
            private String name;

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

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
