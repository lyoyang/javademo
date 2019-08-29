package com.lyoyang.test;

import com.lyoyang.utils.ReadExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: yangbing
 * @Date: 2019/5/30 11:44
 * @Description:
 */
public class ParseExcelDemo {

    private ExecutorService parseExecutor = Executors.newFixedThreadPool(5);


    @Test
    public void testParseExcel() throws IOException {
        try {
            File file = new File("D://temp.xlsx");
            Workbook workbook = ReadExcelUtil.getWorkbook(file);
            Sheet sheetAt1 = workbook.getSheetAt(0);
            Map<Integer, Map<Integer, Object>> integerMapMap = ReadExcelUtil.readExcelConetentBySheet(sheetAt1);
            Set<Map.Entry<Integer, Map<Integer, Object>>> entries = integerMapMap.entrySet();
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Integer, Map<Integer, Object>> entry : entries) {
                Map<Integer, Object> value = entry.getValue();
                String mchId = value.get(0).toString();
                String mchName = value.get(1).toString();
                String subMchId = value.get(2).toString();
                String ultimeMchId = value.get(3).toString();
                String ultimeMchName = value.get(4).toString();
                String belongArea = value.get(5).toString();
                String ultimeTrade = value.get(6).toString();
                String businessManager = value.get(7).toString();
                stringBuilder.append("(").append("'").append(mchId).append("',")
                        .append("'").append(mchName).append("',")
                        .append("'").append(subMchId).append("',")
                        .append("'").append(ultimeMchId).append("',")
                        .append("'").append(ultimeMchName).append("',")
                        .append("'").append(belongArea).append("',")
                        .append("'").append(ultimeTrade).append("',")
                        .append("'").append(businessManager).append("'")
                        .append("),").append("\n");
            }
            File file1 = new File("D://day.sql");
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void tetsThread() {
//        Future<String> test = parseExecutor.submit(new ParseExcelRunner("test"));
//        if(test.isDone()) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
        System.out.println("hello");
        System.out.println(StringUtils.isNotEmpty(""));
    }


    class ParseExcelRunner implements Callable<String> {

        private String name;

        public ParseExcelRunner(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            return name;
        }
    }

}
