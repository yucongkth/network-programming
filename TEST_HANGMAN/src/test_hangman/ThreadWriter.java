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
                    = new BufferedReader(new InputStreamReader(System.in));                       //create a buffer to store input stream
            BufferedWriter bufout
                    = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));       //create a buffer to store output stream

            String line;
            while ((line = bufr.readLine()) != null) {
                if ("QUIT".equals(line)) {
                    break;
                }                                                                                 //type in "QUIT" and the game will stop 
                bufout.write(line);
                bufout.newLine();
                bufout.flush();                                                                  //send the stream in buffer to the server
            }
            socket.close();                                                                      //close the socket and buffer
            bufr.close();

        } catch (Exception e) {

        }
    }
}
