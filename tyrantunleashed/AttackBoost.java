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
public class AttackBoost extends CardBase implements CardSkill{
    
    public AttackBoost(String _name, int Attack, int Health){
        super(_name, Attack, Health);
    }
    
    @Override
    public void ability(Player p, Player o){
       this.setDamage(0);
       int temp = this.getAttack();
       temp = temp + 15;
       this.setDamage(temp);
       
       checkSkill();
       }

    @Override
    public void checkSkill() {
        System.out.println("Cards Attack Boost skill will increase Attack by 10 points !! \n");
    }
    
}
