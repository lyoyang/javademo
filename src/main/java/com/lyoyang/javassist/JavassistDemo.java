package com.lyoyang.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

public class JavassistDemo {

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("HelloWorld");
        CtMethod ctMethod = CtMethod.make("public static void test() {\n" +
                "        System.out.println(\"Hello World!\");\n" +
                "    }", ctClass);
        ctClass.addMethod(ctMethod);
        Class aClass = ctClass.toClass();
        Object object = aClass.newInstance();
        Method test = aClass.getDeclaredMethod("test", null);
        test.invoke(object, null);
    }



}
