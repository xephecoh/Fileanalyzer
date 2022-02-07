package org.khamutov.io.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RequestHandler {
    BufferedReader reader;
    BufferedWriter writer;
    ContentReader contentReader;
    RequestParser parser;


    public RequestHandler(BufferedReader reader, BufferedWriter writer, ContentReader contentReader) {
        this.reader = reader;
        this.writer = writer;
        this.contentReader = contentReader;
        parser = new RequestParser();
    }
    public void handle() throws IOException {
        Request request = parser.parseRequest(reader);
        try {
            String content = contentReader.readContent(request.getUri());
            Response response = new Response(content, HttpStatus.OK, request.headers);
            ResponseWriter responseWriter = new ResponseWriter();
            responseWriter.writeResponse(writer, response);
        } catch (NullPointerException e) {
            ResponseWriter responseWriter = new ResponseWriter();
            responseWriter.writeResponse(writer, HttpStatus.NOT_FOUND);
        } catch (FileNotFoundException e){
            ResponseWriter responseWriter = new ResponseWriter();
            responseWriter.writeResponse(writer, HttpStatus.BAD_REQUEST);
        }
    }
}
