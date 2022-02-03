package org.khamutov.io.bufferedstream;


import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {
    private final int INIT_SIZE = 10;
    private OutputStream target;
    private byte[] buffer;
    private int offset;

    public BufferedOutputStream(OutputStream target) {
        this.target = target;
        buffer = new byte[INIT_SIZE];
    }

    @Override
    public void write(int b) throws IOException {


        buffer[offset++]=(byte)b;

    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}