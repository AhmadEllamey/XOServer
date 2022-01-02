package com.example.xoserver;

public class DatabaseServices implements DatabaseServicesTerms {


    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean register(String username, String password, String name, String mail, String phone) {
        return false;
    }

    @Override
    public PlayerModel getUserData() {
        return null;
    }

    @Override
    public boolean updateProfile(String username, String password, String name, String mail, String phone) {
        return false;
    }

    @Override
    public boolean updateScore(String username, String newScore) {
        return false;
    }

    @Override
    public boolean saveGame(String username, String gameNumber, String opponentPlayerUserName, String filePath) {
        return false;
    }

    @Override
    public String viewGame() {
        return null;
    }
}
