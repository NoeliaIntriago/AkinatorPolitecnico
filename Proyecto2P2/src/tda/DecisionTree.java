package tda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.Stack;

/**
 *
 * @author DELL
 */
public class DecisionTree<E> implements Comparator<E> {
    
    public Node<E> root;

    @Override
    public int compare(E o1, E o2) {
        if(o1.equals(o2)){
            return 1;
        }
        return 0;
    }
    
    public static class Node<E>{
        private E data;
        private boolean flag;
        private Node<E> yes;//izq
        private Node<E> no;//der
        
        public Node(E pregunta, Node<E> si, Node<E> no, boolean flag){
            this.data = data;
            this.yes = yes;
            this.no = no;
            this.flag = flag;
        }
        
        public Node(){
            this.data = data;
            this.yes = yes;
            this.no = no;
            this.flag = flag;
        }
        
        public Node(E data){
            this.yes=null;
            this.no=null;
            this.data = data;
            this.flag = flag;
        }
        
        public Node(E data, boolean flag){
            this.data = data;
            this.flag = flag;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public Node<E> getYes() {
            return yes;
        }

        public void setYes(Node<E> yes) {
            this.yes = yes;
        }

        public Node<E> getNo() {
            return no;
        }

        public void setNo(Node<E> no) {
            this.no = no;
        }
        
    }
    
    public DecisionTree(){
        this.root= root;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public int height(){
        return height(root);
    }
    
    private int height(Node<E> n){
        if(n == null){
            return 0;
        }
        return 1 + Math.max(height(n.getYes()), height(n.getNo()));
    }
    
    public void inOrden() {
        inOrden(root);
    }

    private void inOrden(Node<E> n) {
        if (n != null) {
            inOrden(n.getNo());
            System.out.println(n.data);
            inOrden(n.getYes());
        }
    }
    
    public String postOrden(Node<E> n){        
        if(n != null){
            if(n.getNo()!= null && n.getYes()!=null){                
                return "#P " + n.getData() +"\n"+postOrden(n.getYes())+ "\n" + postOrden(n.getNo());                
            }return "#R " + n.getData();                           
        }
        return "";
    }
    
    public E getRoot(){
        return root == null? null: root.getData();
    }
    
    private Node<E> searchNode(E data){
        if(data == null){
            return null;
        }
        return searchNode(root, data);
    }
    
    private Node<E> searchNode(Node<E> n, E data){
        if(n == null){
            return n;
        }else if(n.getData().equals(data)){
            return n;
        }else{
            Node<E> left = searchNode(n.getYes(), data);
            Node<E> right = searchNode(n.getNo(), data);
            
            if(left != null){
                return left;
            }
            return right;
        }
    }
    
    public E getAnswer(E parent, boolean respuesta){
        if(parent == null){
            return null;
        }
        Node<E> p = searchNode(parent);
        if(p == null){
            return null;
        }
        Node<E> respuestaNode = respuesta? p.getYes(): p.getNo();
        return respuestaNode == null ? null: respuestaNode.getData();
    }
    
    public boolean replace(E old, E newElement){
        if(old == null || newElement == null){
            return false;
        }
        Node<E> n = searchNode(old);
        if(n == null){
            return false;
        }
        n.setData(newElement);
        return true;
    }
    
    public boolean add(E child, E parent, boolean direccion){
        Node<E> nchild = new Node<>(child);
        if(child == null){
            return false;
        }
        if(parent == null && isEmpty()){
            root = nchild;
            return true;
        }
        if(parent != null && searchNode(child) == null){
            Node<E> nparent = searchNode(parent);
            if(direccion){
                if(nparent == null || nparent.getYes() != null){
                    return false;
                }
                nparent.setYes(nchild);
            }else{
                if(nparent == null || nparent.getNo() != null){
                    return false;
                }
                nparent.setNo(nchild);
            }
            return true;
        }
        return false;
    }
    
    public boolean addAnswer(E e){
        if(e ==  null){
            return false;
        }
        return addAnswer(root, e);
    }
    
    private boolean addAnswer(Node<E> n, E e){
        Node<E> nAnswer = new Node<>(e);
        if(e == null){
            return false;
        }else if(n == null){
            Node<E> newNodo = new Node<>(e, true);
            this.root = newNodo;
            return true;
        }else{
            if(searchNode(e) == null){
                if(n.getYes() != null && n.getNo() != null){
                    if(n.getNo().getYes() != null && n.getYes().getYes() == null && n.isFlag() == true){
                        return addAnswer(n.getYes(), e);
                    }
                    return addAnswer(n.getNo(), e);
                }else if(n.getYes() == null){
                    n.setYes(nAnswer);
                    return true;
                }else if(n.getNo() == null && n.getYes() != null){
                    n.setFlag(true);
                    n.setNo(nAnswer);
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean addQuestion(E e){
        if(e ==  null){
            return false;
        }
        return addQuestion(root, e);
    }
    
    private boolean addQuestion(Node<E> n, E e){
        Node<E> nQuestion = new Node<>(e);
        if(e == null){
            return false;
        }else if(n == null){
            Node<E> newNodo = new Node<>(e, true);
            this.root = newNodo;
            return true;
        }else{
            if(searchNode(e) == null){
                if(n.getYes() != null && n.getNo() != null){
                    if(n.getNo().getNo() != null && n.getYes().getNo() == null && n.isFlag()){
                        return addQuestion(n.getYes(), e);
                    }
                    return addQuestion(n.getNo(), e);
                }else if(n.getNo() == null){
                    n.setNo(nQuestion);
                    return true;
                }else if(n.getYes() == null && n.getNo() != null && n.isFlag()){
                    n.setYes(nQuestion);
                    return true;
                }
                return true;
            }
            return false;
        }
    }
    
     public static DecisionTree<String> loadTree(){
        DecisionTree<String> tree = new DecisionTree<>();
        DecisionTree.Node<String> node = null;
        Stack<DecisionTree.Node<String>> pila = new Stack<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/resources/datos(1).txt"))){
            String line;            
            while((line = br.readLine()) != null){                                
                String linea = line.substring(3);                                
                node= new DecisionTree.Node<>(linea);
                if(line.startsWith("#P")){ 
                    if(pila.isEmpty()){
                        tree.root = node;
                    }else{
                        if(pila.peek().yes==null){
                            pila.peek().yes=node;                             
                        }else if(pila.peek().no==null){
                            pila.peek().no=node;
                            if(pila.size()!=1){
                                pila.pop();                            
                            }
                        }
                    }
                    pila.push(node);
                }else{ 
                    if(pila.peek().yes==null){
                        pila.peek().yes=node;
                    }else if(pila.peek().no==null){
                        pila.peek().no=node;
                        if(pila.size()!=1){
                                pila.pop();                            
                            }                        
                    }
                }
            }
        }catch(Exception ex){
            System.out.println("Exception " + ex);
        }
        return tree;
    }
    
    public void saveTree(){
        String tree = postOrden(root);        
        try(BufferedWriter bw =  new BufferedWriter(new FileWriter("src/resources/datos(1).txt"))){
            bw.write(tree);
        }catch(Exception e){
            System.out.println("Excepción" + e);
        }
    }

}
