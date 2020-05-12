package com.lyoyang.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: yangbing
 * @Date: 2019/9/6 16:15
 * @Description:
 */
public class EasyExcelTest {


    @Test
    public void testParseExcel() throws FileNotFoundException {

        InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D://wechat_trans.xls"));
        Sheet sheet = new Sheet(1,7);
        List<Object> data = EasyExcelFactory.read(bufferedInputStream, sheet);

    }


}
