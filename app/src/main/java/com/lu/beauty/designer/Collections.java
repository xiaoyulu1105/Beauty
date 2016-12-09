package com.lu.beauty.designer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  AngleXiao on 16/12/7.
 * OLiGei  what is your name
 * 轻松拿下一个类 属实有牌面
 *
 * 每次用到这个集合其实都是只装载了 一个 数据类
 * 然后将该集合对象压缩 成其json格式的 字符串, 最后保存到 AttentionUser
 */

public class Collections {
    private ArrayList<AttentionSingleBean> mAttentionSingleBeens;

    public ArrayList<AttentionSingleBean> getAttentionSingleBeens() {
        return mAttentionSingleBeens;
    }

    public void setAttentionSingleBeens(ArrayList<AttentionSingleBean> attentionSingleBeens) {
        mAttentionSingleBeens = attentionSingleBeens;
    }
}
