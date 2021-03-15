package com.lyoyang.test;

import com.google.common.base.Stopwatch;
import com.lyoyang.entity.User;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class IOTest {



    @Test
    public void serializableTest() throws IOException {
        User user = new User();
        user.setName("alice");
        user.setCity("成都");
        Stopwatch s1 = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            ByteOutputStream os = new ByteOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(user);
            out.flush();
            out.close();
            byte[] testByte = os.toByteArray();
            os.close();
        }
        System.out.println("ObjectOutputStream序列化：" + s1.stop());

        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        Stopwatch s2 = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            byte[] userBytes = user.getName().getBytes();
            byte[] cityBytes = user.getCity().getBytes();
            byteBuffer.put(userBytes);
            byteBuffer.putInt(userBytes.length);
            byteBuffer.put(cityBytes);
            byteBuffer.putInt(cityBytes.length);
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
        }
        System.out.println("ByteBuffer序列化：" + s2.stop());

    }



}
