package com.lu.beauty.richtext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lu.beauty.R;

/**
 * Created by XiaoyuLu on 16/12/1.
 *
 * 测试结果出来了, 可是没有图片, 是因为复制过来的字符串图片链接 外自动多了 两个\\
 * 第一个图片多出来的\\已被删掉
 */

public class RichTextTestActivity extends AppCompatActivity {

    private HtmlTextView mHtmlTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_text_test);

        mHtmlTextView = (HtmlTextView) findViewById(R.id.rich_text_html_tv);
        String content = "<p>如果你将 Marni 的成衣系列分解剥离，只留下最基础的原料。这最终会落脚到色彩，布料和剪裁。对于所有衣服来说，这都是最基本的元素。但是在 Marni， 所有的基础元素都被使用到极致。布料经历了顶级的发展：被刮过的海狸皮、海绵般的技术平织物和上釉的法兰绒——色彩极其单调，剪裁更像是叙述而不是简单的切割。 </p><p><img src=\"http://dstatic.zuimeia.com/media/article/image/2016/11/28/521d1eaa-d291-4dbc-8594-1bfefc8226fb_800x1200.jpeg\"/></p><p>在 Marni 创始人 Castiglioni 的心中，Marni 男人和女人都来自于同一个精神自由且优雅的世界，当然他们还要很幽默，总之，就是那一类能够理解 Marni 超乎寻常奢侈感（只可意会不可言传）的人。从来没有受过正式时尚训练的  Castiglioni 之所以能够将品牌带入世界创意的巅峰，也许就是因为那双对美丽高度敏感的美学之眼（比千轮眼更厉害的 the eye of beauty)。 </p><p><img src=\\\"http://dstatic.zuimeia.com/media/article/image/2016/11/28/b8f2fa30-d97a-45f5-9592-c873b54dadb5_800x1200.jpeg\\\"/></p><p>意大利品牌本质上就是对人性的探索，每穿一次衣服就相当于一次心理疗程。而 Castiglioni 和 Nickerson 的合作更是天才的碰撞以及心灵恰到好处的相遇。他们让我们记住 Marini 男人：外在强悍，但内心却有诸多新奇古怪的细腻想法。 </p><p><img src=\\\"http://dstatic.zuimeia.com/media/article/image/2016/11/28/b52ce4b0-ea4b-475c-b702-c62b7d7440e2_800x1200.jpeg\\\"/></p><p><!--?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?--><span style=\\\"text-align: center;\\\">Castiglioni 本人有许多奇怪的想法，她的设计从头到尾都是被色彩所驱使。Marni 品牌本质上就是对所有色彩组合可能性的实践。毕竟，对她来说，颜色的使用太重要了。</span> <span style=\\\"background-color: rgb(255, 255, 255); text-align: center;\\\">Castiglioni</span> <span style=\\\"text-align: center;\\\">从来不用单一的黑色，相反，她尝试从色彩的组合中获取灵感，然后创造出更强有力、更叹为观止、但又不会轻易被发觉的东西。「定义它们对我来说是太自然的事情了。」Castiglioni 这样说道。</span></p><p><img src=\\\"http://dstatic.zuimeia.com/media/article/image/2016/11/28/826b54a9-0f2b-4852-8930-c14a7cafb95e_800x1200.jpeg\\\"/></p><p>Castiglioni 既是艺术家也是赞助人。在她看来，艺术家们与其把自己的画作挂在展馆画廊，不如和她一起设计 Marni 系列。在追逐创造的过程中，灵感也是不断涌来。「我很期待和下一位艺术家合作，不管他是已经成名还是初出茅庐。」Castiglioni 接着说道，「我希望我的衣服能成为可以穿戴的艺术。我喜欢实验并且把不同的世界混合在一起，并试图让艺术能够自然地融入日常生活。在当今能够穿戴的艺术才是最大的奢侈。」 </p><p><img src=\\\"http://dstatic.zuimeia.com/media/article/image/2016/11/28/8eae05d8-531b-42ec-a504-49397de58f3d_417x500.jpeg\\\"/></p><p>今年10 月 Castiglioni 决定离开 Marni，而她的继任者是前 Prada 的女装总监：Peter Dundas。Castiglioni 说，当年一手创造了 Marni 并用毕生精力把它推向时尚的巅峰，现在她需要更多的时间去过自己的生活。然而这背后到底有怎样的「真相」，只能等吃瓜群众慢慢去八卦了。 </p><p><br/> <!--?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?--><span style=\\\"color: rgb(145, 145, 145);\\\">（本篇译自 OKI -NI ，作者 Lewis Chong，图片均来源于原文）</span></p>";
        mHtmlTextView.setHtmlFromString(content);
    }
}
