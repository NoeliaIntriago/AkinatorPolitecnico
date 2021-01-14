/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pantallas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import tda.DecisionTree;
import tda.DecisionTree.Node;
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
    private Button si;
    private Button no;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        si = new Button();
        si.setText("Sí");
        no = new Button();        
        no.setText("no");
        mostrarPreguntas();
        
    }    
    
    public void mostrarPreguntas(){
        pregunta.setText(arbol.getRoot());
        pregunta.setVisible(true);
    }
    
    
    public void positivo(){
        positivo(arbol);        
    }
    
    public DecisionTree<String> positivo(DecisionTree<String> a){            
        //System.out.println(a.getRoot());
        pregunta.setVisible(false);        
        //if(a.root.getYes()==null) return null;                
        //a.root = a.root.getYes();
        //System.out.println(a.root.getYes());        
        //System.out.println("NUEVA RAIZ"+a.getRoot());
        return a;
    }
    
    public void negativo(){
        negativo(arbol);
    }
    
    public DecisionTree<String> negativo(DecisionTree<String> a){                        
        pregunta.setVisible(false);
        if(a.root.getYes()==null) return null;        
        a.root = a.root.getNo();        
        return a;
    }
    
    public void handle(ActionEvent event){
        if((event.getSource()==si)){
            positivo();  
            mostrarPreguntas();
        }else if((event.getSource()==no)){
            negativo();  
            mostrarPreguntas();
        
    }}
    /*
    public static void main(String[] args){                
        System.out.println("RAIZ DEL ARBOL -- "+loadTree().getRoot());        
    }
*/
}
