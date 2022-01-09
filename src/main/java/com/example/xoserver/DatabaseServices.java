package com.example.xoserver;


import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        return null;
    }

    @Override
    public boolean updateProfile(JSONObject jsonObject) {
        boolean retVal = false;
        DBConnection.OpenConnection();
        try {
            PreparedStatement pst = DBConnection.connection.prepareStatement("UPDATE player SET Email = ?,Phone=? WHERE UserName = ?");
            pst.setString(1,(String) jsonObject.get("UserEmail"));
            pst.setString(2,(String) jsonObject.get("UserPhone"));
            pst.setString(3,(String) jsonObject.get("UserName"));

            retVal = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.CloseConnection();

        return retVal;
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
        return null;
    }

    @Override
    public String viewGameFlow(JSONObject jsonObject) {
        String jsonText = "";
        try {
            String query = "SELECT * FROM game";
            Statement stmt = DBConnection.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                if (res.getString("Player_1").equals((String) jsonObject.get("UserName"))) {
                    String gameMovements = res.getString("Game_Movements");
                    jsonText = jsonText + "{\"moveIndex\":\"" + gameMovements + "\"}";
                } else if (res.getString("Player_2").equals((String) jsonObject.get("UserName"))) {
                    String gameMovements = res.getString("Game_Movements");
                    jsonText = jsonText + "{\"moveIndex\":\"" + gameMovements + "\"}";
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.CloseConnection();

        return jsonText;

    }

    @Override
    public String getTheServerLeaderBoard(JSONObject jsonObject) {
        return null;
    }
}
