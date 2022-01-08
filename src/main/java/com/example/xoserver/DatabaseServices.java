package com.example.xoserver;


import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static com.example.xoserver.DBConnection.*;

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
    public String login(JSONObject jsonObject) {
        String jasonObjectString = "";
        try{
            OpenConnection();
            String userName = jsonObject.getString("UserName");
            String password = jsonObject.getString("Password");

            Statement CheckTheUser =  connection.createStatement();
            String Check = "SELECT * FROM player WHERE UserName = '"+userName+
                    "' AND password = '"+password+"'";
            ResultSet resultSet = CheckTheUser.executeQuery(Check);

            if(resultSet.next()){
                jasonObjectString ="{\"UserName\": \""+userName+"\", \"UserEmail\": \""+resultSet.getString("Email")
                        +"\", \"UserPhone\": \""+resultSet.getString("Phone")
                        +"\", \"TotalGames\": \""+resultSet.getString("TotalGames")
                        +"\", \"TotalScore\": \""+resultSet.getString("TotalScore")+"\"}";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        CloseConnection();

        return jasonObjectString;
    }

    @Override
    public boolean register(JSONObject jsonObject) {
        return false;
    }

    @Override
    public String getUserData(JSONObject jsonObject) {
         OpenConnection();
         DBConnection dbConnection;
         PreparedStatement stmt;

        String userData=null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM Player where UserName =?");
            stmt.setString(1,jsonObject.getString("UserName"));
            ResultSet rs =stmt.executeQuery();
            userData="UserName\": \""+rs.getString("UserName ")+"\", \"UserEmail\": \""+rs.getString("Email ")+"\", \"UserPhone\": \""+rs.getString("Phone")+"\", \"TotalGames\": \""+rs.getString("TotalGame ")+"\", \"TotalScore\": \""+rs.getString("TotalScore ")+"\"}";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userData;
    }

    @Override
    public boolean updateProfile(JSONObject jsonObject) {
        return false;
    }

    @Override
    public boolean updateScore(JSONObject jsonObject) {

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
    public boolean saveGame(JSONObject jsonObject) {
        return false;
    }

    @Override
    public String viewGames(JSONObject jsonObject) {
        OpenConnection();
        DBConnection dbConnection;
        PreparedStatement stmt;
        String gameData=null;
        try {

            stmt = connection.prepareStatement("SELECT * FROM game");
            ResultSet rs =stmt.executeQuery();
            if(rs.next()){
                if(rs.getString("Player_1 ")==jsonObject.getString("UserName")){
                    gameData="{\"GameNumber\":\""+rs.getString("Game_Number  ")+"\",\"OpponentPlayer\":\""+rs.getString(" Player_2")+"\",\"GameTime\":\""+rs.getString("Game_Time ")+"\"}";
                }
                else if(rs.getString("Player_2 ")==jsonObject.getString("UserName")){
                    gameData=gameData="{\"GameNumber\":\""+rs.getString("Game_Number  ")+"\",\"OpponentPlayer\":\""+rs.getString(" Player_1")+"\",\"GameTime\":\""+rs.getString("Game_Time ")+"\"}";
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gameData;
    }

    @Override
    public String viewGameFlow(JSONObject jsonObject) {
        return null;
    }

    @Override
    public String getTheServerLeaderBoard(JSONObject jsonObject) {
        return null;
    }
}
