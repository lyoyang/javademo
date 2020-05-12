package com.lyoyang.ssl;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class SSLServer {

    // 服务器端授权的用户名和密码
    private static final String USER_NAME = "principal";
    private static final String PASSWORD = "credential";
    // 服务器端保密内容
    private static final String SECRET_CONTENT =
            "This is confidential content from server X, for your eye!";

    private SSLServerSocket serverSocket = null;

    public SSLServer() throws IOException {
        SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        serverSocket = (SSLServerSocket) socketFactory.createServerSocket(8080);
    }

    private void start() throws IOException {
        while (true) {
            try {
                System.out.println("Waiting for connection...");
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String userNmae = bufferedReader.readLine();
                String passwd = bufferedReader.readLine();
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                if (USER_NAME.equals(userNmae) && PASSWORD.equals(passwd)) {
                    printWriter.println("Welcome," + userNmae);
                    printWriter.println(SECRET_CONTENT);
                } else {
                    printWriter.println("Authentication faild,you have no access.");
                }
                printWriter.close();
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new SSLServer().start();
    }

}
