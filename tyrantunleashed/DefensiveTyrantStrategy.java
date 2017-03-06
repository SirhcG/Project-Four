package tyrantunleashed;

import gamebase.Strategy;

public class DefensiveTyrantStrategy extends Strategy {

	public DefensiveTyrantStrategy(TyrantPlayer _p){
		super(_p);
	}

	@Override
	public TyrantMove nextMove() { //returns a move object with the handindex for the card to be played onto the field from the hand
		
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
		
		TyrantMove nextMove = new TyrantMove(index);
		return nextMove;

	}

}
