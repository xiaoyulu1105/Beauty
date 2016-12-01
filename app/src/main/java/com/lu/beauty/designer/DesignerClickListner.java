package com.lu.beauty.designer;

import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;

/**
 * Created by GuoXuanYu on 16/11/28.
 */

public interface DesignerClickListner {
    public void headItemClick(DesignerRecommendBean.DataBean.CategoriesBeanX beanX);
    public void popItemClick(DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean bean);

}
