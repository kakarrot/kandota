package com.xulee.kandota.entity;

import com.xulee.kandota.entity.base.BaseResponse;

import java.util.List;

/**
 * Created by LX on 2016/4/14.
 */
public class HudongResponse  extends BaseResponse {

    /**
     * data : [{"title":"【1025潮语歌曲排行榜】第13期上榜歌曲","img":"http://yao.img.cutv.com/attachment/forum/201602/19/142803l6j7jdnu6zzbn7ic.jpg","tid":"318775","linktype":"link","link_content":"http://yao.cutv.com/plugin.php?id=cutv_shake:viewthread&tid=318775"},{"title":"欢迎提供奖品 电话：83938167 83938707","img":"http://yao.img.cutv.com/attachment/forum/201511/03/133443tkn46dnww6ord6kw.png","tid":"281791","linktype":"link","link_content":"http://yao.cutv.com/plugin.php?id=cutv_shake:viewthread&tid=281791"},{"title":"\u201c看乜个？橄榄台\u201d服务功能呾你知！","img":"http://yao.img.cutv.com/attachment/forum/201511/03/131205ywiu8u7oe5480uzw.png","tid":"234204","linktype":"link","link_content":"http://yao.cutv.com/plugin.php?id=cutv_shake:viewthread&tid=234204"},{"title":"\u201c看乜个？橄榄台\u201d资讯功能呾你知！","img":"http://yao.img.cutv.com/attachment/forum/201511/03/131211mguun9by3vvceuyz.png","tid":"233317","linktype":"link","link_content":"http://yao.cutv.com/plugin.php?id=cutv_shake:viewthread&tid=233317"},{"title":"\u201c看乜个？橄榄台\u201d互动功能呾你知！","img":"http://yao.img.cutv.com/attachment/forum/201511/03/131253pw0hueuziqyyb7ux.png","tid":"233313","linktype":"link","link_content":"http://yao.cutv.com/plugin.php?id=cutv_shake:viewthread&tid=233313"}]
     * theme : [{"area":1,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/1_yaoyiyao_1.png?v=9","func":1},{"area":2,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/2_huodongquan_14.png?v=9","func":12},{"area":3,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/3_ggyao_27.png?v=9","func":27},{"area":4,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/8_xwbl_11.png?v=9","func":11},{"area":5,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/5_ccy_26.png?v=9","func":26},{"area":6,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/7_yhq_24.png?v=9","func":24},{"area":7,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/9_xxgg_14.png?v=9","func":14},{"area":8,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/4_dctp_8.png?v=9","func":8},{"area":9,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/11_yjfk_9.png?v=9","func":9},{"area":10,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/12_drx_28.png?v=9","func":28},{"area":11,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/dzp.png?v=9","func":4},{"area":12,"img":"http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/ggl.png?v=9","func":3}]
     * bgimage : http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/bgimage.jpg?v=9
     * ad : {"image":"","duration":""}
     * gonggao : []
     * channelinfo : {"cname":"汕头橄榄台","pic":"http://yao.cutv.com/data/attachment//portal/201501/30/074021itrncdbt665b42kc.png"}
     */

    public String bgimage;
    /**
     * image :
     * duration :
     */

    public AdEntity ad;
    /**
     * cname : 汕头橄榄台
     * pic : http://yao.cutv.com/data/attachment//portal/201501/30/074021itrncdbt665b42kc.png
     */

    public ChannelInfoEntity channelinfo;
    /**
     * title : 【1025潮语歌曲排行榜】第13期上榜歌曲
     * img : http://yao.img.cutv.com/attachment/forum/201602/19/142803l6j7jdnu6zzbn7ic.jpg
     * tid : 318775
     * linktype : link
     * link_content : http://yao.cutv.com/plugin.php?id=cutv_shake:viewthread&tid=318775
     */

    public List<DataEntity> data;
    /**
     * area : 1
     * img : http://yao.cutv.com/source/plugin/cutv_channel/theme/stgltv/image/new/1_yaoyiyao_1.png?v=9
     * func : 1
     */

    public List<ThemeEntity> theme;
    public List<GongGaoEntity> gonggao;

    public class AdEntity {
        public String image;
        public String duration;
    }

    public class ChannelInfoEntity {
        public String cname;
        public String pic;
    }

    public class DataEntity {
        public String title;
        public String img;
        public String tid;
        public String linktype;
        public String link_content;

    }

    public class ThemeEntity {
        public int area;
        public String img;
        public int func;
    }

    public class GongGaoEntity {
        public String title;
        public String tid;
        public String linktype;
        public String link_content;
    }
}
