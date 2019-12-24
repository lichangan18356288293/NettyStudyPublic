package com.jqs.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Mr.Kai
 * @Date 2019/12/24 15:51
 */
public class TimeServer {
    public static void main(String[] args) {
        int port =80;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port :"+port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,1000);
            while (true){
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (server !=null){
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server = null;
            }
        }
    }
}
