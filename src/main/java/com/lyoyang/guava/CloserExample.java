package com.lyoyang.guava;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CloserExample {


    @Test
    public void closerTest() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("/tmp/a.txt"));
        Closer closer = Closer.create();
        try {
            InputStream register = closer.register(byteSource.openStream());
        } catch (IOException e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
    }

}
