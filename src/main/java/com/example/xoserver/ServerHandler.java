package com.example.xoserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import org.json.*;

class ServerHandler extends Thread{


    static Vector<ServerHandler> clientsVector = new Vector<ServerHandler>();
    static Vector<String> clientsVectorNames = new Vector<String>();

    DataInputStream dataInputStream;
    PrintStream printStream;
    int clientIndex = 0 ;
    Object clientNameInServer;
    boolean isThisIsTheFirstConnectionWithTheServer;




    public ServerHandler(Socket cs){
        try {
            dataInputStream = new DataInputStream(cs.getInputStream());
            printStream = new PrintStream(cs.getOutputStream());
            ServerHandler.clientsVector.add(this);
            clientIndex =  ServerHandler.clientsVector.size() - 1;
            clientNameInServer = ServerHandler.clientsVector.get(clientIndex);
            ServerHandler.clientsVectorNames.add(String.valueOf(clientNameInServer));
            isThisIsTheFirstConnectionWithTheServer = false ;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        while(true){

            try {
                String incomingLine = dataInputStream.readLine();
                if(!isThisIsTheFirstConnectionWithTheServer){
                    if(incomingLine.equals("Please I need My Thread Name !")){
                        sendMessageToSender(String.valueOf(clientNameInServer));
                        isThisIsTheFirstConnectionWithTheServer = true ;
                    }
                }else{

                    if(incomingLine != null){
                        try{
                            JSONObject jsonObject= new JSONObject(incomingLine);
                            String functionMode = jsonObject.getString("functionMode");
                            if(functionMode != null){
                                // the server redirect the message after checking the mode of the function
                                switch (functionMode){

                                    // database services
                                    case "loginRequest" :

                                    case "registerRequest" :

                                    case "getUserInfoRequest" :

                                    case "updateUserInfoRequest" :

                                    case "updateScoreInfoRequest" :

                                    case "saveTheGameRequest" :

                                    case "viewTheGameRequest" :


                                    // server services

                                    case "sendPlayRequest" :

                                    case "sendAnswerToPlayRequest" :

                                    case "sendIWonRequest" :

                                    case "sendUpdateTheGameBoardRequest" :

                                    case "getTheOnlinePlayersOnTheServerRequest" :

                                    case "endTheGameNormallyAfterTheGameFinishedRequest" :

                                    case "endGameRequestWithSurrenderRequest" :

                                    case "requestDrawFromTheOpponentRequest" :

                                    case "getTheRankOfPlayersOnTheServerRequest" :

                                }


                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                }

            } catch (IOException ex) {
                try {

                    dataInputStream.close();
                    printStream.close();
                    clientsVector.remove(this);
                    clientsVectorNames.remove(String.valueOf(clientNameInServer));
                    // ToDo should i break the loop ?????????
                    //break ;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }


    void sendMessageToSender(String msg){
        ServerHandler sh = clientsVector.get(clientIndex) ;
        sh.printStream.println(msg);
    }

    void sendMessageToDestination(String msg , int toIndexNumber){
        ServerHandler ch = clientsVector.get(toIndexNumber) ;
        ch.printStream.println(msg);
    }


}