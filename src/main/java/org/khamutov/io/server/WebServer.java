package org.khamutov.io.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private String webAppPath = "src/main/resources/Webapp/";
    private int port = 9090;
    private ContentReader reader;

    public WebServer() {
        this.reader = new ContentReader(webAppPath);
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Started");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader bufferedReader = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()));
                     BufferedWriter bufferedWriter = new BufferedWriter(
                             new OutputStreamWriter(socket.getOutputStream()))) {
                    RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, reader);
                    requestHandler.handle();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        WebServer webServer = new WebServer();
        webServer.start();
    }
}
