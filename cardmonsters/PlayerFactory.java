/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.CardBase;
import gamebase.Player.StrategyType;

/**
 *
 * @author planb
 */

//creates players for opponent and the user
public class PlayerFactory extends AbstractFactory{

   
    @Override
    MonsterPlayer getPlayer(String playerType) {
        
        if(playerType == null){
            return null;
        }
        else if(playerType.equalsIgnoreCase("Opponent")){
            return new MonsterPlayer("Opponent", StrategyType.SIMPLE);
        }
        else if(playerType.equalsIgnoreCase("User")){
            return new MonsterPlayer("User", StrategyType.SIMPLE);
        }
        
        return null;
    }
    
     @Override
    CardBase getCard(String Card) {
        return null;
    }
    
}
