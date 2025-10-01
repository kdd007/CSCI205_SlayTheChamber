/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Maddy Kalaigian, John Klopcic, Kevin Duong, Clea Ramos
 * Section: 02
 * Date: 4/16/24
 * Time: 10:58 PM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject
 * Class: gameViewController
 *
 * Description: Main class that creates an instance of the game to play
 *
 * ****************************************
 */
package org.FinalProject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.FinalProject.ModelClasses.Deck;
import org.FinalProject.ModelClasses.GameModel;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    private GameModel theModel;
    private gameViewController theController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.theModel = new GameModel();

        //loading the fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gameViewfxml.fxml"));
        Parent root = loader.load();


        //Set up our stage
        primaryStage.setTitle("Slay the Chamber");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styling.css").toExternalForm());

        primaryStage.setScene(scene);
        this.theController = loader.getController();
        this.theController.setModel(this.theModel);

        primaryStage.show();
    }
}