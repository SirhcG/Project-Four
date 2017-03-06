/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import tyrantunleashed.*;

/**
 *
 * @author planb
 */

//superclass for monter strategy
abstract class MonsterStrategy {
    
    protected MonsterPlayer p, o;
	
	public MonsterStrategy(MonsterPlayer _p){
		p = _p;
	}
	
	
	public abstract MonsterMove nextMove();
    
}
