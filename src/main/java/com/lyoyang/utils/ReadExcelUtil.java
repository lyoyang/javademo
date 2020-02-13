package com.lyoyang.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ipaynow1117
 * Date: 17-4-11
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class ReadExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReadExcelUtil.class);
    //获得Workbook
    public static Workbook getWorkbook(File file){
        //获取文件名
        String fileName = file.getAbsoluteFile().getName();
        //创建Workbook工作簿，代表整个excel
        Workbook workbook = null;
        try{
            //获得文件IO输入流
            InputStream is = new FileInputStream(file);
            //根据文件名后缀名不同（xls和xlsx）获得不同的Workbook实现类·对象
            if(fileName.endsWith("xls")){
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith("xlsx")){
                workbook = new XSSFWorkbook(is);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return workbook;
    }

    //获取表头内容
    public static String[]  readExcelTitle(Sheet sheet){
        if(sheet == null){
            return null;
        }
        String[] title = null;
        try{
            Row row = sheet.getRow(0);
            //标题的总列数
            int colNum = row.getPhysicalNumberOfCells();
            logger.info("获取excel文件的标题的总列数，总列数："+colNum);
            title = new String[colNum];
            for(int i = 0; i < colNum;i++){
                title[i] = row.getCell(i).getCellFormula();
            }
        }catch(Exception e){
            logger.info(e.getMessage());
        }

        return title;
    }

    //读取Excel的数据内容
    public static Map<Integer, Map<Integer, Object>> readExcelConetent(Workbook workbook){
        if(workbook == null){
            return null;
        }
        Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();
        try{
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            int rowNum = sheet.getLastRowNum();
            int firstRowNum = sheet.getFirstRowNum();
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            //获取总列数
            Row row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            //正文内容应该从第二行开始，第一行为文件的标头的标题
            for(int i = 1;i < rowNum;i++){
                row = sheet.getRow(i);
                int j = 0;
                Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
                while(j < colNum){
                    Object object = getCellFormatValue(row.getCell(j));
                    cellValue.put(j,object);
                    j++;
                }
                content.put(i,cellValue);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return content;
    }

    //根据Cell类型设置数据
    private static Object getCellFormatValue(Cell cell){
        Object cellObject = null;
        if(cell != null){
            switch(cell.getCellType()){
                //数字
                case Cell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellObject = com.lyoyang.utils.DateUtil.getStringFromDate(date, com.lyoyang.utils.DateUtil.FORMAT_DATE);
                    } else {
                        cellObject = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                //字符串
                case Cell.CELL_TYPE_STRING:
                    cellObject = cell.getRichStringCellValue().getString();
                    break;
                //空值
                case Cell.CELL_TYPE_BLANK:
                    cellObject = "";
                    break;
                //公式
                case Cell.CELL_TYPE_FORMULA:
                    if(DateUtil.isCellDateFormatted(cell)){
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        cellObject = simpleDateFormat.format(date);
                    }else{
                        cellObject = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                default:
                    cellObject = "";
                    break;
            }
        }else{
            cellObject = "";
        }
        return cellObject;

    }



    //读取Excel的数据内容
    public static Map<Integer,Map<Integer,Object>> readExcelConetentBySheet(Sheet sheet){
        if(sheet == null){
            return null;
        }
        Map<Integer,Map<Integer,Object>> content = new HashMap<Integer,Map<Integer,Object>>();
        try{
            //获取总行数
            int rowNum = sheet.getLastRowNum();
            //获取总列数
            Row row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells();
            //正文内容应该从第二行开始，第一行为文件的标头的标题
            for(int i = 0;i < rowNum;i++){
                row = sheet.getRow(i+1);
                int j = 0;
                Map<Integer,Object> cellValue = new HashMap<Integer,Object>();
                while(j < colNum){
                    Object object = getCellFormatValue(row.getCell(j));
                    cellValue.put(j,object);
                    j++;
                }
                content.put(i,cellValue);
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return content;
    }


    @Test
    public void test() {

    }


}
