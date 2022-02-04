package org.khamutov.io.server;

public class Parser {

    public static String parseGet(String header) {

        String searchWord;
        searchWord = header.replaceAll("GET", "")
                .replaceAll("HTTP/1.1", "")
                .replaceAll("/", "");
        if (searchWord.endsWith(".css")) {
            searchWord = "/css" + searchWord;
        }
        return searchWord;

    }


}
