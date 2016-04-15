package com.xulee.kandota.entity.base;

/**
 * Created by LX on 2016/4/14.
 */
public class HudongTagResponse extends BaseResponse {
    /*    {
            "status": "ok",
                "message": "获取标示位成功!",
                "data": {
            "uptime": "1460597877"
        }
        }*/
    public TagData data;

    public class TagData {
        public String uptime;
    }
}
