/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_hangman;
import java.io.*;
import java.net.*;


class Test {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("130.229.161.177", 10001);                 //建立一个socket来向服务端的10001端口发送信息

        new ThreadWriter(socket).start();                                   //调用threadwriter 向服务器写数据
        new ThreadReader(socket).start();                                   //调用threadreader 读取服务器发送过来的数据
    }

}
