package com.lyoyang.ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class SSLClient {
    private SSLSocket socket = null;

    public SSLClient() throws IOException {
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        socket = (SSLSocket) socketFactory.createSocket("localhost", 8080);
    }

    private void connect() {
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String userName = "principal";
            String passwd = "credential";
            printWriter.println(userName);
            printWriter.println(passwd);
            printWriter.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            response += "\n" + reader.readLine();
            System.out.println(response);
            printWriter.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        new SSLClient().connect();
    }


}
