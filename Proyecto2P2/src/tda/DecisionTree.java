/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.util.Comparator;

/**
 *
 * @author DELL
 */
public class DecisionTree<E> implements Comparator<E> {
    
    private Node<E> root;
    
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
    
}
