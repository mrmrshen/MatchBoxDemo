package com.blastone.matchboxdemo.utils;

import com.blastone.matchboxdemo.bean.PicDirBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/29.
 */

public class PictureUtil {

    private static List<PicDirBean> dirList;
    private static int index;
    private static PicDirBean allPic;

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        PictureUtil.index = index;
    }

    public static void clear() {
        dirList = null;
        index = 0;
    }

    public static List<File> getFileList(String dir) {
        for (PicDirBean picDirBean : dirList) {
            picDirBean.getDirName().equals(dir);
            return picDirBean.getPicList();
        }
        return null;
    }

    public static List<PicDirBean> getDirList() {
        return dirList;
    }

    public static void initDirList(String dir) {
        initDirList(new File(dir));
    }

    public static void initDirList(File dir) {
        dirList = new ArrayList<>();
        allPic = new PicDirBean("所有图片");
        dirList.add(allPic);
        index = 0;
        searchPic(dir);
    }

    private static void searchPic(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        PicDirBean dirBean = new PicDirBean(dir.getName());
        for (File file : files) {
            if (file.isDirectory() && !dir.getName().equals("tencent")) {
                searchPic(file);
            } else {
                String name = file.getName();
                if (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".gif")) {
                    dirBean.getPicList().add(file);
                    allPic.getPicList().add(file);
                }
            }
        }
        if (dirBean.getPicList().size() > 0) {
            dirList.add(dirBean);
        }
    }
}
