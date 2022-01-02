package com.example.xoserver;

import javafx.fxml.Initializable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerHandler extends Thread implements Initializable{


    DataInputStream dis;
    PrintStream ps;
    static Vector<ServerHandler> clientsVector = new Vector<ServerHandler>();
    int x = 0 ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ServerHandler(Socket cs){
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            ServerHandler.clientsVector.add(this);
            x =  ServerHandler.clientsVector.size() - 1;
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void run(){

        while(true){
            try {
                String str = dis.readLine();
                //sendMessageToSender(str);
            } catch (IOException ex) {
                try {

                    dis.close();
                    ps.close();
                    //clientsVector.remove(this);

                } catch (IOException ex1) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }

            }
        }
    }

}
