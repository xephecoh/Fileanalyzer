package org.khamutov.javaanalyzer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAnalyzer {
    private List<String> sentencesList = new ArrayList<>();

    public FileAnalyzer() {
    }

    public List<String> getSentencesList() {
        return sentencesList;
    }

    boolean checkIfMatches(String regexp, String word, int flag) {
        Pattern pattern = Pattern.compile(regexp, flag);
        Matcher matcher = pattern.matcher(word);
        int matches = 0;
        if (matcher.matches()) {
            matches++;
        }
        return matches > 0;
    }

    String getData(String path) throws IOException {
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[10];
            int iterationCounter;
            while ((iterationCounter = fis.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, iterationCounter));
            }
            return stringBuilder.toString().replace("\t", "");
        }
    }

    String resolveThePunctuation(String element) {
        if (element.endsWith(";")) {
            element = element.substring(0, element.length() - 1);
        }
        else if (element.endsWith(",")) {
            element = element.substring(0, element.length() - 1);
        }
        else if (element.endsWith(".")) {
            element = element.substring(0, element.length() - 1);
        }
        else if (element.endsWith("?")) {
            element = element.substring(0, element.length() - 1);
        }
        else if (element.endsWith(":")) {
            element = element.substring(0, element.length() - 1);
        }
        else if (element.endsWith("!")) {
            element = element.substring(0, element.length() - 1);
        }
        return element;
    }

    public int getSentences(String path, String word) throws IOException {
        int totalCounter = 0;
        String[] sentences = getData(path).split("(\\.)");
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (getCounter(sentence, word) > 0) {
                sentencesList.add(sentence.trim() + ".");
            }
            totalCounter = getCounter(sentence, word) + totalCounter;
        }
        return totalCounter;
    }

    int getCounter(String sentence, String word) {
        int tempCounter = 0;
        String[] words = sentence.split("\\s+");
        for (String element : words) {
            element = resolveThePunctuation(element);
            if (checkIfMatches(word, element, Pattern.CASE_INSENSITIVE) ) {
                tempCounter++;
            }
        }
        return tempCounter;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        String word = scanner.next();
        int result = new FileAnalyzer().getSentences(path, word);
        System.out.println("The " + word + " has been mentioned " + result + " times");
    }
    //src/main/resources/lisbon.txt
}
