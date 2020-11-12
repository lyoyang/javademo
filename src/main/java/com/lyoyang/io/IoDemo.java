package com.lyoyang.io;

import com.google.common.base.Stopwatch;
import com.lyoyang.entity.User;
import io.netty.buffer.ByteBuf;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Brian
 * @Date: 2020/7/10 14:22
 * @Description:
 */
public class IoDemo {

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);


    public static void main(String[] args) throws Exception {
//        pipedStream();
//        fileInputStream();
//        byteArrayStream();
//        bufferedStream();
        objectStream();
    }


    public static void fileInputStream() {
        File file = new File("D://test/a.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file); FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes, 0, fileInputStream.available());
            System.out.println(new String(bytes));
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            FileChannel writeChannel = fileOutputStream.getChannel();
            ByteBuffer wrap = ByteBuffer.wrap("simple".getBytes());
            writeChannel.write(wrap);
            FileChannel readChannel = fileInputStream.getChannel();
            ByteBuffer readBuffer = ByteBuffer.allocate(fileInputStream.available());
            readChannel.read(readBuffer);
            System.out.println(new String(readBuffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void byteArrayStream() throws FileNotFoundException {
        String str = "this is a byte array stream";
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(str.getBytes(), 0, str.length());
        System.out.println((char) arrayInputStream.read());
        System.out.println((char) arrayInputStream.read());
    }


    public static void objectStream() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("D://test/object.data")); ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D://test/object.data"))) {
            User user = new User();
            user.setId(1);
            user.setName("jim");
            outputStream.writeObject(user);
            User store = (User) inputStream.readObject();
            System.out.println(store.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void bufferedStream() {
        //指定缓存吃大小
        //new BufferedInputStream(new FileInputStream(new File("")), 8 * 1024);
        Stopwatch stopwatch = Stopwatch.createStarted();
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("F://ons.log")))) {
            byte[] bytes = new byte[2048];
            while (bufferedInputStream.read(bytes) != -1) {
                bufferedInputStream.read(bytes, 0, bytes.length);
                System.out.println(new String(bytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("**************" + stopwatch.stop() + "*******************");
    }





    public static void pipedStream() throws IOException {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        EXECUTOR_SERVICE.execute(() -> {
            try {
                pipedOutputStream.write("Hello,pipe".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        EXECUTOR_SERVICE.execute(() -> {
            try {
                byte[] bytes = new byte[pipedInputStream.available()];
                pipedInputStream.read(bytes, 0, pipedInputStream.available());
                System.out.println(new String(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void objectInputStream() {

    }











}
