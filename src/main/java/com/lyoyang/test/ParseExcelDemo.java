package com.lyoyang.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSONObject;
import com.lyoyang.utils.ReadExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
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
            File file = new File("D://fee.xlsx");
            Workbook workbook = ReadExcelUtil.getWorkbook(file);
            Sheet sheetAt1 = workbook.getSheetAt(0);
            Map<Integer, Map<Integer, Object>> integerMapMap = ReadExcelUtil.readExcelConetentBySheet(sheetAt1);
            Set<Map.Entry<Integer, Map<Integer, Object>>> entries = integerMapMap.entrySet();
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> set = new TreeSet<>();
            for (Map.Entry<Integer, Map<Integer, Object>> entry : entries) {
                Map<Integer, Object> value = entry.getValue();
                if (StringUtils.isNotEmpty(value.get(5).toString())) {
                    String sql = "update fee_config set PARTNER_INCOME_RATIO = {0} where mch_id=''{1}'' and channel_id = ''{2}'';";
                    String mchId = value.get(0).toString().replace("\t", "");
                    String fee = value.get(4).toString();
                    String channelName = value.get(2).toString().replace("\t", "");
                    String channelId = "";
                    if ("支付宝".equals(channelName)) {
                        channelId = "12";
                    } else {
                        channelId = "13";
                    }
                    set.add(fee);
                    String format = MessageFormat.format(sql, new BigDecimal(fee).divide(new BigDecimal("100")).toPlainString(), mchId, channelId);
                    stringBuilder.append(format)
                    .append("\n");
                }
            }
            String s = JSONObject.toJSONString(set);
            File file1 = new File("D://fee.sql");
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
