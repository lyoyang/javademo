package com.lyoyang.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicModel implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            while (!Thread.interrupted()) {
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class Handler implements Runnable {

        final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("there is a connection...");
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = "hello, nice to meet you!".getBytes();
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new BasicModel()).start();
        new Thread(new BasicModel()).start();
    }


}





