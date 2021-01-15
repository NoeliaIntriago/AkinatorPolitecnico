/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
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
    
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);       
    }
    
    @Override
    public void start(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanas/Inicio.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("GENIA POLITÉCNICA");
            stage.show();
        }catch(IOException e){
            System.err.println("Error al mostrar la pantalla: "+e);
        }
    }
}
