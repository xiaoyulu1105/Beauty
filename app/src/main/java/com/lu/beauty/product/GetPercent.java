package com.lu.beauty.product;

/**
 * Created by XiaoyuLu on 16/11/25.
 *
 * 获得喜欢 和 不喜欢的百分比 和 高度
 * 在画脸要用
 */

public class GetPercent {

    private static final double MAX_HEIGHT = 500; // 最高的高度,默认为500

    // 获取 喜欢和不喜欢的百分比
    public static double getLikeCount(double likeNum, double uLikeNum) {
        double LikeCount = (likeNum / (likeNum + uLikeNum)) * 100;

        return LikeCount;
    }
    public static double getDislikeCount(double likeNum, double uLikeNum) {
        double noLike = (uLikeNum / (likeNum + uLikeNum)) * 100;

        return noLike;
    }


    // 获取 喜欢和不喜欢的最终高度
    public static double getDislikeHigh(double likeNum, double uLikeNum) {
        double noLikeHigh = (uLikeNum / (likeNum + uLikeNum)) * MAX_HEIGHT;
        return noLikeHigh;
    }
    public static double getLikeHigh(double likeNum, double uLikeNum) {
        double likeHigh = (likeNum / (likeNum + uLikeNum)) * MAX_HEIGHT;
        return likeHigh;
    }
}
