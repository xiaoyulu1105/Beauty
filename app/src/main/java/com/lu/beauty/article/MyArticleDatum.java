package com.lu.beauty.article;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Target;

/**
 * Created by XiaoyuLu on 16/11/28.
 *
 * 参照 Datum类书写的 画报的第一级的数据的 序列化的类
 * 未使用该类, 使用的是 model 里的 Datum类
 */

public class MyArticleDatum implements Parcelable {

    public int id;
    public String avatar_url;
    public String username; // 作者名字
    public String title;     // 标题
    public String sub_title;   // 副标题
    public String image_url;
    public Target avatar_url_target; // 作者头像占位
    public Target image_url_target; // 图片占位 , Target放二次网络请求的图片

    public MyArticleDatum() {
        // 空的构造方法
    }

    protected MyArticleDatum(Parcel in) {
        id = in.readInt();
        avatar_url = in.readString();
        username = in.readString();
        title = in.readString();
        sub_title = in.readString();
        image_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(avatar_url);
        dest.writeString(username);
        dest.writeString(title);
        dest.writeString(sub_title);
        dest.writeString(image_url);
    }

    public static final Creator<MyArticleDatum> CREATOR = new Creator<MyArticleDatum>() {
        @Override
        public MyArticleDatum createFromParcel(Parcel in) {
            return new MyArticleDatum(in);
        }

        @Override
        public MyArticleDatum[] newArray(int size) {
            return new MyArticleDatum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return ((MyArticleDatum)obj).id == this.id;
    }
}
