package org.khamutov;


import java.io.File;

public class folderInfo {
    public static long calculateDirectoryInfo(String dirPath) {

        int totalFiles = 0;
      //  int totalDirs = 0;
       // long totalSize = 0;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            throw new IllegalArgumentException("The given path does not exist.");
        }
        if (dir.isFile()) {
            throw new IllegalArgumentException("The given path is a file. A directory is expected.");
        }
        File[] subFiles = dir.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    totalFiles++;
                  //  totalSize += subFile.length();
                } else if(subFile.isDirectory()) {
                 //   totalDirs++;
                    long info = calculateDirectoryInfo(subFile.getAbsolutePath());
                 //   totalDirs += info[0];
                    totalFiles += info;
                 //   totalSize += info[2];
                }
            }

           // result[0] = totalDirs;
          //  result[1] = totalFiles;
          //  result[2] = totalSize;
        }

        return totalFiles;
    }

    public static void main(String[] args) {
        long totalFiles = calculateDirectoryInfo("E:\\Coding\\FileAnalyzer\\src\\main\\resources");
        System.out.println(totalFiles);
    }

}