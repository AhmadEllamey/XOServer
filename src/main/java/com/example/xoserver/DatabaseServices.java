package com.example.xoserver;


import org.json.JSONObject;

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
        return null;
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
        return false;
    }

    @Override
    public boolean updateScore(JSONObject jsonObject) {
        return false;
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
        return null;
    }
}
