/*
0 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyrantunleashed;

import java.util.ArrayList;

import gamebase.CardBase;
import gamebase.Player.StrategyType;
/**
 *
 * @author planb
 */
public class TyrantUnleashed {

	TyrantPlayer cg;
	TyrantPlayer opponent;
	Battle battle;
	
    public TyrantUnleashed() {
        
        cg = new TyrantPlayer("Player", 100, 3, StrategyType.SIMPLE);
        opponent = new TyrantPlayer("Opponent", 40, 3, StrategyType.DEFENSIVE);
        battle = new Battle(cg,opponent);
        
    }
	
	public void go(){
		createSampleHand(cg,opponent);
		battle.start();
	}
    
    public void createSampleHand(TyrantPlayer cg, TyrantPlayer opponent){
    	
    	//creating sample cards for player
    	 AttackBoost one = new AttackBoost("name", 20, 30);
         AttackBoost card0 = new AttackBoost("card1", 20, 30);
         ArmorBoost card1 = new ArmorBoost("card2", 20, 30);
         StrikeBoost card2 = new StrikeBoost("card3", 20, 30);
         
         ArrayList<CardBase> val = new ArrayList<>();
          
         val.add(card0);
         val.add(card1);
         val.add(card2);
         
         cg.setCards(val);
         
         //creating sample hand for opponent
         AttackBoost card3 = new AttackBoost("card4", 20, 20);
         AttackBoost card4 = new AttackBoost("card5", 20, 100);
         AttackBoost card5 = new AttackBoost("card6", 20, 200);
         
         ArrayList<CardBase> opponentHand = new ArrayList<>() ;
         
         opponentHand.add(card3);
         opponentHand.add(card4);
         opponentHand.add(card5);
         
         opponent.setCards(opponentHand);
    }

}
