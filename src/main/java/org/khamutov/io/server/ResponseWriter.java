package org.khamutov.io.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ResponseWriter {
    public static final String HTTP_1_1 = "HTTP/1.1 ";

    public void writeResponse(BufferedWriter writer, Response response) throws IOException {
        writer.write(HTTP_1_1 + response.getStatus().toString() +" "+ response.status.getCode());
        System.out.println(HTTP_1_1 + response.getStatus().toString() +" "+ response.status.getCode());
        writer.write(response.getContent());
        System.out.println(response.getContent());
        for (Map.Entry<String, List<String>> stringListEntry : response.getHeaders().entrySet()) {
            writer.write(stringListEntry.getKey());
            stringListEntry.getValue().forEach(e -> {
                try {
                    writer.write(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Unable to write element" + e);
                }
            });
        }
    }

    public void writeResponse(BufferedWriter bufferedWriter, HttpStatus status) throws IOException {
        bufferedWriter.write(HTTP_1_1 + status.toString());
        System.out.println(status);
        bufferedWriter.write("\r\n");
    }
}
