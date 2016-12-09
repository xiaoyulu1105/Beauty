package com.lu.beauty.designer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by  AngleXiao on 16/12/6.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class AttentionUser extends BmobUser {

    private String attentionId;
    private String attentionName;
    private String attentionLabel;
    private String attentionAvatar;
    private String attentionImage;

    private int attentionCount = 0; // 关注的设计 的总数, 默认为0;
    //关注的数组, 每一项是一个 设计师类AttentionSingleBean 的集合类 Collections,压缩后的 json字符串
    private String[] attentionList;


    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }

    public int getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

    public String getAttentionName() {
        return attentionName;
    }

    public void setAttentionName(String attentionName) {
        this.attentionName = attentionName;
    }

    public String getAttentionLabel() {
        return attentionLabel;
    }

    public void setAttentionLabel(String attentionLabel) {
        this.attentionLabel = attentionLabel;
    }

    public String getAttentionAvatar() {
        return attentionAvatar;
    }

    public void setAttentionAvatar(String attentionAvatar) {
        this.attentionAvatar = attentionAvatar;
    }

    public String getAttentionImage() {
        return attentionImage;
    }

    public void setAttentionImage(String attentionImage) {
        this.attentionImage = attentionImage;
    }

    public String[] getAttentionList() {
        return attentionList;
    }

    public void setAttentionList(String[] attentionList) {
        this.attentionList = attentionList;
    }
}
