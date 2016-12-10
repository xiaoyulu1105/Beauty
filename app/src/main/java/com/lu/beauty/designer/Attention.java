package com.lu.beauty.designer;

import cn.bmob.v3.BmobObject;

/**
 * Created by XiaoyuLu on 16/12/9.
 *
 * 这个类是 存放设计师信息的 数据表类
 */

public class Attention extends BmobObject {

    private String attentionId;  // 设计师的id
    private String attentionName;
    private String attentionLabel;
    private String attentionAvatar;
    private String attentionImage;

    // 才用一对一, 或一对多的方式
    private AttentionUser myUser;

    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
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

    public AttentionUser getMyUser() {
        return myUser;
    }

    public void setMyUser(AttentionUser myUser) {
        this.myUser = myUser;
    }
}
