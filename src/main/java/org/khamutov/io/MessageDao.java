package org.khamutov.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MessageDao {
    void save(Message message) throws IOException;

    Message load() throws FileNotFoundException, IOException, ClassNotFoundException;
}