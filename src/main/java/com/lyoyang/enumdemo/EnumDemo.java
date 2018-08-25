package com.lyoyang.enumdemo;

public enum EnumDemo {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    private String name;
    private int index;
    private EnumDemo(String name, int index){
        this.name = name;
        this.index = index;
    }
    public static  String getName(int index){
        for (EnumDemo e : EnumDemo.values()){
            if(e.getIndex() == index){
                return e.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
