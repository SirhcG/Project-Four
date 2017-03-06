/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;
//import tyrantunleashed.*;
import gamebase.Strategy;
import gamebase.Player;
import java.io.Serializable;

/**
 *
 * @author planb
 */

public class SimpleMonsterStrategy extends Strategy implements Serializable {
    
    private static final long serialVersionUID = 5650428219175090991L;
    
    public SimpleMonsterStrategy(MonsterPlayer _p){
		super(_p);
		
	}
	
	@Override
	public MonsterMove nextMove() { //returns a move object with the handindex for the card to be played onto the field from the hand
		
		if(p.getHand().size()>0){
			MonsterMove nextMove = new MonsterMove(0); //plays the first card in hand
			return nextMove;
		}
			
		return null; //returns null if no moves can be played
	}

}

    

