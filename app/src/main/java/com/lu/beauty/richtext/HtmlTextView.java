package com.lu.beauty.richtext;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import com.lu.beauty.article.ArticleBannerActivity;
import com.lu.beauty.article.ArticleDetailActivity;
import com.lu.beauty.article.ArticleImageSingleton;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by XiaoyuLu on 16/12/1.
 *
 * 在这个类 书写 点击图片后的操作
 */

public class HtmlTextView extends TextView{

    // 用于跳转时到轮播Activity的 key值
    public static final String INTENT_ARRAY_LIST_KEY = "ArrayList";
    public static final String INTENT_SOUR_URL_KEY = "String";
    public static final int MESSAGE_WHAT = 200;

    // 选择了复写两个构造方法
    public HtmlTextView(Context context) {
        super(context);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHtmlFromString(String html) {
        Html.ImageGetter imageGetter;
        imageGetter = new UrlImageGetter(getContext(), this);
        setText(Html.fromHtml(html, imageGetter, null));
        // 让 links 和 image 工作
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                if (what == MESSAGE_WHAT) {
                    MessageSpan ms = (MessageSpan)msg.obj;
                    Object[] spans = (Object[]) ms.getObject();
                    for (Object span : spans) {
                        if (span instanceof ImageSpan) {
                            // instanceof: 判断左边对象 是不是 右边类的实例

                            Log.d("HtmlTextView", "点击了 富文本 HtmlTextView 里的图片");

                            ArticleImageSingleton mArticleImageSingleton; // 存放图片网址的 单例类
                            mArticleImageSingleton = ArticleImageSingleton.getInstance();
                            ArrayList<String> arrayList;

                            // 在这里 可以获取到单例类里已经存放的 集合数据
                            arrayList = mArticleImageSingleton.getImageUrlArrayList();
                            Log.d("HtmlTextView", "在 HtmlTextView 里获取到单例类里的集合长度" + arrayList.size());

                            Intent intent = new Intent(getContext(), ArticleBannerActivity.class);
                            intent.putExtra(INTENT_ARRAY_LIST_KEY, arrayList);
                            intent.putExtra(INTENT_SOUR_URL_KEY, ((ImageSpan) span).getSource());

                            getContext().startActivity(intent);

                        }
                    }
                }
            }
        };
        setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
    }

}
