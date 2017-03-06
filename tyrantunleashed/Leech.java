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
 * @author Danny Huang
 */
public class Leech extends CardBase implements CardSkill{
	
	int leech;
	
	public Leech(String _name, int Attack, int Health, int _leech){
        super(_name, Attack, Health);
        this.leech = _leech;
    }
	
	@Override
    public void checkSkill() {
        System.out.println("Leech heals this card as it deals Attack damage to an Assault card.\n");
    }
	
	@Override
	public void ability(Player p, Player o) { //ability triggers before card attacks
		int temp = getHealth();
		
		if(leech > getAttack())
			temp+=getAttack();
		else
			temp+=leech;
		
		setHealth(temp);
		
		checkSkill();
	}
}
