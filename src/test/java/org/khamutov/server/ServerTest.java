package org.khamutov.server;

import org.junit.jupiter.api.Test;
import org.khamutov.io.server.WebServer;
import java.io.IOException;

public class ServerTest {

    @Test
    public void test() throws IOException {
        WebServer server = new WebServer();

        server.start();
    }
}