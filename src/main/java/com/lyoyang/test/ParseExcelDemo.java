package com.lyoyang.test;

import com.lyoyang.utils.ReadExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.util.Map;
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
    public void testParseExcel() {
        File file = new File("D://你好现在4月结算数据明细.xlsx");
        Workbook workbook = ReadExcelUtil.getWorkbook(file);
        Sheet sheetAt1 = workbook.getSheetAt(0);
        Sheet sheetAt2 = workbook.getSheetAt(1);

        Map<Integer, Map<Integer, Object>> integerMapMap = ReadExcelUtil.readExcelConetentBySheet(sheetAt2);

    }


    @Test
    public void tetsThread() {
        Future<String> test = parseExecutor.submit(new ParseExcelRunner("test"));
        if(test.isDone()) {
            System.out.println("ok");
        } else {
            System.out.println("no");
        }
        System.out.println("hello");
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
