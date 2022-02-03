package org.khamutov.javaanalyzer;

import java.io.File;

public class FileManager {

    public static int countFiles(String path) {
        int totalFiles = 0;

        File file = new File(path);
        checkPath(file);
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

    private static void checkPath(File file){
        if (!file.exists()) {
            throw new IllegalArgumentException("Path does not exist.");
        }
        if (file.isFile()) {
            throw new IllegalArgumentException("Path is a file.Folder is expected.");
        }
    }

    public static int countDirs(String path) {
        int totalDirs = 0;
        File dir = new File(path);
        checkPath(dir);
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
