package org.khamutov.io.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private static String webAppPath = "src/main/resources/Webapp/";
    static int port = 9090;

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Started");
            String file = "";
            try (Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(
                         new OutputStreamWriter(socket.getOutputStream()))) {
                String line;
                while (!(line = bufferedReader.readLine()).isEmpty()) {
                    System.out.println(line);
                    if (line.startsWith("GET")) {
                        file = line;
                    }
                }
                file = file.replaceAll("GET", "")
                        .replaceAll("HTTP/1.1", "")
                        .replaceAll("/", "");
                try (ObjectInputStream outputStream = new ObjectInputStream(
                        new ObjectInputStream(new FileInputStream(webAppPath + file.trim())))) {

                    String index = outputStream.readUTF();

                    System.out.println("Writing response");
                    bufferedWriter.write("HTTP/1.1 200 OK");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    bufferedWriter.write(index);
                }
            }
            System.out.println("Finish");
        }


    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Started");
            String file = "";
            try (Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(
                         new OutputStreamWriter(socket.getOutputStream()))) {
                String header;
                while (!(header = bufferedReader.readLine()).isEmpty()) {
                    System.out.println(header);
                    if (header.startsWith("GET")) {
                        file = header;
                    }
                }
                file = Parser.parseGet(file);
                System.out.println(webAppPath + file.trim());
                try (BufferedReader outputStream = new BufferedReader(
                        (new FileReader(webAppPath + file.trim())))) {

                    StringBuilder htmlPage = new StringBuilder();
                    String buffer;
                    while ((buffer = outputStream.readLine()) != null) {
                        htmlPage.append(buffer).append("\r\n");
                    }
                    System.out.println(htmlPage);
                    System.out.println("Writing response");
                    bufferedWriter.write("HTTP/1.1 200 OK");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    bufferedWriter.write(htmlPage.toString());
                }
            }
            System.out.println("Finish");
        }
    }
}
