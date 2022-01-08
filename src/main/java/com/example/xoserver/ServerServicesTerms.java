package com.example.xoserver;
import org.json.*;
import java.util.Vector;

public interface ServerServicesTerms {


    boolean sendPlayRequestToTheOpponent(JSONObject jsonObject);

    boolean sendAnswerRequestToTheOpponent(JSONObject jsonObject);

    boolean iWonTheGame(JSONObject jsonObject);

    boolean updateTheGameBoard(JSONObject jsonObject);

    String getTheOnlineUsers(Vector<?> clientsVector , Vector<String> clientsVectorNames , Vector<String> available);

    boolean endTheGameNormally(JSONObject jsonObject);

    boolean endTheGameWithSurrender(JSONObject jsonObject);

    boolean requestDrawFromTheOpponent(JSONObject jsonObject);



}
