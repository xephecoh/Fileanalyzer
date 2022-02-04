package org.khamutov.io.testserver;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    final static private String URL = "localhost";
    final static private int PORT = 8081;

    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket(URL, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            writer.write(message + "\r\n");
            writer.flush();
            String serverResponse = reader.readLine();
            System.out.println(serverResponse);
            scanner.close();
        }
    }
}
