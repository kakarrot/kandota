package com.xulee.kandota.utils;

import com.liuguangqiang.framework.utils.StorageUtils;

import java.io.File;

/**
 * Created by Eric on 15/5/7.
 */
public class FileUtils {

    public static void createFolder(String path) {
        String root = StorageUtils.getSDCardPath();
        File file = new File(root, path);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
    }

}
