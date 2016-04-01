package com.xulee.kandota.entity;

/**
 * Created by Eric on 14-8-3.
 */
public class Configures {

    public String status;
    public String message;
    public UpGradeInfo data;

    public class UpGradeInfo {
        public String id;
        public String vercode;
        public String desc;
        public String device_type;
        public String source;
        public String dateline;
        public String downurl;
    }
}
