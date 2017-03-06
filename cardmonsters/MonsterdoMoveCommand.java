/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.Move;
import gamebase.Player;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author planb
 */


public class MonsterdoMoveCommand implements MonsterCommand, Serializable {
    
        private static final long serialVersionUID = 8330375274075464923L;
   

	MonsterMove m;
	MonsterPlayer p;
	
	public MonsterdoMoveCommand(MonsterPlayer _p, MonsterMove _m){
		p = _p;
		m = _m;
	}
	
	@Override
	public void execute() {
		
		m.Monstergo(p);
	}

}
    

