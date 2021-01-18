/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc-4k
 */
public class AdivinoController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void jugar(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/ventanas/Juego.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    } 
    
    public void salir(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/ventanas/Adivino.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);            
            appStage.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
}
