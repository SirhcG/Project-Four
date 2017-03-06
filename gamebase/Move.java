package gamebase;

import cardmonsters.MonsterPlayer;

public abstract class Move {

	public int handIndex;
	
	public Move(int _handIndex){
		handIndex = _handIndex;
	}
        
        public Move(){
            
        }

	@Override
	public String toString(){
		return String.valueOf(handIndex);
	}
	
	public abstract void go(Player p);
        public abstract void Monstergo(MonsterPlayer p);
}
