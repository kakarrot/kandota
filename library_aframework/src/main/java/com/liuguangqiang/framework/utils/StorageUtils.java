package com.liuguangqiang.framework.utils;

import android.os.Environment;

import java.io.File;

/**
 * StorageUtils
 * <p/>
 * Created by Eric on 2014-5-19.
 */
public class StorageUtils {

    private static final String SDCARD_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/";

    /**
     * Create a folder in SDCard.
     *
     * @param path such as : parent_path/folder_name
     */
    public static void createFolder(String path) {
        File file = new File(SDCARD_ROOT, path);
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Return the root path of SDCard.
     *
     * @return
     */
    public static String getSDCardPath() {
        return SDCARD_ROOT;
    }

    public static boolean sdCardIsAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * Return the size of a file.
     * <p>If the file is a directory,return the sum total of all files in this diretory.</p>
     *
     * @param file
     * @return size, unit:k
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size = size + getFileSize(fileList[i]);
            } else {
                size = size + fileList[i].length();
            }
        }
        return size;
    }

}
