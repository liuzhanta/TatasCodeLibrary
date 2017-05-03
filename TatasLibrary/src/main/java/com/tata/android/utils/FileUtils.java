package com.tata.android.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件操作相关的工具方法集合
 */
public class FileUtils {

    /**
     * 检查外部存储是否可读写
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 检查外部存储是否可读
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 判断文件是否存在
     *
     * @return
     */
    public static boolean fileIsExists(String filePath) {
        try {
            if (!isExternalStorageReadable()) {
                return false;
            }
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        boolean isok = true;
        try {
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath.substring(0, newPath.lastIndexOf("/")));
            if (!newfile.exists()) {
                newfile.mkdirs();
            }
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
//				int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
                fs.close();
                inStream.close();
            } else {
                isok = false;
            }
        } catch (Exception e) {
            // System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            isok = false;
        }
        return isok;
    }


    /**
     * 创建文件
     *
     * @param pathName
     */
    public static void createPath(String pathName) {
        String path = pathName.substring(0, pathName.lastIndexOf("/"));
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 文件重命名
     *
     * @param oldPath
     * @param newPath
     */
    public static void changeFilePath(String oldPath, String newPath) {
        FileUtils.copyFile(oldPath, newPath);
        File oldfile = new File(oldPath);
        if (oldfile.exists()) {
            boolean s = oldfile.delete();
        }

    }

    /**
     * 删除文件
     *
     * @param inputPath
     */
    public static void deleteFile(String inputPath/* , String inputFile */) {
        deleteFile(new File(inputPath));
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file == null) return;
        if (file.isFile()) {
            file.delete();
        }
    }

    /**
     * 删除目录下所有文件
     *
     * @param file
     * @return
     */
    public static void delteDirectory(File file) {
        if (file == null) return;
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deleteFile(childFiles[i]);
            }
            file.delete();
        }
    }

    /**
     * 删除指定文件或目录
     *
     * @param root
     */
    public static void clearDirectory(File root) {
        if (!root.exists()) {
            return;
        }
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    f.delete();
                } else {
                    clearDirectory(f);
                }
            }
            root.delete();
        }
    }


    /**
     * 字符串保存文件
     *
     * @param content
     * @return
     */
    public static boolean saveAsFile(String path, String content) {
        createPath(path);
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(path);
            fwriter.write(content);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fwriter != null) {
                    fwriter.flush();
                    fwriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 获取文本文件
     *
     * @param filePath
     */
    public static String readTxtFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuilder sb = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
                read.close();
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取assets目录下的文件，输出字符串
     *
     * @param filePath
     * @param context
     * @return
     * @throws IOException
     */
    public static String readAssetsFile(String filePath, Context context) throws IOException {
        InputStream is = context.getAssets().open(filePath);
        return readInputStream(is);
    }

    private static String readInputStream(InputStream is) throws IOException {
        InputStreamReader read = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        StringBuilder sb = new StringBuilder();
        while ((lineTxt = bufferedReader.readLine()) != null) {
            sb.append(lineTxt);
        }
        is.close();
        read.close();
        return sb.toString();
    }
}
