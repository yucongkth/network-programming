package test_hangman;

import java.io.*;
import java.net.*;

public class ThreadWriter extends Thread {

    Socket socket;

    public ThreadWriter(Socket socket) {
        this.socket = socket;  
    }

    @Override
    public void run() {
        try {
            BufferedReader bufr
                    = new BufferedReader(new InputStreamReader(System.in));                       //建立一个buffer来放置从外部读取的数据流
            BufferedWriter bufout
                    = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));       //建立一个buffer来放置要写出的数据流

            String line;
            while ((line = bufr.readLine()) != null) {
                if ("QUIT".equals(line)) {
                    break;
                }                                                                                 //输入NO 就会停止运行 保证客户端能够随时停止游戏
                bufout.write(line);
                bufout.newLine();
                bufout.flush();                                                                  //将buffer中的数据推出去
            }
            socket.close();                                                                      //关闭服务
            bufr.close();

        } catch (Exception e) {

        }
    }
}
