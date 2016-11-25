package com.lu.beauty.product;

/**
 * Created by XiaoyuLu on 16/11/25.
 *
 * 获得喜欢 和 不喜欢的百分比 和 高度
 */

public class GetPercent {

    private static double maxHeight; // 最高的高度, 你们想最高为多少, 就通过set方法设置多少

    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public static double getMaxHeight() {
        return maxHeight;
    }

    public static double getLikeHigh(double likeNum, double uLikeNum) {
        double likeHigh = (likeNum / (likeNum + uLikeNum)) * maxHeight;
        return likeHigh;
    }

    public static double getNoLikeCount(double likeNum, double uLikeNum) {
        double noLike = (uLikeNum / (likeNum + uLikeNum)) * 100;

        return noLike;
    }

    public static double getNoLikeHigh(double likeNum, double uLikeNum) {
        double noLikeHigh = (uLikeNum / (likeNum + uLikeNum)) * maxHeight;
        return noLikeHigh;
    }

    public static double getLikeCount(double likeNum, double uLikeNum) {
        double LikeCount = (likeNum / (likeNum + uLikeNum)) * 100;

        return LikeCount;
    }
}
