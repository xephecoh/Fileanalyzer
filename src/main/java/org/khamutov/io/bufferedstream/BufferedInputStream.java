package org.khamutov.io.bufferedstream;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private static final int DEFAULT_SIZE = 20;
    private InputStream target;
    private byte[] buffer;
    private  int offset;

    public BufferedInputStream(InputStream target) {
        this.target = target;
        buffer = new byte[DEFAULT_SIZE];
    }

    @Override
    public int read() throws IOException {
       /* if (buffer.isEmpty()){
            target.read(buffer);
        }
        return buffer[currentIndex];*/
        return 1;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}