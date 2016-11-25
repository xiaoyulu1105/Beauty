package com.lu.beauty.product;

/**
 * Created by XiaoyuLu on 16/11/25.
 *
 * 获得喜欢 和 不喜欢的百分比 和 高度
 */

public class GetPercent {

    private  double maxHeight = 500; // 最高的高度,默认为500, 你们想最高为多少, 就通过set方法设置多少

    // 通过set方法设置 最高的高度值
    public  void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public  double getMaxHeight() {
        return maxHeight;
    }


    // 获取 喜欢和不喜欢的百分比
    public static double getLikeCount(double likeNum, double uLikeNum) {
        double LikeCount = (likeNum / (likeNum + uLikeNum)) * 100;

        return LikeCount;
    }
    public  double getDislikeCount(double likeNum, double uLikeNum) {
        double noLike = (uLikeNum / (likeNum + uLikeNum)) * 100;

        return noLike;
    }



    // 获取 喜欢和不喜欢的最终高度
    public  double getDislikeHigh(double likeNum, double uLikeNum) {
        double noLikeHigh = (uLikeNum / (likeNum + uLikeNum)) * maxHeight;
        return noLikeHigh;
    }
    public  double getLikeHigh(double likeNum, double uLikeNum) {
        double likeHigh = (likeNum / (likeNum + uLikeNum)) * maxHeight;
        return likeHigh;
    }
}
