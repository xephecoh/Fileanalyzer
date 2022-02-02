package org.khamutov;

import java.io.File;

public class FileManager {

    public static int countFiles(String path) {
        int totalFiles = 0;

        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("The given path does not exist.");
        }
        if (file.isFile()) {
            throw new IllegalArgumentException("The given path is a file. A directory is expected.");
        }
        File[] subFiles = file.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    totalFiles++;
                } else if (subFile.isDirectory()) {
                    long filesFromRecursion = countFiles(subFile.getAbsolutePath());
                    totalFiles += filesFromRecursion;
                }
            }
        }
        return totalFiles;
    }

    public static int countDirs(String path) {
        int totalDirs = 0;
        File dir = new File(path);
        if (!dir.exists()) {
            throw new IllegalArgumentException("The given path does not exist.");
        }
        if (dir.isFile()) {
            throw new IllegalArgumentException("The given path is a file. A directory is expected.");
        }
        File[] subFiles = dir.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (File subFile : subFiles) {
                if (subFile.isDirectory()) {
                    totalDirs++;
                    long info = countDirs(subFile.getAbsolutePath());
                    totalDirs += info;
                }
            }
        }
        return totalDirs;
    }

    /*
    public static void copy(String from, String to) {
            try (FileInputStream fis = new FileInputStream(from)){
                StringBuilder stringBuilder = new StringBuilder();
                byte[] buffer = new byte[10];
                int iterationCounter;
                while ((iterationCounter = fis.read(buffer)) != -1) {
                    stringBuilder.append(new String(buffer, 0, iterationCounter));
                }

            }

    }

     */

    public static void move(String from, String to) {
    }

    public static void main(String[] args) {
        int filesNumber = countFiles("src/main/resources");
        System.out.println(filesNumber);
        int folderNumber = countDirs("src/main/resources");
        System.out.println(folderNumber);
    }


}
