package com.example.xoserver;


import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static com.example.xoserver.DBConnection.*;
import static com.example.xoserver.ServerHandler.*;

public class DatabaseServices implements DatabaseServicesTerms {



    /*
     please i want the Jason Object String Be like this in the return String ::

     for login and getUserData  :
                "{\"UserName\": \""+SetTheUserNameHere+"\", \"UserEmail\": \""+SetTheUserEmailHere+"\", \"UserPhone\": \""+SetUserPhoneHere+"\", \"TotalGames\": \""+SetTotalGamesHere+"\", \"TotalScore\": \""+SetTotalScoreHere+"\"}"

     for viewGames :
                 "[{\"GameNumber\":\""+SetGameNumberHere+"\",\"OpponentPlayer\":\""+SetOpponentHere+"\",\"GameTime\":\""+SetGameTimeHereHere+"\"},....]"

     for viewGameFlow :
                 "[{\"MovePlace\":\""+SetMovePlaceHere+"\"},....]"

    */


    @Override
    public synchronized String login(JSONObject jsonObject) {
        String jasonObjectString = null;
        try{
            OpenConnection();
            String userName = jsonObject.getString("UserName");
            String password = jsonObject.getString("Password");

            Statement CheckTheUser =  connection.createStatement();
            String Check = "SELECT * FROM player WHERE UserName = '"+userName+
                    "' AND password = '"+password+"'";
            ResultSet resultSet = CheckTheUser.executeQuery(Check);

            if(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    jasonObjectString ="{\"UserName\": \""+userName+"\", \"UserEmail\": \""+resultSet.getString("Email")
                            +"\", \"UserPhone\": \""+resultSet.getString("Phone")
                            +"\", \"TotalGames\": \""+resultSet.getString("TotalGame")
                            +"\", \"FunctionMode\": \""+"PassedTheLogIn"
                            +"\", \"TotalScore\": \""+resultSet.getString("TotalScore")+"\"}";
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        CloseConnection();

        System.out.println(jasonObjectString);
        return jasonObjectString;
    }

    @Override
    public synchronized boolean register(JSONObject jsonObject) {

        boolean retVal = false ;

        try{
            OpenConnection();
            Statement registerUser =  connection.createStatement();
            String userQuery = "INSERT INTO player (UserName, password, Email, Phone) VALUES ('"
                    +jsonObject.getString("UserName")+
                    "','"+jsonObject.getString("Password")+
                    "','"+jsonObject.getString("Email")+
                    "','"+jsonObject.getString("Phone")+"')";
            registerUser.executeUpdate(userQuery);
            retVal = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        CloseConnection();
        return retVal;
    }

    @Override
    public synchronized String getUserData(JSONObject jsonObject) {
         OpenConnection();
         PreparedStatement stmt;
        String userData = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Player where UserName =?");
            stmt.setString(1,jsonObject.getString("UserName"));
            ResultSet rs =stmt.executeQuery();
            userData="{UserName\": \""+rs.getString("UserName ")+
                    "\", \"UserEmail\": \""+rs.getString("Email ")+
                    "\", \"UserPhone\": \""+rs.getString("Phone")+
                    "\", \"TotalGames\": \""+rs.getString("TotalGame ")+
                    "\", \"TotalScore\": \""+rs.getString("TotalScore ")+
                    "\"}";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }

    @Override
    public synchronized boolean updateProfile(JSONObject jsonObject) {
        boolean retVal = false ;

        try{
            OpenConnection();

            Statement updateUser =  connection.createStatement();
            String userQuery = "UPDATE player SET password = '"+jsonObject.getString("Password")+
                    "' , Email = '"+jsonObject.getString("Email")+
                    "' , Phone = '"+jsonObject.getString("Phone")+
                    "' WHERE UserName = '"+jsonObject.getString("UserName")+"'";
            updateUser.executeUpdate(userQuery);
            retVal = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        CloseConnection();
        return retVal;

    }

    @Override
    public synchronized boolean updateScore(JSONObject jsonObject) {

        boolean returnType = false ;
        try{
            OpenConnection();
            String userName = jsonObject.getString("UserName");
            Statement updateTheScore =  connection.createStatement();
            String updateScore = "UPDATE player SET TotalGame = TotalGame + 1  , TotalScore = TotalScore + 3 WHERE UserName = '"+userName+"'";
            updateTheScore.executeUpdate(updateScore);
            returnType = true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        CloseConnection();


        return returnType;
    }

    @Override
    public synchronized boolean saveGame(JSONObject jsonObject) {
        boolean retVal = false ;
        try {
            OpenConnection();
            Statement saveTheGame =  connection.createStatement();
            String gameQuery = "Insert Into game (Game_Number, Player_1, Player_2, Game_Movements) VALUES ('"+
                    jsonObject.getString("GameNumber")+
                    "','"+jsonObject.getString("MainPlayer")+
                    "','"+jsonObject.getString("OpponentPlayer")+
                    "','"+jsonObject.getString("TheStringTrack")+"')";
            saveTheGame.executeUpdate(gameQuery);
            retVal = true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        CloseConnection();
        return retVal;
    }

    @Override
    public synchronized String viewGames(JSONObject jsonObject) {
        PreparedStatement stmt;
        String gameData;
        try {
            OpenConnection();
            stmt = connection.prepareStatement("SELECT * FROM game where player_1 = '"+jsonObject.getString("UserName")+"'");
            ResultSet rs =stmt.executeQuery();
            gameData = "[";
            while(rs.next()){
                gameData = gameData + "{\"GameNumber\":\""+rs.getString("Game_Number")+
                        "\",\"OpponentPlayer\":\""+rs.getString(" Player_2")+
                        "\",\"GameTime\":\""+rs.getString("Game_Time ")+"\"} ,";
            }
            gameData = gameData.substring(0,gameData.length()-1);
            gameData = gameData + "]" ;
        } catch (SQLException e) {
            e.printStackTrace();
            gameData = null;
        }

        return gameData;
    }

    @Override


    public synchronized String getTheServerLeaderBoard(JSONObject jsonObject) {

        PreparedStatement stmt;
        String playerData ;
        try {
            OpenConnection();
            stmt = connection.prepareStatement("SELECT * FROM player ORDER BY TotalScore DESC ");
            ResultSet rs =stmt.executeQuery();
            playerData = "[";
            while(rs.next()){
                playerData = playerData + "{\"UserName\":\""+rs.getString("UserName")+
                        "\",\"TotalGames\":\""+rs.getString("TotalGames")+
                        "\",\"TotalScore\":\""+rs.getString("TotalScore")+"\"} ,";
            }
            playerData = playerData.substring(0,playerData.length()-1);
            playerData = playerData + "]" ;
        } catch (SQLException e) {
            e.printStackTrace();
            playerData = null;
        }

        return playerData;
    }
    public synchronized String viewGameFlow(JSONObject jsonObject) {
        String jsonText = "[";

        try {
            String query = "SELECT * FROM game";
            Statement stmt = DBConnection.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                if (res.getString("Player_1").equals(jsonObject.get("UserName"))) {
                    String gameMovements = res.getString("Game_Movements");
                    jsonText = jsonText + "{\"moveIndex\":\"" + gameMovements + "\"}";
                } else if (res.getString("Player_2").equals(jsonObject.get("UserName"))) {
                    String gameMovements = res.getString("Game_Movements");
                    jsonText = jsonText + "{\"moveIndex\":\"" + gameMovements + "\"}";
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        jsonText = jsonText.substring(0, jsonText.length()-1)+ "]" ;
        DBConnection.CloseConnection();
        return jsonText;

    }

    @Override
    public synchronized String  getTheServerOnlinePlayers() {

        String onlinePlayers ;
        try {
            onlinePlayers =
                    "[{\"FunctionMode\":\" TheOnlinePlayers \" },";
            for (Object obj : clientsVectorRealNames) {
                if (clientsVectorStates.get(clientsVectorRealNames.indexOf(obj))) {
                    onlinePlayers = onlinePlayers +
                            "{\"PlayerName\":\""+obj+
                            "\",\"PlayerIP\":\""+clientsVector.get(clientsVectorRealNames.indexOf(obj))+
                            "\"},";
                }
            }
            onlinePlayers = onlinePlayers.substring(0,onlinePlayers.length()-1);
            onlinePlayers = onlinePlayers + "]";
        }catch (Exception e){
            onlinePlayers = null ;
        }

        return onlinePlayers;
    }
}
