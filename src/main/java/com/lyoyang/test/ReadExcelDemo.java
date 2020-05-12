package com.lyoyang.test;

import com.lyoyang.utils.ReadExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.util.Map;

/**
 * @author: yangbing
 * @Date: 2019/9/18 15:28
 * @Description:
 */
public class ReadExcelDemo {



    @Test
    public void parseTest() {
        File file = new File("D://fghf.xlsx");
        Workbook workbook = ReadExcelUtil.getWorkbook(file);
        Map<Integer, Map<Integer, Object>> integerMapMap = ReadExcelUtil.readExcelConetent(workbook);
        int a = 0;
    }



}
