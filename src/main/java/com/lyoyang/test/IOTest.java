package com.lyoyang.test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: yangbing
 * @Date: 2019/9/10 16:48
 * @Description:
 */
public class IOTest {



    @Test
    public void testReadFile() throws IOException {
        List<String> parse = Files.readLines(new File("D://2019-09-10/umf.txt"), Charset.forName("gb2312"));
        for (int i = 1; i < parse.size(); i++) {
            String s = parse.get(i);
            String[] split = s.split("\\|");
            int a = 4;
        }
    }


}
