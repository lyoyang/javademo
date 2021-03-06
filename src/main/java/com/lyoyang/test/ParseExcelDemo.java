package com.lyoyang.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSONObject;
import com.lyoyang.entity.FeeConfig;
import com.lyoyang.utils.ReadExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
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


    @Test
    public void parseMerchant() {
        try {
            File file = new File("E://测试文档/10.22.xlsx");
            Workbook workbook = ReadExcelUtil.getWorkbook(file);
            Sheet sheetAt1 = workbook.getSheetAt(0);
            Map<Integer, Map<Integer, Object>> integerMapMap = ReadExcelUtil.readExcelConetentBySheet(sheetAt1);
            Set<Map.Entry<Integer, Map<Integer, Object>>> entries = integerMapMap.entrySet();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO merchant_info (MCH_ID, MCH_NAME, SUB_MCH_ID, ULTIMATE_MCH_ID, ULTIMATE_MCH_NAME, BELONG_AREA, ULTIMATE_TRADE, BUSINESS_MANAGER) VALUES");
            for (Map.Entry<Integer, Map<Integer, Object>> entry : entries) {
                Map<Integer, Object> value = entry.getValue();
                String mchId = "''";
                String finalMchId = "''";
                String subMchId = "''";
                String mchName = "''";
                String finalMchName = "''";
                String belongArea = "''";
                String finalTrade = "''";
                String businessManager = "''";
                finalMchId = "'" + value.get(2).toString() + "'";
                mchName = "'" + value.get(3).toString() + "'";
                finalMchName = "'" + value.get(4).toString() + "'";
                finalTrade = "'" + value.get(6).toString() + "'";
                businessManager = "'" + value.get(7).toString() + "'";
                if (StringUtils.isNotEmpty(value.get(0).toString())) {
                    mchId = "'" + value.get(0).toString() + "'";
                }
                if (StringUtils.isNotEmpty(value.get(1).toString())) {
                    subMchId = "'" + value.get(1).toString() + "'";
                }
                if (StringUtils.isNotEmpty(value.get(5).toString())) {
                    belongArea = "'" + value.get(5).toString() + "'";
                }
                String sql = "({0},{1},{2},{3},{4},{5},{6},{7}),";
                String format = MessageFormat.format(sql, mchId, mchName, subMchId, finalMchId, finalMchName, belongArea, finalTrade, businessManager);
                stringBuilder.append(format)
                        .append("\n");
            }
            File outFile = new File("E:\\测试文档\\merchant.sql");
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testParseExcelConfig() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("H5", "02");
        map.put("APP小程序", "11");
        map.put("原生APP", "03");
        map.put("小程序", "01");
        File file = new File("C:\\Users\\ipaynow0829\\Desktop\\商户子场景\\子场景反馈及费率确认.xls");
            Workbook workbook = ReadExcelUtil.getWorkbook(file);
            Sheet sheetAt1 = workbook.getSheetAt(0);
            Map<Integer, Map<Integer, Object>> integerMapMap = ReadExcelUtil.readExcelConetentBySheet(sheetAt1);
            Set<Map.Entry<Integer, Map<Integer, Object>>> entries = integerMapMap.entrySet();
            List<FeeConfig> feeConfigs = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            builder.append("INSERT INTO fee_config (CHANNEL_ID, DEVICE_ID, MCH_ID, TRANS_TYPE, FEE_MODE, RATIO_VALUE, RATE_MAX_VALUE, MER_REFUND_FEE, MONTH_TYPE, IS_DAFAULT, BEGIN_DATE, remark, SUB_DEVICE_ID) VALUES");
        for (Map.Entry<Integer, Map<Integer, Object>> entry : entries) {
                Map<Integer, Object> value = entry.getValue();
                String mchId = value.get(0).toString();
                String channelId = "13";
                String deviceId = "01";
                String transType = "05";
                String feeMode = "1";
                BigDecimal rateMaxValue = new BigDecimal("1000000");
                String merRefundFee = "0";
                String monthType = "0";
                String isDefault = "0";
                String beginDate = "2020-01-10 00:00:00";
                String remark = "sys-subDeviceId";
                BigDecimal ratioValue = new BigDecimal(value.get(7).toString());
                String subDeviceIds = value.get(6).toString();
                if (mchId.equals("000000001572389")) {
                    rateMaxValue = new BigDecimal("5000000");
                }
                List<String> subDeviceIdList = Arrays.asList(subDeviceIds.trim().split("、"));
                for (String subDeviceId : subDeviceIdList) {
                    String specialMchId = map.get(subDeviceId);
                    builder.append("(").append("'").append(channelId).append("'").append(",").append("'").append(deviceId).append("'").append(",")
                            .append("'").append(mchId).append("'").append(",").append("'").append(transType).append("'").append(",").append("'").append(feeMode).append("'").append(",")
                            .append(ratioValue).append(",").append(rateMaxValue).append(",").append("'").append(merRefundFee).append("'").append(",")
                            .append("'").append(monthType).append("'").append(",").append("'").append(isDefault).append("'").append(",").append("'").append(beginDate).append("'").append(",")
                            .append("'").append(remark).append("'").append(",").append("'").append(specialMchId).append("'").append("),").append("\n");
                }
            }
        File file1 = new File("E://opt/feeConfigSub.sql");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(builder.toString().getBytes("GBK"));
        fileOutputStream.flush();
    }
}
