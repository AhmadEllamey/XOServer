package com.example.xoserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import org.json.*;

class ServerHandler extends Thread{


    static Vector<ServerHandler> clientsVector = new Vector<>();
    static Vector<String> clientsVectorNames = new Vector<>();
    static Vector<Boolean> clientsVectorStates = new Vector<>();
    static Vector<String> clientsVectorRealNames = new Vector<>();

    DataInputStream dataInputStream;
    PrintStream printStream;
    int clientIndex = 0 ;
    Object clientNameInServer;
    boolean isThisIsTheFirstConnectionWithTheServer;

    //------------------
    DatabaseServices databaseServices = new DatabaseServices();

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

        DatabaseServices databaseServices = new DatabaseServices();

        while(true){

            try {
                String incomingLine = dataInputStream.readLine();
                System.out.println(incomingLine);
                if(!isThisIsTheFirstConnectionWithTheServer){
                    if(incomingLine.equals("Please I need My Thread Name !")){
                        sendMessageToSender(String.valueOf(clientNameInServer));
                        isThisIsTheFirstConnectionWithTheServer = true ;

                    }
                }else{

                    if(incomingLine != null){

                            JSONObject jsonObject= new JSONObject(incomingLine);
                            String functionMode = jsonObject.getString("FunctionMode");
                            String Sender = jsonObject.getString("From");
                            String Receiver = jsonObject.getString("To");
                            int senderIP = ServerHandler.clientsVectorNames.indexOf(Sender) ;
                            int receiverIP = ServerHandler.clientsVectorNames.indexOf(Receiver) ;
                            System.out.println(jsonObject.getString("FunctionMode"));
                            System.out.println(jsonObject.getString("From"));
                            System.out.println(jsonObject.getString("To"));
                            System.out.println(jsonObject.getString("UserName"));
                            System.out.println(jsonObject.getString("Password"));
                            if(functionMode != null){
                                // the server redirect the message after checking the mode of the function
                                String destName = "";
                                int destIndex = clientsVectorNames.indexOf(destName);
                                switch (functionMode){

                                    // database services

                                    case "loginRequest" :
                                        System.out.println("We are at log in case");
                                        sendMessageToSender(databaseServices.login(jsonObject));
                                        /*
                                        if(databaseServices.login(jsonObject)!=null){
                                            ServerHandler.clientsVectorRealNames.add(jsonObject.getString("UserName"));
                                            ServerHandler.clientsVectorStates.add(true);
                                            sendMessageToSender(databaseServices.getTheServerOnlinePlayers());
                                        }
                                         */
                                        break;
                                    case "registerRequest" :
                                        String message ;
                                        if(!databaseServices.register(jsonObject)){
                                            message = "RegistrationFailed";
                                        }else{
                                            message = "RegistrationAccepted";
                                        }
                                        sendMessageToSender(message);
                                        break;
                                    case "getUserInfoRequest" :
                                        sendMessageToSender(databaseServices.getUserData(jsonObject));
                                        break;
                                    case "updateUserInfoRequest" :
                                        if (databaseServices.updateProfile(jsonObject)){
                                            sendMessageToDestination("Updated Successfully", destIndex);
                                        }
                                        else{
                                            sendMessageToDestination("update failed",destIndex);
                                        }
                                        break;

                                    case "updateScoreInfoRequest" :
                                        String message3 ;
                                        if(!databaseServices.updateScore(jsonObject)){
                                            message3 = "UpdateScoreFailed";
                                        }else{
                                            message3 = "UpdateScoreAccepted";
                                        }
                                        sendMessageToSender(message3);
                                        break;
                                    case "saveTheGameRequest" :
                                        if (databaseServices.updateProfile(jsonObject)){
                                            sendMessageToDestination("save Successfully", destIndex);
                                        }
                                        else{
                                            sendMessageToDestination("Save Failed",destIndex);
                                        }
                                        break;

                                    case "viewTheGameRequest" :
                                        sendMessageToSender(databaseServices.viewGameFlow(jsonObject));
                                        break;
                                    case "viewAllGameRequest" :
                                        sendMessageToSender(databaseServices.viewGames(jsonObject));
                                        break;
                                    // server services
                                    case "getTheLeaderBoardPlayers" :
                                        sendMessageToSender(databaseServices.getTheServerLeaderBoard(jsonObject));
                                        break;
                                    case "sendPlayRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("sendPlayerRequestFailed");
                                        }
                                        break;
                                    case "sendAnswerToPlayRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("yourAnswerRequestFailed");
                                        }
                                        break;
                                    case "sendIWonRequest" :
                                        sendMessageToDestination("you lose",destIndex);
                                        break;

                                    case "sendUpdateTheGameBoardRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("UpdateTheGameBoardRequestFailed");
                                        }
                                        break;
                                    case "getTheOnlinePlayersOnTheServerRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("getTheOnlinePlayersRequestFailed");
                                        }
                                        break;
                                    case "endTheGameNormallyAfterTheGameFinishedRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("endTheGameNormallyAfterTheGameFinishedRequestFailed");
                                        }
                                        break;
                                    case "endGameRequestWithSurrenderRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("endGameRequestWithSurrenderRequestFailed");
                                        }
                                        break;
                                    case "requestDrawFromTheOpponentRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("requestDrawFromTheOpponentRequestFailed");
                                        }
                                        break;
                                    case "getTheRankOfPlayersOnTheServerRequest" :
                                        try{
                                            sendMessageToDestination(incomingLine,receiverIP);
                                        }catch (Exception e){
                                            sendMessageToSender("getTheRankOfPlayersOnTheServerRequestFailed");
                                        }
                                        break;
                                }

                                System.out.println("Finished The Switch");


                            }
                    }

                }

            } catch (IOException ex) {
                try {
                    //ex.printStackTrace();
                    dataInputStream.close();
                    printStream.close();
                    clientsVector.remove(this);
                    clientsVectorNames.remove(String.valueOf(clientNameInServer));
                    // ToDo should i break the loop ?????????
                    break ;

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