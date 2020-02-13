package com.lyoyang.annotation;

import java.lang.reflect.Field;

/**
 * 注解处理器
 */
public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider annotation = field.getAnnotation(FruitProvider.class);
                String info = "id:" + annotation.id() + " name:" + annotation.name() + " address:" + annotation.address();
                System.out.println(info);
            }
        }
    }
}
