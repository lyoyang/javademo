package com.lyoyang.netty;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MessagePackTest {


    public static void main(String[] args) throws IOException {
        List<String> list = Arrays.asList("groovy", "java", "scala", "php");
        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(list);

        List<String> convert = messagePack.read(write, Templates.tList(Templates.TString));
        System.out.println(convert);
    }

}
