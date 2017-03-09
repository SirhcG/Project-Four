/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;
import gamebase.Player;
import gamebase.CardBase;
import gamebase.Player.StrategyType;
import java.io.Serializable;


/**
 *
 * @author planb
 */
public class CampaignTree implements Serializable {
    
    Node root;
    
    public void addNode(int key, MonsterPlayer opp, String name){
        
        Node add = new  Node(key, opp, name);
        
        if(root == null){
            root = add;
        }else {
            Node focus = root;
            Node parent;
            
            while(true){
                
                parent = focus;
                
                if(key < focus.key){
                    focus = focus.leftChild;
                    if(focus == null ){
                        parent.leftChild = add;
                        return;
                    }
                    }else{
                        focus = focus.rightChild;
                        if(focus == null){
                        parent.rightChild = add;
                        return;
                    }
                }
            }
        }
    }
    
    public Node find(int key){
        
        Node focus = root;
        
        while(focus.key != key){
            if(key < focus.key){
                focus = focus.leftChild;
            }else {
                focus = focus.rightChild;
            }
            
            if(focus == null)
                return null;
        }
        return focus;
    }
    
    public boolean remove(int key) {

                Node focus = root;
		Node parent = root;

		boolean isItALeftChild = true;
                while (focus.key != key) {

			parent = focus;
                        if (key < focus.key) {

				isItALeftChild = true;
                                focus = focus.leftChild;
                        } else {

				isItALeftChild = false;
                                focus = focus.rightChild;
                                }

			if (focus == null)
				return false;
                    }

		
                if (focus.leftChild == null && focus.rightChild == null) {

			if (focus == root)
			root = null;

                        else if (isItALeftChild)
			parent.leftChild = null;

			else
			parent.rightChild = null;

		}

		

		else if (focus.rightChild == null) {

			if (focus == root)
				root = focus.leftChild;

			else if (isItALeftChild)
				parent.leftChild = focus.leftChild;
                        else
				parent.rightChild = focus.leftChild;
                        }

	

		else if (focus.leftChild == null) {

			if (focus == root)
				root = focus.rightChild;

			else if (isItALeftChild)
				parent.leftChild = focus.rightChild;

			else
				parent.rightChild = focus.rightChild;
                    }
                return true;
    }
    
    
    
    public void inOrder(Node focus){
        
        
        if(focus !=null){
            
            inOrder(focus.leftChild);
            System.out.println(focus);
            inOrder(focus.rightChild);
        }
    }
    
}
