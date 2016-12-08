package com.lu.beauty.designer;

import com.lu.beauty.bean.DesignerHeadlineBean;
import com.lu.beauty.bean.DesignerRecommendBean;

/**
 * Created by GuoXuanYu on 16/11/28.
 */

public interface DesignerClickListener {
    void headItemClick(DesignerRecommendBean.DataBean.CategoriesBeanX beanX);
    void popItemClick(DesignerHeadlineBean.DataBean.CategoriesBean.SubCategoriesBean bean);
    void allAdapterItemClick(int id);

}
