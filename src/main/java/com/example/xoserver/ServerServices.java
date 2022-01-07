package com.example.xoserver;

import org.json.JSONObject;

import java.util.Vector;

public class ServerServices implements ServerServicesTerms {


    /*
     please i want the Jason Object String Be like this in the return String ::


     for getTheOnlineUsers :
                 "[{\"PlayerIP\":\""+SetPlayerIPHere+"\"},....]"

     for getTheServerLeaderBoard :
                 "[{\"PlayerUserName\":\""+SetPlayerUserNameHere+"\"},....]"
    */


    @Override
    public boolean sendPlayRequestToTheOpponent(JSONObject jsonObject) {
        return false;
    }

    @Override
    public boolean sendAnswerRequestToTheOpponent(JSONObject jsonObject) {
        return false;
    }

    @Override
    public boolean iWonTheGame(JSONObject jsonObject) {
        return false;
    }

    @Override
    public boolean updateTheGameBoard(JSONObject jsonObject) {
        return false;
    }

    @Override
    public String getTheOnlineUsers(Vector<?> clientsVector, Vector<String> clientsVectorNames, Vector<String> available) {
        return null;
    }

    @Override
    public boolean endTheGameNormally(JSONObject jsonObject) {
        return false;
    }

    @Override
    public boolean endTheGameWithSurrender(JSONObject jsonObject) {
        return false;
    }

    @Override
    public boolean requestDrawFromTheOpponent(JSONObject jsonObject) {
        return false;
    }

    @Override
    public String getTheServerLeaderBoard(JSONObject jsonObject) {
        return null;
    }
}
