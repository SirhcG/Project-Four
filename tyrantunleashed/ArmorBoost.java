/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyrantunleashed;

import gamebase.CardBase;
import gamebase.CardSkill;
import gamebase.Player;

/**
 *
 * @author planb
 */
public class ArmorBoost extends CardBase implements CardSkill {
    
    private int Armor;
    
    public ArmorBoost(String _name, int Attack, int Health){
        super(_name, Attack, Health);
        this.Armor = 15;
    }
    
     @Override
    public void ability(Player p, Player o) {
        int temp = this.getHealth();
        temp = temp + 15;
        this.setHealth(temp);
        
        checkSkill();
    }
    
  
    @Override
    public void checkSkill() {
        System.out.println("Cards Armor skill will increase Hp by 15 points !! \n");
    }

   
    
}