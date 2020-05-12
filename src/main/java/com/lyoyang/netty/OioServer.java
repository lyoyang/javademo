package com.lyoyang.netty;

import com.sun.corba.se.spi.activation.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Auther: yangbing
 * @Date: 2019/3/26 20:20
 * @Description:
 */
public class OioServer {


    public static void server(int port) {
        try {
            final ServerSocket serverSocket = new ServerSocket(port);
            for(;;) {
                Socket accept = serverSocket.accept();
                System.out.println("Accepted Connection from:" + accept);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = accept.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            accept.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        server(8088);
    }

}
