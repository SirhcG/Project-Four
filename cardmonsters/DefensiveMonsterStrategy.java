/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;
//import tyrantunleashed.*;
import gamebase.Strategy;
import gamebase.Player;

/**
 *
 * @author planb
 */

//a defensive strategy for cardmonsters, plays the biggest defensive card in hand
public class DefensiveMonsterStrategy extends Strategy {
    
    public DefensiveMonsterStrategy(Player _p){
		super(_p);
	}

    //returns a MonsterMove object that indicates the handIndex of the card being played
	@Override
	public MonsterMove nextMove() { //returns a move object with the handindex for the card to be played onto the field from the hand
		
		int maxHp = 0;
		int index = 0;
		
		if(p.getHand().size() == 0)
			return null;
		
		for(int i = 0; i < p.getHand().size() ; i++){ //nextMove = card with the most HP
			if(p.getHand().get(i).getHealth() > maxHp){
				maxHp = p.getHand().get(i).getHealth();
				index = i;
			}
		}
		
		MonsterMove nextMove = new MonsterMove(index);
		return nextMove;

	}

}

    

