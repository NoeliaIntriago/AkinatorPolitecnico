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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tda.DecisionTree;
import static tda.DecisionTree.loadTree;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class JuegoController implements Initializable {
    
    DecisionTree<String> arbol = loadTree();
    @FXML
    private Text pregunta; 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarPreguntas();
    }    
    
    public void mostrarPreguntas(){
        pregunta.setText(arbol.getRoot());
        pregunta.setVisible(true);
    }

    public void positivo(){ 
        DecisionTree.Node<String> nodo = positivo(arbol);
        pregunta.setText(nodo.getData());              
    }
    
    public DecisionTree.Node<String> positivo(DecisionTree<String> a){ 
        DecisionTree.Node<String> nodo = a.root;
        if(nodo.getYes()!=null){a.root=nodo.getYes();}        
        return a.root;
    }
    
    public void negativo(){   
        pregunta.setText(negativo(arbol));         
    } 
    
    public String negativo(DecisionTree<String> a){
        DecisionTree.Node<String> nodo = a.root;
        if(nodo.getNo()!=null){a.root = nodo.getNo();}
        return a.getRoot();
    }
    
    public void adivinar(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/ventanas/Fin.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    } 
}
