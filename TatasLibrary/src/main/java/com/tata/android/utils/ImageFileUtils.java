package com.tata.android.utils;


import com.tata.mnbz.config.Config;

/**
 * Desc:
 * Author: Terry
 * Date:2016-05-08
 */
public class ImageFileUtils {
    public static String getImageFilePath() {
        return Config.SDCARD_CACHE_IMAGE_PATH;
    }

    public static String getFileName() {
        return System.currentTimeMillis() + ".jpg";
    }

}
