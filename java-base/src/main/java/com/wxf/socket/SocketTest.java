package com.wxf.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args) {
        try {
            //1.创建一个新的ServerSocket，用以监听指定端口上的连接请求
            ServerSocket serverSocket = new ServerSocket(8888);
            //2.accept()方法调用将被阻塞，直到一个连接建立
            Socket clientSocket = serverSocket.accept();
            //这些流对象都派生于该套接字的流对象
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String request, response;
            //循环处理
            while ((request = in.readLine()) != null) {

                if ("Done".equals(request)) break;
                response = processRequest(request);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processRequest(String request) {
        return null;
    }
}
