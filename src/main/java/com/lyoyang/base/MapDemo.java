package com.lyoyang.base;

import java.util.HashMap;

public class MapDemo {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "bob");
        String put = map.put("name", "jim");
        System.out.println(put);
        System.out.println(map.size());
    }


}
