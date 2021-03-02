package com.lyoyang.utils;

import java.math.BigDecimal;


public class BigDecimalUtils {

    /*
    * BigDecimal 做除法并做精度处理，转换成字符串，
    * @param nums 除数
    * @param  deviceors 被除数
    * @param scale 保留小数精度
    * **/
    public static String deviceToString(BigDecimal nums, Long deviceors, int scale) {
        if (nums == null || nums.equals(BigDecimal.ZERO)) {
            return "0";
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(deviceors));
        return result.setScale(scale).toPlainString();

    }


    /*
    * BigDecimal 分转换为元字符串
    * @param nums 除数
    * @param  deviceors 被除数 100
    * @param scale 保留小数精度  2
    * **/
    public static String fenToYuanStr(BigDecimal nums) {
        if (nums == null) {
            return "0";
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(100L));
        if (result.equals(BigDecimal.ZERO)) {
            return "0";
        }
        return result.setScale(2).toPlainString();

    }

    /*
     * BigDecimal 厘转换为元字符串
     * @param nums 除数
     * @param  deviceors 被除数 100
     * @param scale 保留小数精度  2
     * **/
    public static String liToYuanStr(BigDecimal nums) {
        if (nums == null) {
            return "0";
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(1000L));
        if (result.equals(BigDecimal.ZERO)) {
            return "0";
        }
        return result.setScale(4).toPlainString();
    }


    /*
    * BigDecimal 做除法并做精度处理，转换负数再转换成字符串
    * @param nums 除数
    * @param  deviceors 被除数 1
    * @param scale 保留小数精度  2
    * **/
    public static String deviceAndToMinusToStringDefault(BigDecimal nums) {
        if (nums == null) {
            return "0";
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(100L));
        if (result.equals(BigDecimal.ZERO)) {
            return "0";
        }
        return result.multiply(new BigDecimal(-1)).setScale(2).toPlainString();
    }


    /*
    * BigDecimal 分转换成元 BigDecimal
    * @param nums 除数
    * 被除数 100 、 保留小数精度  2
    **/
    public static BigDecimal fenToYuanBigDecimal(BigDecimal nums) {
        if (nums == null || nums.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(100));
        return result.setScale(2);
    }

    /*
     * BigDecimal 分转换成厘 BigDecimal
     * @param nums 除数
     * 被除数 1000 、 保留小数精度  3
     **/
    public static BigDecimal liToYuanBigDecimal(BigDecimal nums) {
        if (nums == null || nums.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(1000));
        return result.setScale(4);
    }


    /*
  * BigDecimal 分转换成元 BigDecimal
  * @param nums 除数
  * 被除数 100 、 保留小数精度  2
  **/
    public static BigDecimal fenToYuanBigDecimal(BigDecimal nums,int roundMode) {
        if (nums == null || nums.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(100));
        return result.setScale(2,roundMode);
    }


    /*
    * BigDecimal 做除法并做精度处理
    * @param nums 除数
    * @param  deviceors 被除数
    * @param scale 保留小数精度
    **/
    public static BigDecimal deviceBigDecimal(BigDecimal nums, Long deviceors, int scale) {
        if (nums == null || nums.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(deviceors));
        return result.setScale(scale);
    }

    public static String deviceDefaultToString(BigDecimal nums,int roundMode) {
        if(nums == null){
            return "0";
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(100L));
        if (result.equals(BigDecimal.ZERO)) {
            return "0";
        }
        return result.setScale(2,roundMode).toPlainString();
    }

    public static BigDecimal yuanToFenBigDecimal(BigDecimal nums) {
            if (nums == null) {
                return BigDecimal.ZERO;
            }
            BigDecimal result = nums.multiply(BigDecimal.valueOf(100L));
            return result;
    }



    public static BigDecimal fenToYuanForFinancial(BigDecimal nums) {
        if (nums != null && !nums.equals(BigDecimal.ZERO)) {
            BigDecimal result = nums.divide(BigDecimal.valueOf(100L));
            return result.setScale(6);
        } else {
            return BigDecimal.ZERO;
        }
    }


    public static BigDecimal nullToZero(BigDecimal num) {
        return num == null ? BigDecimal.ZERO : num;
    }


}
