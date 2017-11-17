/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_hangman;

import java.net.*;

public class MultiServer1 {

    public static void main(String[] args) {
        try {                                                          
            ServerSocket server = new ServerSocket(10001);                      //server will listen to port 10001
            {
                
                boolean f = true;
                while (f) {
                                                                             
                    Socket client = server.accept();
                    String ip = client.getInetAddress().getHostAddress();       //get the IP address of the client
                    System.out.println(ip + "......connected ");                //show the ip address of the client on the server side
                    new Thread(new ServerThread(client)).start();               //create a new thread for every connected client. 
                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
