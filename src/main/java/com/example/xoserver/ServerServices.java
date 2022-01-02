package com.example.xoserver;

public class ServerServices implements ServerServicesTerms {


    @Override
    public String sendTheUserHisIndexInTheServer(String info) {
        return null;
    }

    @Override
    public String sendTheUserTheIndexOfHisOpponentInTheServer(String info) {
        return null;
    }

    @Override
    public String[] updateTheOnlinePlayersList() {
        return new String[0];
    }

    @Override
    public boolean updateTheGameBoard(String destinationPortWithInfo) {
        return false;
    }

    @Override
    public boolean updateTheGameState(String destinationPortWithInfo) {
        return false;
    }

    @Override
    public boolean sendPlayRequestToTheOpponent(String destinationPortWithInfo) {
        return false;
    }

    @Override
    public boolean sendAnswerRequestToTheOpponent(String destinationPortWithInfo) {
        return false;
    }
}
