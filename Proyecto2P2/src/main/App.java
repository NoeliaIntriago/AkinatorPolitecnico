/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author DELL
 */
public class App extends Application { 

    public static void main(String[] args) {
        launch(args);       
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource("src/ventanas/Juego.fxml")); 
        Scene scene = new Scene(root);
        stage.setTitle("BIENVENIDO");
        stage.setScene(scene);
        stage.show();
    }

}
