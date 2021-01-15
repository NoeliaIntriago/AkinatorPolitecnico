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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pantallas.JuegoController;
import tda.DecisionTree;

/**
 * FXML Controller class
 *
 * @author pc-4k
 */
public class No_AdivinoController implements Initializable { 
    
    //private JuegoController juego;    
    @FXML        
    private Text descripcion;
    private Text advertencia;
    private TextField respuesta;
    private TextField pregunta;
    private Button guardar1;
    private Button guardar2;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //DecisionTree.Node<String> ultimo = juego.ultimo;
        //System.out.println(ultimo.getData());
        advertencia.setText("");
        descripcion.setText(" ");              
    } 
    
    public void guardarRespuesta(){        
        //String animal = respuesta.getText();
        descripcion.setText("¿Cuál es la diferencia entre un ");
        advertencia.setText("Por favor llena todos los espacios");
    }
    
    public void guardarPregunta(){
        String descripcion = pregunta.getText();
    }
}
