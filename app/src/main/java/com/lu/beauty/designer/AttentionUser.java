package com.lu.beauty.designer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by  AngleXiao on 16/12/6.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class AttentionUser extends BmobUser{

    private int attentionCount = 0; // 关注的设计 的总数, 默认为0;

    public int getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

    private String  attentionId;

    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }


        private String attentionName;
        private String attentionLabel;
        private String attentionAvatar;
        private String attentionImage;
    private ArrayList<String> attentionList; // 关注的集合


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

    public ArrayList<String> getAttentionList() {
        return attentionList;
    }

    public void setAttentionList(ArrayList<String> attentionList) {
        this.attentionList = attentionList;
    }
}
