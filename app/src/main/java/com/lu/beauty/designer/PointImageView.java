package com.lu.beauty.designer;

import android.content.Context;
import android.widget.ImageView;

import com.lu.beauty.R;

/**
 * Created by GuoXuanYu on 16/12/5.
 *
 * 轮播图中的 小菱形
 */

public class PointImageView extends ImageView {
    private Context context;

    public PointImageView(Context context) {
        super(context);
        this.context = context;
    }

    public void setSelected(boolean isSelected){
       // PointImageView point = new PointImageView(context);
        setPadding(10,10,10,10);
        if (isSelected){
            setImageResource(R.mipmap.ic_point_selected);
        }else {
            setImageResource(R.mipmap.ic_point_unselected);
        }
    }

}
