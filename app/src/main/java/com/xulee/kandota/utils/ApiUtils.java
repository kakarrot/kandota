package com.xulee.kandota.utils;

/**
 * API工具类，生产不同的API地址。
 */
public class ApiUtils {

    private final static String HOST_NAME = "http://kandota.thnuclub.com/api_v2";

    public final static String FORMAT_3_PARAMS = HOST_NAME + "/%s/%s/%s";

    public final static String FORMAT_2_PARAMS = HOST_NAME + "/%s/%s";

    public final static String FORMAT_1_PARAMS = HOST_NAME + "/%s";

    public static String build(String param) {
        return String.format(FORMAT_1_PARAMS, param);
    }

    public static String build(String param1, String param2) {
        return String.format(FORMAT_2_PARAMS, param1, param2);
    }

    public static String build(String param1, String param2, String param3) {
        return String.format(FORMAT_3_PARAMS, param1, param2, param3);
    }

    /**
     * 获取视频列表。
     *
     * @return
     */
    public static String getMovies() {
        return String.format(FORMAT_1_PARAMS, "vidoes");
    }

    /**
     * 获取作者列表。
     *
     * @return
     */
    public static String getAuthors() {
        return String.format(FORMAT_1_PARAMS, "authors");
    }
    /**
     * 获取一个视频。
     *
     * @param movieId 视频id
     * @return
     */
    public static String getMovie(String movieId) {
        return String.format(FORMAT_2_PARAMS, ApiModels.movie, movieId);
    }

    /**
     * 获取相关推荐视频
     *
     * @param movieId
     * @return
     */
    public static String getRelated(String movieId) {
        return String.format(FORMAT_3_PARAMS, ApiModels.movie, movieId, ApiModels.related);
    }

    /**
     * 获取分类列表
     *
     * @return
     */
    public static String getCategories() {
        return String.format(FORMAT_1_PARAMS, ApiModels.categories.toString());
    }

    /**
     * 获取推荐列表
     *
     * @return
     */
    public static String getRecommend() {
        return String.format(FORMAT_2_PARAMS, ApiModels.movie, ApiModels.recommend);
    }

    /**
     * 获取视频排行
     *
     * @return
     */
    public static String getMoviesByTop() {
        return String.format(FORMAT_2_PARAMS, ApiModels.movie, ApiModels.top);
    }

    /**
     * 获取免责声明
     *
     * @return
     */
    public static String getStatement() {
        return String.format(FORMAT_1_PARAMS, "statement.txt");
    }

    /**
     * 检查版本更新
     *
     * @return
     */
    public static String getConfigures() {
        return String.format(FORMAT_1_PARAMS, ApiModels.configures);
    }

    /**
     * 检查Book是否更新
     *
     * @return
     */
    public static String checkBookUpdate() {
        return String.format(FORMAT_2_PARAMS, ApiModels.movie, ApiModels.updates);
    }

    /**
     * 赞
     *
     * @param movieId
     * @return
     */
    public static String postBookVoteUp(String movieId) {
        return String.format(FORMAT_3_PARAMS, ApiModels.movie, movieId, ApiModels.voteup);
    }

    public static String postFeedBack() {
        return String.format(FORMAT_1_PARAMS, ApiModels.feedbacks);
    }

    public static String putAuth() {
        return String.format(FORMAT_1_PARAMS, ApiModels.auths);
    }

    public static String postUser() {
        return String.format(FORMAT_1_PARAMS, ApiModels.users);
    }

    public static String getUser(String userId) {
        return String.format(FORMAT_2_PARAMS, ApiModels.users, userId);
    }

    public static String postLevel() {
        return String.format(FORMAT_1_PARAMS, ApiModels.levels);
    }

    public static String getMirrors() {
        return String.format(FORMAT_1_PARAMS, ApiModels.mirrors);
    }

    public static String putProgresses() {
        return String.format(FORMAT_1_PARAMS, ApiModels.progresses);
    }

    public static String getProgresses() {
        return String.format(FORMAT_1_PARAMS, ApiModels.progresses);
    }

    /**
     * Test
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getRelated("abcd"));
    }
}
