package org.khamutov.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int SERVER_PORT = 8081;
    private static boolean flag = true;

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT);) {
            while (flag) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                    String data = reader.readLine();
                    System.out.println(data);
                    if (data.equals("STOP THE SERVER")) {
                        flag = false;
                        System.out.println("Server stopped");
                    }
                    String response = data + " echo";
                    writer.write(response);
                    writer.flush();
                }
            }
        }

    }
}
