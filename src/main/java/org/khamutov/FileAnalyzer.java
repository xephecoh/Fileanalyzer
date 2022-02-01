package org.khamutov;

import org.junit.platform.commons.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileAnalyzer {
    String path;
    String word;

    public FileAnalyzer(String path, String word) {
        this.path = path;
        this.word = word;
    }

    private String getData() throws IOException {
        try (FileInputStream fis = new FileInputStream(path)){
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[10];
            int iterationCounter;
            while ((iterationCounter = fis.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, iterationCounter));
            }
            return stringBuilder.toString();
        }
    }

    private String resolveThePunctuation(String element){
        if(element.endsWith(";")){
            element = element.substring(0,element.length()-1);
        }
        if(element.endsWith(",")){
            element = element.substring(0,element.length()-1);
        }
        return element;
    }

    public int getUsageNumberAndPrintSentences() throws IOException {
        int tempWordCounter;
        int wordCounter = 0;
        String data = getData();
        String[] sentences = data.trim().split("(\\.)");
        for (String sentence : sentences) {
            tempWordCounter = 0;
            String[] wordsAndPunctuationMarks = sentence.split("\\s+");
            for (String element : wordsAndPunctuationMarks) {
                element = resolveThePunctuation(element);
                if (element.matches(word) & tempWordCounter > 0) {
                    tempWordCounter++;
                }
                if (element.matches(word) & tempWordCounter == 0) {
                    System.out.println(sentence);
                    tempWordCounter++;
                }
            }
            wordCounter = wordCounter + tempWordCounter;
        }
        return wordCounter;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        String word = scanner.next();
        int result = new FileAnalyzer(path, word).getUsageNumberAndPrintSentences();
        System.out.println("The " + word + " has been mentioned " + result +" times");
    }
    //src/main/resources/lisbon.txt
}
