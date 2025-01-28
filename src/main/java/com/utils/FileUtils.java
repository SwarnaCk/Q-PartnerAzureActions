package com.utils;

import java.io.File;

public class FileUtils {

    public static void clearDownloadDirectory(String downloadDirPath) {
        File downloadDir = new File(downloadDirPath);
        if (downloadDir.exists() && downloadDir.isDirectory()) {
            for (File file : downloadDir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".pdf")) {
                    file.delete();
                }
            }
        }
    }
}
