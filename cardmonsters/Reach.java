/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;
import gamebase.CardBase;
import gamebase.CardSkill;
import gamebase.Player;

import java.io.Serializable;
import tyrantunleashed.*;
/**
 *
 * @author planb
 */

//reach card type
public class Reach extends CardBase implements CardSkill, Serializable {
    
    private int attack;
    
    public Reach(String name, int attack, int health){
        super(name, attack, health);
        this.attack = attack;
    }
    
    public Reach(){
        super(null, 0, 0);
    }
    
    
    //can attack cards anywhere 
    @Override
    public void ability(Player p, Player o){
    
        int val = o.getCardInField();
       
        if(val == 1){
            int health = o.getCard(0).getHealth();
            health = health - attack;
            o.getCard(0).setHealth(health);
            checkSkill();
       }
        
        else if(val == 2){
            int health = o.getCard(1).getHealth();
            health = health - attack;
            o.getCard(1).setHealth(health);
            checkSkill();
        }
        else if(val == 3){
            int health = o.getCard(2).getHealth();
            health = health - attack;
            o.getCard(2).setHealth(health);
            checkSkill();
        }
        
        }

    @Override
    public void checkSkill() {
        System.out.println("Can attack any card regardless of position");
    }

}
