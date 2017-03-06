/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyrantunleashed;

import cardmonsters.MonsterPlayer;
import java.io.IOException;
import gamebase.Move;
import gamebase.Player;

/**
 *
 * @author planb
 */
public class TyrantMove extends Move{
    
	public int handIndex;

	
	public TyrantMove(int _handIndex){
		super(_handIndex);
	}
	

	@Override
	public void go(Player p) {
		
		String name = p.getHand().get(handIndex).getName();
		p.playCard(handIndex);
		
		System.out.println(p.getName() + " PLAYED " + name + " ONTO THE FIELD\n");

		try {
			System.in.read();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		p.setTurn();
		
	}

    @Override
    public void Monstergo(MonsterPlayer p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
