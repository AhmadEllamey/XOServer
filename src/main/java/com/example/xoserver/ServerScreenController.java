
package com.example.xoserver;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
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

    /*
     - why we use initialize .
            The constructor is called first, then any @FXML annotated fields are populated,
             then initialize() is called. This means the constructor does not have access
              to @FXML fields referring to components defined in the fxml file, while initialize() does have access to them.

     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ToDo what happened when the Screen is loading (as a constructor for the screen)
        restartServerButton.setDisable(true);

    }

    public ServerScreenController(){

    }

    public void serverControllerFunction(){

        new Thread(() -> {
            try {

                    try{
                        myServerSocket.close();
                        System.out.println("This Socket Is Closed Now");
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("This Socket Is Not Opened To Be Closed");
                    }

                myServerSocket = new ServerSocket(5005);
                Platform.runLater(() ->restartServerButton.setDisable(false));
                Platform.runLater(() ->serverStatesToggleButton.setDisable(false));
                System.out.println("We Are In The Connection lol .... :)");
                //restartServerButton.setDisable(false);
                while(true){
                    System.out.println("We Are In The Connection loop lol .... :)");

                    Socket s = myServerSocket.accept();
                    new ServerHandler(s);

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();

    }





    @FXML
    void manageRestartServerButton(ActionEvent event) {
        restartServerButton.setDisable(true);
        serverStatesToggleButton.setDisable(true);
        serverControllerFunction();
    }

    @FXML
    void manageServerStatesToggleButton(ActionEvent event) {

        if(serverStatesToggleButton.isSelected()){

            System.out.println("Congrats The Server Is Running Now ... :)");
            serverControllerFunction();


        }else {

            try {
                myServerSocket.close();
                System.out.println("System Shut Down Correctly ...");
                restartServerButton.setDisable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }


}
