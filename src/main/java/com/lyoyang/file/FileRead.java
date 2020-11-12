package com.lyoyang.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileRead {


    public static void main(String[] args) throws IOException {
        calculateUserPv(new File("/tmp/a.txt"));
    }



    private static void calculateUserPv(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("file can not be null.");
        }
        Map<Long, Long> userVisitTimesMap = new HashMap<>();
        FileInputStream fileInputStream = null;
        Scanner scanner = null;
        try {
            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String[] contentArray = scanner.nextLine().split(" ");
                Long userId = Long.valueOf(contentArray[2]);
                if (userVisitTimesMap.containsKey(userId)) {
                    Long visitTimes = userVisitTimesMap.get(userId);
                    userVisitTimesMap.put(userId, ++visitTimes);
                } else {
                    userVisitTimesMap.put(userId, 1L);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println(userVisitTimesMap);
    }







}
