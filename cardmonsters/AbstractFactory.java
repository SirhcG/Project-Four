/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.CardBase;

/**
 *
 * @author planb
 */

//AbstractFactory class
public abstract class AbstractFactory {
    
	//creates and returns a CardBase object with name of the argument Card
    abstract CardBase getCard(String Card);
    
    //creates and returns a MonsterPlayer object with name of argument Player
    abstract MonsterPlayer getPlayer(String Player);
    
}
