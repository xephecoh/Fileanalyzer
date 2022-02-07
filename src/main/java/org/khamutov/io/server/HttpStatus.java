package org.khamutov.io.server;

public enum HttpStatus {
    OK(200, "Ok"), BAD_REQUEST(400, "Bad request"),
    NOT_FOUND(401, "Not found");

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    HttpStatus getStatusByCode(int code){
        for(HttpStatus el:values()){
            if(el.getCode()==code){
                return el;
            }
        }
        throw new NoSuchHttpStatusException("There is no status with code " + code);
    }
}
