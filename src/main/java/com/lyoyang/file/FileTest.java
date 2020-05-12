package com.lyoyang.file;

import org.apache.commons.lang3.ThreadUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author: yangbing
 * @Date: 2020/2/13 16:59
 * @Description:
 */
public class FileTest {



    @Test
    public void testWriteAndReadData() throws IOException {
        String content = "hello, this is a file test";
        Files.write(Paths.get("D://test.txt"),
                content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        List<String> strings = Files.readAllLines(Paths.get("D://test.txt"));
        System.out.println(strings.toString());
    }



}
