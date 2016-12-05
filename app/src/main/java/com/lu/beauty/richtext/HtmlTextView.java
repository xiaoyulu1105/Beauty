package com.lu.beauty.richtext;

import android.content.Context;
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

import com.lu.beauty.article.ArticleDetailActivity;
import com.lu.beauty.article.ArticleImageSingleton;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by XiaoyuLu on 16/12/1.
 *
 * 在这个类书写点击图片后的操作
 */

public class HtmlTextView extends TextView{

    // 选择了复写两个构造方法
    public HtmlTextView(Context context) {
        super(context);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
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
                if (what == 200) {
                    MessageSpan ms = (MessageSpan)msg.obj;
                    Object[] spans = (Object[]) ms.getObject();
                    for (Object span : spans) {
                        if (span instanceof ImageSpan) {

                            Log.d("HtmlTextView", "点击了图片");
                            Toast.makeText(getContext(), "点击了图片", Toast.LENGTH_SHORT).show();

                            ArticleImageSingleton mArticleImageSingleton; // 存放图片网址的 单例类
                            mArticleImageSingleton = ArticleImageSingleton.getInstance();
                            ArrayList<String> arrayList = new ArrayList<String>();

                            arrayList = mArticleImageSingleton.getImageUrlArrayList();
                            Log.d("HtmlTextView", "arrayList:" + arrayList); 

                            // TODO 点击图片后 的代码

                        }
                    }
                }
            }
        };
        setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
    }

}
