package tyrantunleashed;

import java.util.ArrayList;
import java.util.Random;

import gamebase.CardBase;
import gamebase.CardSkill;
import gamebase.Player;

public class Weaken extends CardBase implements CardSkill{

	int weaken;
	
	public Weaken(String _name, int Attack, int Health, int _weaken){
        super(_name, Attack, Health);
        this.weaken = _weaken;
    }
	
	@Override
	public void checkSkill() {
        System.out.println("Reduces Attack of a random enemy Assault card.\n");
    }
	
	@Override
	public void ability(Player p, Player o) { //ability triggers before card attacks
		ArrayList<CardBase> oppField = o.getField();
		int fieldSize = o.getField().size();	//size of opponent's field
		
		Random rand = new Random();
		CardBase target = oppField.get(rand.nextInt(fieldSize));	//chooses a random card on opponent field
		
		target.setDamage(target.getDamage()-weaken);
		
		checkSkill();
	}
}
