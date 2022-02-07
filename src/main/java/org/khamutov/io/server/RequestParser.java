package org.khamutov.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestParser {

    Request request;

    public RequestParser() {
        request = new Request();
    }

    public Request parseRequest(BufferedReader reader) throws IOException {
        injectUriAndMethod(reader);
        injectHeaders(reader);
        return request;
    }

    public void injectUriAndMethod(BufferedReader reader) throws IOException {
        String methodBody = reader.readLine();
        String method = methodBody.split(" ")[0];
        HttpMethod httpMethod = HttpMethod.getHttpMethod(method);
        String uri = methodBody.split(" ")[1];
        request.setUri(uri);
        request.setHttpMethod(httpMethod);
    }

    public void injectHeaders(BufferedReader reader) throws IOException {
        String buffer;
        while ((buffer = reader.readLine()) != null) {
            String[] headerWithContent = buffer.split(":");
            if (headerWithContent.length > 1) {
                String headerNames = headerWithContent[0];
                String headerValues = headerWithContent[1];
                String[] headersContent = headerValues.split(",");
                List<String> fieldsValue = new ArrayList<>(Arrays.asList(headersContent));
                request.getHeaders().put(headerNames, fieldsValue);
            }
        }
    }

    public static String parseGet(String header) {
        String searchWord;
        searchWord = header.replaceAll("GET", "")
                .replaceAll("HTTP/1.1", "")
                .replaceAll("/", "");
        return searchWord;
    }

}
