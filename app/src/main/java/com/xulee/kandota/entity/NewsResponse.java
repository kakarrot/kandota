package com.xulee.kandota.entity;

import java.util.List;

/**
 * 资讯页请求
 * Created by LX on 2016/1/27.
 */
public class NewsResponse {

    public String status;
    public String message;
    public RecommendTvData recommend_tv[]; //两个推荐电视位数据，今日视线，汕头新闻
    public List<News> news;    //新闻分类数据
    public List<RecommendNews> recommend_news; //轮播图数据

    public class RecommendTvData {
        public String fid;
        public String title;
        public String image;
    }

    public class News {
        public String fid;
        public String title;
        public List<NewsData> data;

        public class NewsData {
            public String title;
            public String img;
            public String tid;
            public String date;
            public String linktype;
            public String link_content;
        }
    }

    public class RecommendNews {
        public String title;
        public String img;
        public String tid;
        public String linktype;
        public String link_content;
    }
}
