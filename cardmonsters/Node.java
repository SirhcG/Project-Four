/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.Move;
import gamebase.Player;
import gamebase.CardBase;
import gamebase.Player.StrategyType;
import java.io.Serializable;


/**
 *
 * @author planb
 */
public class Node implements Serializable {
    
    int key;
    MonsterPlayer one;
    String name;
    
    
    Node leftChild;
    Node rightChild;
    
    Node(int key, MonsterPlayer opp, String name){
        
        this.key = key;
        this.one = opp;
        this.name = name;
    }
    
    @Override
    public String toString(){
        
        return("Key: " + key + " " + name + " " + one.getHand());
    }
    
}
