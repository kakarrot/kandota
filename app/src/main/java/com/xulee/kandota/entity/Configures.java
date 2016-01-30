package com.xulee.kandota.entity;

/**
 * Created by Eric on 14-8-3.
 */
public class Configures {

    public Upgrade upgrade;

    public Functions functions;

    public class Upgrade {
        public Android android;
    }

    public class Android {

        public String url;

        public String ver;

    }


    public class Functions {

        /**
         * 是否开启智能阅读,0:关闭，1:开启
         */
        public int auto_reading = 1;

        public boolean autoReading() {
            return auto_reading == 1;
        }

    }

}
