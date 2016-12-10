package com.lu.beauty.designer;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by  AngleXiao on 16/12/9.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 */

public class Post extends BmobObject {
    private String attentionName;
    private String attentionLabel;
    private String attentionAvatar;
    private String attentionImage;
private String content;
    private String  attentionId;
    private BmobRelation likes;
    private MyUser auther;
  private Post mPost;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return mPost;
    }

    public void setPost(Post post) {
        mPost = post;
    }

    public MyUser getAuther() {
        return auther;
    }

    public void setAuther(MyUser auther) {
        this.auther = auther;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
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

    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId;
    }
}
