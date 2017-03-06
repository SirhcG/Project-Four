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
public class StrikeBoost extends CardBase implements CardSkill {
        
    
    private int baseStrike;
    
    public StrikeBoost(String _name, int Attack, int Health){
        super(_name, Attack, Health);
        this.baseStrike = 15;
    }
    
     @Override
    public void ability(Player p, Player o) {
        Player one = p;
        Player two = o;
        
        int val = ((TyrantPlayer) o).getCardInField();
        if(val == 0){
           int temp = ((TyrantPlayer) o).getHealth() - baseStrike;
           ((TyrantPlayer) o).setHealth(temp);
        }
        else if(val == 1){
            int temp = o.getCard(val).getHealth();
            temp = temp - 15;
            o.getCard(val).setHealth(temp);
        }
        else if(val > 1){
            int temp = o.getCard(val - 1).getHealth();
            temp = temp - 15;
            o.getCard(val - 1).setHealth(temp);
            
        }
        
        checkSkill();
    }
    
    
    @Override
    public void checkSkill() {
        System.out.println("Cards Strike Boost skill will randomly damage an opponents card!!\n");
    }

   
    
    
    
}
