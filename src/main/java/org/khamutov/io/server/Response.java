package org.khamutov.io.server;

import java.util.HashMap;
import java.util.List;

public class Response {
    String content;
    HttpStatus status;
    HashMap<String, List<String>> headers;

    public Response(String content, HttpStatus status, HashMap<String, List<String>> headers) {
        this.content = content;
        this.status = status;
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public HashMap<String, List<String>> getHeaders() {
        return headers;
    }
}
