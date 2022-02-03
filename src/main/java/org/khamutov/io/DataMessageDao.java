package org.khamutov.io;

import java.io.*;
import java.util.Arrays;
import java.util.Date;


public class DataMessageDao implements MessageDao {
    @Override
    public void save(Message message) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(
                new FileOutputStream("E:\\Coding\\FileAnalyzer\\src\\main\\resources\\level_1_1\\test4.txt"));) {
            dataOutputStream.writeLong(message.getDate().getTime());
            dataOutputStream.writeInt(message.getAmount());
            dataOutputStream.writeInt(message.getMessage().length());
            dataOutputStream.write(message.getMessage().getBytes());
        }
    }

    @Override
    public Message load() throws IOException {
        try (DataInputStream inputStream = new DataInputStream(
                new FileInputStream("E:\\Coding\\FileAnalyzer\\src\\main\\resources\\level_1_1\\test4.txt"))) {
            Date date = new Date(inputStream.readLong());
            int amount = inputStream.readInt();
            int length = inputStream.readInt();
            String messageText = new String(inputStream.readNBytes(length));
            return new Message(date, messageText, amount);
        }
    }

    public static void main(String[] args) throws IOException {
        DataMessageDao dataMessageDao = new DataMessageDao();
        dataMessageDao.save(new Message(new Date(), "HelloWorld", 112));
        Message message = dataMessageDao.load();
        System.out.println(message);
    }


    //String messageText = new String(inputStream.readNBytes(length));
}

