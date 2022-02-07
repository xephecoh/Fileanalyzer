package org.khamutov.io.server;

public enum HttpMethod {
    GET,PUT,POST;

    public static HttpMethod getHttpMethod(String method) {
        for (HttpMethod el : values()) {
            if(el.toString().equals(method)){
                return el;
            }
        }
        throw new  NoSuchMethodNameException("Wrong method name" + method);
    }
}
