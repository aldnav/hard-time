/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aldnav
 */
public class Tree<T> {
    private Node<T> root;
    
    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.parent = null;
        root.children = new ArrayList<Node<T>>();
    }
    
    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }
    
    public void preOrder(Node<T> n) {
        if (n != null) {
            System.out.println(n.data);
            if (n.children != null) {                
                for (Node<T> child: n.children)
                    preOrder(child);
            }
        }
    }
    
    public static void main(String[] args) {
        Tree code = new Tree("code");
        
        Node child1 = new Node();
        child1.data = "Hello";
        child1.parent = code.root;
        code.root.children.add(child1);
        code.preOrder(code.root);
    }
}
