package org.khamutov.io.server;

import java.util.HashMap;
import java.util.List;

public class Request {
    String uri;
    HttpMethod httpMethod;
    HashMap<String, List<String>> headers;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setHeaders(HashMap<String, List<String>> headers) {
        this.headers = headers;
    }

    public HashMap<String, List<String>> getHeaders() {
        return headers;
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Request() {
        this.headers = new HashMap<>();
    }
}
