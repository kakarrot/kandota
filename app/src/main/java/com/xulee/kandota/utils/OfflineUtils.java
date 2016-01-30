package com.xulee.kandota.utils;

import com.liuguangqiang.framework.utils.StorageUtils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Eric on 15/5/7.
 */
public class OfflineUtils {

    private static final String TAG = "OfflineUtils";

    private static final String ROOT = "shuba";

    private static final String CHAPTRES = "chapters";

    private static final String OFFLINE_PATH = ROOT + "/" + CHAPTRES;

    private static final String ROOT_PATH = StorageUtils.getSDCardPath() + "/" + OFFLINE_PATH;

    public static void createOfflineFolder() {
        FileUtils.createFolder(OFFLINE_PATH);
    }

    public static void createChapterFolder(String bookId) {
        String path = String.format("%s/%s", OFFLINE_PATH, bookId);
        FileUtils.createFolder(path);
    }

    public static File getOfflineFile(String bookId, String chapterId) {
        return new File(getOfflinePath(bookId, chapterId));
    }

    public static String getOfflinePath(String bookId, String chapterId) {
        return String.format("%s/%s/%s", ROOT_PATH, bookId, chapterId);
    }

    public static boolean saveChapter(String bookId, String chapterId, String content) {
        FileOutputStream fileOutputStream;
        File file = getOfflineFile(bookId, chapterId);
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
