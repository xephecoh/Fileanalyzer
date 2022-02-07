package org.khamutov.io.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContentReader {
    private String webAppPath;

    public ContentReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    String readContent(String uri) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                (new FileReader(webAppPath + uri.trim())))) {
            StringBuilder content = new StringBuilder();
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                content.append(buffer).append("\r\n");
            }
            return content.toString();
        }
    }
}
