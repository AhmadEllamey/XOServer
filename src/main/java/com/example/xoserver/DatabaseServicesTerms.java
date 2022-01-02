package com.example.xoserver;

public interface DatabaseServicesTerms {


    boolean login(String username , String password);

    boolean register(String username , String password , String name , String mail , String phone);

    PlayerModel getUserData();

    boolean updateProfile(String username , String password , String name , String mail , String phone);

    boolean updateScore(String username ,String newScore);

    boolean saveGame(String username , String gameNumber , String opponentPlayerUserName , String filePath);

    String viewGame();


}





