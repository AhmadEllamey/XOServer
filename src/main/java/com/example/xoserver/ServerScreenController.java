
package com.example.xoserver;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

    ServerSocket myServerSocket;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ToDo what happened when the Screen is loading (as a constructor for the screen)

        restartServerButton.setDisable(true);

    }

    public ServerScreenController(){


        try {
            myServerSocket = new ServerSocket(5005);
            restartServerButton.setDisable(false);
            while(true){

                Socket s = myServerSocket.accept();
                new ServerHandler(s);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



    @FXML
    void manageRestartServerButton(ActionEvent event) {

        try {
            myServerSocket.close();
            System.out.println("Server Shut Down Correctly ...");
            new ServerScreenController();
            System.out.println("Server Opened Again Correctly ...");
            restartServerButton.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void manageServerStatesToggleButton(ActionEvent event) {

        if(serverStatesToggleButton.isSelected()){

            new ServerScreenController();
            restartServerButton.setDisable(false);

        }else {

            try {
                myServerSocket.close();
                System.out.println("System Shut Down Correctly ...");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
