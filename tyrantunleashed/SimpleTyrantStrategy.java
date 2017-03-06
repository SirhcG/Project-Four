package tyrantunleashed;

import gamebase.Strategy;

public class SimpleTyrantStrategy extends Strategy{
	
	public SimpleTyrantStrategy(TyrantPlayer _p){
		super(_p);
		
	}
	
	@Override
	public TyrantMove nextMove() { //returns a move object with the handindex for the card to be played onto the field from the hand
		
		if(p.getHand().size()>0){
			TyrantMove nextMove = new TyrantMove(0); //plays the first card in hand
			return nextMove;
		}
			
		return null; //returns null if no moves can be played
	}

}
