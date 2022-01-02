package com.example.xoserver;

public interface ServerServicesTerms {


    String sendTheUserHisIndexInTheServer(String info);

    String sendTheUserTheIndexOfHisOpponentInTheServer(String info);

    String[] updateTheOnlinePlayersList();

    boolean updateTheGameBoard(String destinationPortWithInfo);

    boolean updateTheGameState(String destinationPortWithInfo);

    boolean sendPlayRequestToTheOpponent(String destinationPortWithInfo);

    boolean sendAnswerRequestToTheOpponent(String destinationPortWithInfo);

}
