package com.lu.beauty.bean;

import java.util.List;

/**
 * Created by XiaoyuLu on 16/11/28.
 *
 * 画报的数据类
 */

public class ArticleBean {


    /**
     * data : {"has_next":1,"articles":[{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"Sarah Burton：平凡的天才","sub_title":"关于 McQueen、黑暗、死亡与爱","favor_user_num":5,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/25/8642627e-7812-48b8-9312-8f3e55ede0d8_675x675.jpeg","publish_at":1480089600000,"id":116},{"author":{"username":"独角兽","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/11/07/7e8ae58a7b893867d1f6f10b4c509e61_200x200.jpg","id":70834,"sign":"听说是时尚界里最不时尚的土酷女孩"},"title":"色彩、几何感与异域风情：名利场里人人都爱 CA&LOU","sub_title":"两位法国女设计师，掀起珠宝界文艺复兴的风潮","favor_user_num":7,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/21/b0a788df-6bfd-4ce6-a19f-a39291b4e657_640x640.jpeg","publish_at":1479916800000,"id":113},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"环形世界的女王陛下","sub_title":"环住女人的优雅和永恒","favor_user_num":6,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/22/dcd07827-ae3a-4f18-9642-a5141d0e1db2_806x806.jpeg","publish_at":1479830400000,"id":114},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"冷冽的金属在手间绽放女神之光","sub_title":"轻轻一提，就撬动了整个手包世界","favor_user_num":7,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/21/45c0eb83-7b15-4088-84d4-c47889ceec4b_800x800.jpeg","publish_at":1479744000000,"id":112},{"author":{"username":"独角兽","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/11/07/7e8ae58a7b893867d1f6f10b4c509e61_200x200.jpg","id":70834,"sign":"听说是时尚界里最不时尚的土酷女孩"},"title":"穿上一双 Le Flow，游荡在巴黎的左岸与右岸","sub_title":"在流动中，寻找自己内心的韵律与平衡","favor_user_num":13,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/16/5eaa528c-c5c5-48ee-b6fc-0748a56895e8_750x750.jpeg","publish_at":1479571200000,"id":111},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"在她手绘的简笔自然里，只想做一只慵懒十足的猫","sub_title":"把最朴素的自然，绘在生活的细枝末节","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/16/ae1129a3-2b16-40e8-9ebe-5b43e9d61b93_800x800.jpeg","publish_at":1479484800000,"id":110},{"author":{"username":"马力","avatar_url":"http://wx.qlogo.cn/mmopen/B8HPPaibicsOxmcr8icntPeXBUZOQlIZmVRN2zDicKVuSVuCiadJ2NEzuIZ0HCVlAcYa70hdBjiax3Ej3MYQ0FTPL4vg/0","id":5091,"sign":"最美大当家"},"title":"你能为我留住风么？","sub_title":"能够留住风和时光的项链","favor_user_num":33,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/16/1e89e9b5-fce5-4386-9a3e-8d50e2b18482_936x936.jpeg","publish_at":1479312000000,"id":109},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"你今天心情如何","sub_title":"如果感到开心，你就\u2026\u2026戴上它","favor_user_num":12,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/15/0725a314-f107-4dab-bb71-6dca95152472_800x800.jpeg","publish_at":1479225600000,"id":108},{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"Cuero & Mor：极简包袋背后的创意爱侣","sub_title":"关于他们的设计、生活、与爱情","favor_user_num":11,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/15/0df06c86-f530-4b0c-b440-81eda1817209_1000x1000.jpeg","publish_at":1479139200000,"id":107},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"在他的世界里，你只是一个长着人类头颅的动物","sub_title":"一不小心也会生出章鱼触角和大象鼻子","favor_user_num":19,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/6/f2cbc126-d8f8-4b72-a161-eb21b16c127c_500x500.jpeg","publish_at":1478966400000,"id":102},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"Sarah Law：极简信条","sub_title":"为去除人生的无谓繁复而设计","favor_user_num":15,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/7/f48b7a39-021a-4bb8-bd43-92d5921fbc91_800x800.jpeg","publish_at":1478880000000,"id":104},{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"从女孩到女人：美好的高跟鞋，伴你一同成长","sub_title":"从模仿他人到取悦自我的心灵之旅","favor_user_num":24,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/9/4f4f36d9-30b0-45cc-8f25-6045ac2de8bb_675x675.jpeg","publish_at":1478707200000,"id":106},{"author":{"username":"独角兽","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/11/07/7e8ae58a7b893867d1f6f10b4c509e61_200x200.jpg","id":70834,"sign":"听说是时尚界里最不时尚的土酷女孩"},"title":"Amanda Thomas：一半是女人，一半是坏蛋","sub_title":"邪恶的性感，往往是一种致命的诱惑","favor_user_num":18,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/7/ef9249dd-6c74-4ce1-be20-8727565c8b29_640x640.jpeg","publish_at":1478620800000,"id":105},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"Tom Ford：我的 24 小时","sub_title":"世界上最性感的设计师的一天是怎么度过的","favor_user_num":24,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/7/0150113a-49bb-444b-bd6c-aea5b68291ab_960x1200.jpeg","publish_at":1478534400000,"id":103},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"如何在小物件上，架构自己的生活美学","sub_title":"如果不够有趣，那么还是不要做了","favor_user_num":35,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/72278f4c-630f-4db0-b8c1-829a6a4df693_480x480.jpeg","publish_at":1478361600000,"id":100},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"年轻人的特权：鲜活的人生不该被黑白灰所禁锢","sub_title":"向鲜艳的岁月和敢于恶搞的年龄致敬","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/c87d16d0-3063-4946-9b6f-e02783e506bc_800x800.jpeg","publish_at":1478275200000,"id":98},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"把秘密花园涂在现实中，是不是就能开出七色花","sub_title":"Michal Golan：描绘一个七彩斑斓的童话世界","favor_user_num":26,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/8dce510a-882c-455a-b575-678f1f85341a_800x800.jpeg","publish_at":1478102400000,"id":99},{"author":{"username":"RainbowMe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/29/0ce86625189bc5c071bdd4fc8ccabae9_170x170.jpg","id":29,"sign":"最美有物 创始人"},"title":"放弃粉色的公主梦之后，你才有机会活成一个金色的女王","sub_title":"毕竟女王更懂得如何用设计宠爱自己","favor_user_num":30,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/2/308bfab3-da72-4282-a9cd-64d6c3045989_750x750.jpeg","publish_at":1478016000000,"id":101},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"和 Alexander McQueen 肩并肩战斗的男人","sub_title":"他和 McQueen 分别改变了珠宝界和时尚界","favor_user_num":9,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/192d53bc-69f2-4211-8fdc-c700d5d2ab99_800x800.jpeg","publish_at":1477929600000,"id":97},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"禅与皮具制造的艺术","sub_title":"皮具的良质，在于设计者自身的自在和诚实","favor_user_num":14,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/29/12644649-23ba-4c03-9473-8ab847c2a665_750x750.jpeg","publish_at":1477738020000,"id":96},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"小岛上的金匠姑娘，找到了自己梦中的青鸟","sub_title":"把喜欢的事做成了工作，谁说一定不会快乐？","favor_user_num":26,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/28/a0fc0c93-0c25-42b3-83d3-ca361fd8dcb3_750x750.jpeg","publish_at":1477622340000,"id":95},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"Todd Pownell：以山川之名，写一首情诗","sub_title":"献给我所深爱的大自然和你","favor_user_num":32,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/26/a9bc7ea9-2887-485b-b654-9b17e973e124_548x548.jpeg","publish_at":1477497600000,"id":94},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"空到近于无，于是你看到了生活本来的样子","sub_title":"接近一无所有的透明里，才能看到云朵的倒影","favor_user_num":20,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/26/f9ab4ecb-cf61-4e3a-9294-afff7c4d5120_944x944.jpeg","publish_at":1477453920000,"id":93},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"我想，我是忘记了长大","sub_title":"长不大的设计师，做出了俏皮的童话首饰","favor_user_num":58,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/462f6a13-b875-4958-9602-d0dcf1832b90_800x800.jpeg","publish_at":1477065600000,"id":91},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"为动物的自由而战","sub_title":"素食主义者的设计之路","favor_user_num":27,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/2f720e4b-98bf-4222-a069-66fad8007d99_809x809.jpeg","publish_at":1476979200000,"id":90},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"当你不再试图从一个包中确定自己","sub_title":"KikaNY：还原一个包最应该有的简单状态","favor_user_num":23,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/7ac855a9-5680-4368-8ded-68cccf1bd2e3_800x800.jpeg","publish_at":1476892800000,"id":89},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"为阿尔法女度身定制的时尚","sub_title":"时尚是每个女人的事， 即使你选择同你的事业结婚","favor_user_num":13,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/30f07a18-c4d3-434f-9d90-8833f7f86f94_505x505.jpeg","publish_at":1476720000000,"id":88},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"领带的文艺时代","sub_title":"在别人正襟危坐时，我偏要玩世不恭","favor_user_num":39,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/13/74bea20e-c2f8-4031-9fcf-6eb7d304eef5_800x800.jpeg","publish_at":1476460800000,"id":86},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"从小在祖父的地毯上捡黄金的女孩，用金子去描绘山峦温柔的曲线","sub_title":"她把爬过的山，都做成了戒指","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/12/ecae22a0-cf65-4843-b6a5-1ff50049294a_750x750.jpeg","publish_at":1476288000000,"id":87},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"Donatella Versace：她为大牌续写着神话","sub_title":"她披上了时尚的铠甲，并让全世界都记住了她","favor_user_num":14,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/10/99162390-bb9a-41b9-86b5-9d13c5cff320_800x602.jpeg","publish_at":1476115200000,"id":85}]}
     * result : 1
     */

    private DataBean data;
    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * has_next : 1
         * articles : [{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"Sarah Burton：平凡的天才","sub_title":"关于 McQueen、黑暗、死亡与爱","favor_user_num":5,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/25/8642627e-7812-48b8-9312-8f3e55ede0d8_675x675.jpeg","publish_at":1480089600000,"id":116},{"author":{"username":"独角兽","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/11/07/7e8ae58a7b893867d1f6f10b4c509e61_200x200.jpg","id":70834,"sign":"听说是时尚界里最不时尚的土酷女孩"},"title":"色彩、几何感与异域风情：名利场里人人都爱 CA&LOU","sub_title":"两位法国女设计师，掀起珠宝界文艺复兴的风潮","favor_user_num":7,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/21/b0a788df-6bfd-4ce6-a19f-a39291b4e657_640x640.jpeg","publish_at":1479916800000,"id":113},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"环形世界的女王陛下","sub_title":"环住女人的优雅和永恒","favor_user_num":6,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/22/dcd07827-ae3a-4f18-9642-a5141d0e1db2_806x806.jpeg","publish_at":1479830400000,"id":114},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"冷冽的金属在手间绽放女神之光","sub_title":"轻轻一提，就撬动了整个手包世界","favor_user_num":7,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/21/45c0eb83-7b15-4088-84d4-c47889ceec4b_800x800.jpeg","publish_at":1479744000000,"id":112},{"author":{"username":"独角兽","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/11/07/7e8ae58a7b893867d1f6f10b4c509e61_200x200.jpg","id":70834,"sign":"听说是时尚界里最不时尚的土酷女孩"},"title":"穿上一双 Le Flow，游荡在巴黎的左岸与右岸","sub_title":"在流动中，寻找自己内心的韵律与平衡","favor_user_num":13,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/16/5eaa528c-c5c5-48ee-b6fc-0748a56895e8_750x750.jpeg","publish_at":1479571200000,"id":111},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"在她手绘的简笔自然里，只想做一只慵懒十足的猫","sub_title":"把最朴素的自然，绘在生活的细枝末节","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/16/ae1129a3-2b16-40e8-9ebe-5b43e9d61b93_800x800.jpeg","publish_at":1479484800000,"id":110},{"author":{"username":"马力","avatar_url":"http://wx.qlogo.cn/mmopen/B8HPPaibicsOxmcr8icntPeXBUZOQlIZmVRN2zDicKVuSVuCiadJ2NEzuIZ0HCVlAcYa70hdBjiax3Ej3MYQ0FTPL4vg/0","id":5091,"sign":"最美大当家"},"title":"你能为我留住风么？","sub_title":"能够留住风和时光的项链","favor_user_num":33,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/16/1e89e9b5-fce5-4386-9a3e-8d50e2b18482_936x936.jpeg","publish_at":1479312000000,"id":109},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"你今天心情如何","sub_title":"如果感到开心，你就\u2026\u2026戴上它","favor_user_num":12,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/15/0725a314-f107-4dab-bb71-6dca95152472_800x800.jpeg","publish_at":1479225600000,"id":108},{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"Cuero & Mor：极简包袋背后的创意爱侣","sub_title":"关于他们的设计、生活、与爱情","favor_user_num":11,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/15/0df06c86-f530-4b0c-b440-81eda1817209_1000x1000.jpeg","publish_at":1479139200000,"id":107},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"在他的世界里，你只是一个长着人类头颅的动物","sub_title":"一不小心也会生出章鱼触角和大象鼻子","favor_user_num":19,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/6/f2cbc126-d8f8-4b72-a161-eb21b16c127c_500x500.jpeg","publish_at":1478966400000,"id":102},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"Sarah Law：极简信条","sub_title":"为去除人生的无谓繁复而设计","favor_user_num":15,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/7/f48b7a39-021a-4bb8-bd43-92d5921fbc91_800x800.jpeg","publish_at":1478880000000,"id":104},{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"从女孩到女人：美好的高跟鞋，伴你一同成长","sub_title":"从模仿他人到取悦自我的心灵之旅","favor_user_num":24,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/9/4f4f36d9-30b0-45cc-8f25-6045ac2de8bb_675x675.jpeg","publish_at":1478707200000,"id":106},{"author":{"username":"独角兽","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/11/07/7e8ae58a7b893867d1f6f10b4c509e61_200x200.jpg","id":70834,"sign":"听说是时尚界里最不时尚的土酷女孩"},"title":"Amanda Thomas：一半是女人，一半是坏蛋","sub_title":"邪恶的性感，往往是一种致命的诱惑","favor_user_num":18,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/7/ef9249dd-6c74-4ce1-be20-8727565c8b29_640x640.jpeg","publish_at":1478620800000,"id":105},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"Tom Ford：我的 24 小时","sub_title":"世界上最性感的设计师的一天是怎么度过的","favor_user_num":24,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/7/0150113a-49bb-444b-bd6c-aea5b68291ab_960x1200.jpeg","publish_at":1478534400000,"id":103},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"如何在小物件上，架构自己的生活美学","sub_title":"如果不够有趣，那么还是不要做了","favor_user_num":35,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/72278f4c-630f-4db0-b8c1-829a6a4df693_480x480.jpeg","publish_at":1478361600000,"id":100},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"年轻人的特权：鲜活的人生不该被黑白灰所禁锢","sub_title":"向鲜艳的岁月和敢于恶搞的年龄致敬","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/c87d16d0-3063-4946-9b6f-e02783e506bc_800x800.jpeg","publish_at":1478275200000,"id":98},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"把秘密花园涂在现实中，是不是就能开出七色花","sub_title":"Michal Golan：描绘一个七彩斑斓的童话世界","favor_user_num":26,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/8dce510a-882c-455a-b575-678f1f85341a_800x800.jpeg","publish_at":1478102400000,"id":99},{"author":{"username":"RainbowMe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/29/0ce86625189bc5c071bdd4fc8ccabae9_170x170.jpg","id":29,"sign":"最美有物 创始人"},"title":"放弃粉色的公主梦之后，你才有机会活成一个金色的女王","sub_title":"毕竟女王更懂得如何用设计宠爱自己","favor_user_num":30,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/11/2/308bfab3-da72-4282-a9cd-64d6c3045989_750x750.jpeg","publish_at":1478016000000,"id":101},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"和 Alexander McQueen 肩并肩战斗的男人","sub_title":"他和 McQueen 分别改变了珠宝界和时尚界","favor_user_num":9,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/31/192d53bc-69f2-4211-8fdc-c700d5d2ab99_800x800.jpeg","publish_at":1477929600000,"id":97},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"禅与皮具制造的艺术","sub_title":"皮具的良质，在于设计者自身的自在和诚实","favor_user_num":14,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/29/12644649-23ba-4c03-9473-8ab847c2a665_750x750.jpeg","publish_at":1477738020000,"id":96},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"小岛上的金匠姑娘，找到了自己梦中的青鸟","sub_title":"把喜欢的事做成了工作，谁说一定不会快乐？","favor_user_num":26,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/28/a0fc0c93-0c25-42b3-83d3-ca361fd8dcb3_750x750.jpeg","publish_at":1477622340000,"id":95},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"Todd Pownell：以山川之名，写一首情诗","sub_title":"献给我所深爱的大自然和你","favor_user_num":32,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/26/a9bc7ea9-2887-485b-b654-9b17e973e124_548x548.jpeg","publish_at":1477497600000,"id":94},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"空到近于无，于是你看到了生活本来的样子","sub_title":"接近一无所有的透明里，才能看到云朵的倒影","favor_user_num":20,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/26/f9ab4ecb-cf61-4e3a-9294-afff7c4d5120_944x944.jpeg","publish_at":1477453920000,"id":93},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"我想，我是忘记了长大","sub_title":"长不大的设计师，做出了俏皮的童话首饰","favor_user_num":58,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/462f6a13-b875-4958-9602-d0dcf1832b90_800x800.jpeg","publish_at":1477065600000,"id":91},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"为动物的自由而战","sub_title":"素食主义者的设计之路","favor_user_num":27,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/2f720e4b-98bf-4222-a069-66fad8007d99_809x809.jpeg","publish_at":1476979200000,"id":90},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"当你不再试图从一个包中确定自己","sub_title":"KikaNY：还原一个包最应该有的简单状态","favor_user_num":23,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/7ac855a9-5680-4368-8ded-68cccf1bd2e3_800x800.jpeg","publish_at":1476892800000,"id":89},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"为阿尔法女度身定制的时尚","sub_title":"时尚是每个女人的事， 即使你选择同你的事业结婚","favor_user_num":13,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/30f07a18-c4d3-434f-9d90-8833f7f86f94_505x505.jpeg","publish_at":1476720000000,"id":88},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"领带的文艺时代","sub_title":"在别人正襟危坐时，我偏要玩世不恭","favor_user_num":39,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/13/74bea20e-c2f8-4031-9fcf-6eb7d304eef5_800x800.jpeg","publish_at":1476460800000,"id":86},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"从小在祖父的地毯上捡黄金的女孩，用金子去描绘山峦温柔的曲线","sub_title":"她把爬过的山，都做成了戒指","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/12/ecae22a0-cf65-4843-b6a5-1ff50049294a_750x750.jpeg","publish_at":1476288000000,"id":87},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"Donatella Versace：她为大牌续写着神话","sub_title":"她披上了时尚的铠甲，并让全世界都记住了她","favor_user_num":14,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/10/99162390-bb9a-41b9-86b5-9d13c5cff320_800x602.jpeg","publish_at":1476115200000,"id":85}]
         */

        private int has_next;
        private List<ArticlesBean> articles;

        public int getHas_next() {
            return has_next;
        }

        public void setHas_next(int has_next) {
            this.has_next = has_next;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public static class ArticlesBean {
            /**
             * author : {"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"}
             * title : Sarah Burton：平凡的天才
             * sub_title : 关于 McQueen、黑暗、死亡与爱
             * favor_user_num : 5
             * web_url :
             * image_url : http://dstatic.zuimeia.com/common/image/2016/11/25/8642627e-7812-48b8-9312-8f3e55ede0d8_675x675.jpeg
             * publish_at : 1480089600000
             * id : 116
             */

            private AuthorBean author;
            private String title;
            private String sub_title;
            private int favor_user_num;
            private String web_url;
            private String image_url;
            private long publish_at;
            private int id;

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public int getFavor_user_num() {
                return favor_user_num;
            }

            public void setFavor_user_num(int favor_user_num) {
                this.favor_user_num = favor_user_num;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public long getPublish_at() {
                return publish_at;
            }

            public void setPublish_at(long publish_at) {
                this.publish_at = publish_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class AuthorBean {
                /**
                 * username : Mana Bean
                 * avatar_url : http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg
                 * id : 16653
                 * sign : 一颗从金融学土壤中萌发的时尚之豆
                 */

                private String username;
                private String avatar_url;
                private int id;
                private String sign;

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }
            }
        }
    }
}
