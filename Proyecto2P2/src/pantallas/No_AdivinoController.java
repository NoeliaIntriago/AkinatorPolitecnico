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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import tda.DecisionTree;
import static tda.DecisionTree.loadTree;

/**
 * FXML Controller class
 *
 * @author pc-4k
 */
public class No_AdivinoController implements Initializable {     
    
    public JuegoController juegoController = new JuegoController();
    private String animal;
    private String desc; 
    private String b;    
    private String ultimo2;
    private final DecisionTree<String> arbol = DecisionTree.loadTree();
    @FXML        
    private Text descripcion;
    @FXML    
    private Text descripcion2;
    @FXML
    private Text advertencia;
    @FXML
    private Pane guardado;    
    @FXML
    private Pane campo_booleano;
    @FXML
    private Pane campo_respuesta;
    @FXML
    private TextField respuesta;
    @FXML
    private TextField pregunta;
    @FXML
    private TextField pregunta1;
    @FXML
    private Button guardar1;
    @FXML
    private Button guardar2;    
    @FXML
    private Button guardar3;  
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {         
        System.out.println(obtenerUltimo(juegoController));
        setLocalUltimo("un perro.");
        campo_booleano.setVisible(false);
        campo_respuesta.setVisible(false);
        advertencia.setText("");
        guardado.setVisible(false);
    }
    
    public void setLocalUltimo(String ultimo){
        ultimo2 = ultimo;        
    }
    
    public void setController(JuegoController jc){
        this.juegoController = jc;
    }
    
    public JuegoController getController(){
        return juegoController;
    }
    
    public String obtenerUltimo(JuegoController juegoController){
        JuegoController jc = new JuegoController();
        setController(jc);                
        ultimo2 = getController().getUltimo();
        return ultimo2;
    }
        
    public void guardarRespuesta(){      //validar si no ingresa signos o números          
        animal = respuesta.getText();
        if(animal.isEmpty()){
            advertencia.setText("Por favor, llena todos los campos!!");            
        }else{
        animal = animal.strip();        
        if(animal.endsWith("a")&& !animal.startsWith("u")){
            animal = "una "+animal;
        }else if(!animal.startsWith("u")){
            animal = "un "+animal;
        }
        campo_respuesta.setVisible(true);           
        descripcion.setText("¿Cual es la diferencia entre "+animal+" y "+ultimo2+"?");
        }                
    }
    
    public void guardarPregunta(){    //validar si no ingresa signos o números         
        desc = pregunta.getText();
        if(desc.isEmpty()|| animal.isEmpty()){
            advertencia.setText("Por favor, llena todos los campos!!");
        }else{            
            campo_booleano.setVisible(true);
            desc+= "?";            
            descripcion2.setText("Para "+animal +" la respuesta a la pregunta: "+desc+" es Sí o No?");
        }           
    }
    
    public void guardarBoolean(){     //validar si no ingresa signos o números   //validar si ingresa si o no        
        b = pregunta1.getText();
        if(desc.isEmpty()|| animal.isEmpty()|| b.isEmpty()){
            advertencia.setText("Por favor, llena todos los campos!!");
        }else{                         
            String b2 = b.toLowerCase();
            if(b2.contains("s")){
                System.out.println(b2);
                arbol.replace(ultimo2, desc);
                arbol.add(animal, desc, true);
                arbol.add(ultimo2,desc,false);
            }if(b2.contains("n")){
                System.out.println(b2);
                arbol.replace(ultimo2, desc);
                arbol.add(animal, desc, false);
                arbol.add(ultimo2, desc, true);
            }
            arbol.saveTree();
            guardado.setVisible(true);            
        }  
    }
    
    public void borrarAdvertencia(){
        advertencia.setText("");
    }
    
    public void salir(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/ventanas/No_Adivino.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);            
            appStage.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    public void jugar(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/ventanas/Juego.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();
        }catch(IOException e){
            System.err.println(e);
        }
    }  
}
