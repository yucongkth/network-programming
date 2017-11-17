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
        Socket socket = new Socket("130.229.161.177", 10001);                 //commnuicate with the server in port 10001
        

        new ThreadWriter(socket).start();                                   //use ThreadWriter to send data to the server  
        new ThreadReader(socket).start();                                   //use Threadreader to read the data from the server reader 
    }

}
