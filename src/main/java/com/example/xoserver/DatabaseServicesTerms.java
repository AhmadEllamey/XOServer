package com.example.xoserver;

import org.json.JSONObject;

public interface DatabaseServicesTerms {


    String login(JSONObject jsonObject);

    boolean register(JSONObject jsonObject);

    String getUserData(JSONObject jsonObject);

    boolean updateProfile(JSONObject jsonObject);

    boolean updateScore(JSONObject jsonObject);

    boolean saveGame(JSONObject jsonObject);

    String viewGames(JSONObject jsonObject);

    String viewGameFlow(JSONObject jsonObject);

    String getTheServerLeaderBoard(JSONObject jsonObject);

    String getTheServerOnlinePlayers();


}





