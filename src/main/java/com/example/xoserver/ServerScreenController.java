package com.example.xoserver;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerScreenController implements Initializable {


    @FXML
    private Label numberOfOnlinePlayersLabel;

    @FXML
    private Label numberOfCurrentGamesLabel;

    @FXML
    private JFXToggleButton serverStatesToggleButton;

    @FXML
    private JFXButton restartServerButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ToDo what happened when the Screen is loading (as a constructor for the screen)

    }



    @FXML
    void manageRestartServerButton(ActionEvent event) {

    }

    @FXML
    void manageServerStatesToggleButton(ActionEvent event) {

    }


}