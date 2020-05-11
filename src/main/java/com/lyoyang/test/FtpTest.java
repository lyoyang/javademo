package com.lyoyang.test;

import com.lyoyang.utils.FTPUtil;

import java.text.MessageFormat;

/**
 * @author: yangbing
 * @Date: 2019/8/30 10:59
 * @Description:
 */
public class FtpTest {

    public static void main(String[] args) {
        String accDate = "2019-08-28";
        String offlineHost = "211.154.166.174";
        Integer offlinePort = 21;
        String offlineUser = "dwfj";
        String offlinePasswd = "dwfj132";
        String offlinePath = "pos_wfj/" + accDate;
        String path = "/opt/cleanSettle/wfj/offline/" + accDate;
        String offlinePrefix = "pos_";
        String fileName = MessageFormat.format("{0}{1}{2}", offlinePrefix, accDate, ".txt");
        boolean downFile = FTPUtil.downloadFile(offlineHost, offlinePort, offlineUser, offlinePasswd, offlinePath + accDate, fileName, "D://");
    }

}
